package com.verylinkedin.mypost.commands.CreateMedia;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioException;
import com.verylinkedin.mypost.minio.config.MinioService;
import com.verylinkedin.mypost.models.Media;
import com.verylinkedin.mypost.models.Post;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.bson.types.ObjectId;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;


public record CreateMedia(CreateMediaRequest createMediaRequest, MinioService minioService,
                          PostRepository postRepository
) implements Command {


    public static InputStream compressImage(InputStream inputStream, String imageName) throws IOException
    {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);

        float imageQuality = 0.0f;

        // Create the buffered image
        BufferedImage bufferedImage = ImageIO.read(inputStream);

        // Get image writers
        Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName(imageExtension); // Input your Format Name here

        if (!imageWriters.hasNext())
            throw new IllegalStateException("Writers Not Found!!");

        ImageWriter imageWriter = imageWriters.next();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);

        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

        // Set the compress quality metrics
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(imageQuality);

        // Compress and insert the image into the byte array.
        imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

        byte[] imageBytes = outputStream.toByteArray();

        // close all streams
        inputStream.close();
        outputStream.close();
        imageOutputStream.close();
        imageWriter.dispose();

        InputStream myInputStream = new ByteArrayInputStream(imageBytes);

        return myInputStream;
    }

    public String execute() {
        byte[] file = createMediaRequest.file();
        String imageName = createMediaRequest.imageName();
        String contentType = createMediaRequest.contentType();
        String postId = createMediaRequest.postId();
        Post post = postRepository.findById(createMediaRequest.postId());
        Media media = Media.builder().postId(post.getId()).low_quality_image_id( String.valueOf(new ObjectId())).high_quality_image_id( String.valueOf(new ObjectId())).build();


        Path path = Path.of(imageName);
        try {
            String type = contentType.split("/")[0];
            HashMap<String, String> map = new HashMap<>();

            String id = String.valueOf(media.getHigh_quality_image_id());

            if (type.equals("image")) {


                String compressed_id = String.valueOf(media.getLow_quality_image_id());
                Path compressed_path = Path.of(compressed_id);
                InputStream image_compressed = compressImage(new ByteArrayInputStream(file), imageName);
                minioService.upload(compressed_path, image_compressed, contentType);
                map.put("low_quality_link", media.getLow_quality_link(minioService()));

            }
            System.out.println(Path.of(id)+" "+ new ByteArrayInputStream(file)+" "+ contentType);

            minioService.upload(Path.of(id), new ByteArrayInputStream(file), contentType);
            map.put("link", media.getHigh_quality_link(minioService()));



            post.getMedia().add(media);
            postRepository.save(post);
            String json = new Gson().toJson(map);
            return json;

        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
