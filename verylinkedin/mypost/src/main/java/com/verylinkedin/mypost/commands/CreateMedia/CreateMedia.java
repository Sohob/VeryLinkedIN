package com.verylinkedin.mypost.commands.CreateMedia;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
//import com.verylinkedin.mypost.MediaRepository;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioException;
import com.verylinkedin.mypost.minio.config.MinioService;
import com.verylinkedin.mypost.models.Comment;
import com.verylinkedin.mypost.models.Media;
import com.verylinkedin.mypost.models.Post;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public record CreateMedia(CreateMediaRequest createMediaRequest, MinioService minioService, PostRepository postRepository
                      )implements Command {

    public String execute() {
        byte[] file = createMediaRequest.file();
        String imageName = createMediaRequest.imageName();
        String contentType = createMediaRequest.contentType();
        String postId = createMediaRequest.postId();
        Post post = (Post) postRepository.findById(createMediaRequest.postId());
        Media media = Media.builder().build();

        Path path = Path.of(imageName);
        try {
            String type = contentType.split("/")[0];
            HashMap<String, String> map = new HashMap<>();

            String id = String.valueOf(media.getHigh_quality_image_id());

            if(type.equals("image")) {



                String compressed_id = String.valueOf(media.getLow_quality_image_id());
                Path compressed_path =Path.of(compressed_id);
                InputStream image_compressed = compressImage(new ByteArrayInputStream(file),imageName);
                minioService.upload(compressed_path,  image_compressed, contentType);
                map.put( "low_quality_link", media.getLow_quality_link(minioService()));

            }

            minioService.upload(Path.of(id), new ByteArrayInputStream(file), contentType);
            map.put( "link", media.getHigh_quality_link(minioService()) );


            post.getMedia().add(media);
            postRepository.save(post);

            String json = new Gson().toJson(map);
            return json ;

        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        }

    }

    public static InputStream compressImage(InputStream imgStream, String imageName   ) {
        float quality = 0.0f;
        String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
        // Returns an Iterator containing all currently registered ImageWriters that claim to be able to encode the named format.
        // You don't have to register one yourself; some are provided.
        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(imageExtension).next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Check the api value that suites your needs.
        // A compression quality setting of 0.0 is most generically interpreted as "high compression is important,"
        // while a setting of 1.0 is most generically interpreted as "high image quality is important."
        imageWriteParam.setCompressionQuality(quality);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // MemoryCacheImageOutputStream: An implementation of ImageOutputStream that writes its output to a regular
        // OutputStream, i.e. the ByteArrayOutputStream.
        ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(baos);
        // Sets the destination to the given ImageOutputStream or other Object.
        imageWriter.setOutput(imageOutputStream);
        BufferedImage originalImage = null;
        try (InputStream inputStream = imgStream) {
            originalImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            String info = String.format("compressImage - bufferedImage (file %s)- IOException - message: %s ", imageName, e.getMessage());
            System.out.println(info);
            InputStream myInputStream = new ByteArrayInputStream(baos.toByteArray());

            return myInputStream;
        }
        IIOImage image = new IIOImage(originalImage, null, null);
        try {
            imageWriter.write(null, image, imageWriteParam);
        } catch (IOException e) {
            String info = String.format("compressImage - imageWriter (file %s)- IOException - message: %s ", imageName, e.getMessage());
            System.out.println(info);
        } finally {
            imageWriter.dispose();
        }
        InputStream myInputStream = new ByteArrayInputStream(baos.toByteArray());

        return myInputStream;    }

}
