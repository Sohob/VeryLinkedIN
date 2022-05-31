//package com.verylinkedin.mypost.minio;
//
//import com.verylinkedin.mypost.commands.CreateMedia.CreateMediaRequest;
//import com.verylinkedin.mypost.MediaService;
//import com.verylinkedin.mypost.minio.config.*;
//import com.verylinkedin.mypost.models.Media;
//
//import io.minio.messages.Item;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.imageio.IIOImage;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageWriteParam;
//import javax.imageio.ImageWriter;
//import javax.imageio.stream.ImageOutputStream;
//import javax.imageio.stream.MemoryCacheImageOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Path;
//import java.util.HashMap;
//import java.util.List;
//
//
//@Service
//@AllArgsConstructor
//@Slf4j
//@RestController
//@RequestMapping("/files")
//public class TestController {
//
//    @Autowired
//    private MinioService minioService;
//    private MediaService mediaService ;
//
//    @GetMapping
//    public List<Item> testMinio() throws MinioException {
//        return minioService.list();
//    }
//
//    @GetMapping("/{object}")
//    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
//        InputStream inputStream = minioService.get(Path.of(object));
//        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
//
//        // Set the content type and attachment header.
//        response.addHeader("Content-disposition", "attachment;filename=" + object);
//        response.setContentType(URLConnection.guessContentTypeFromName(object));
//
//        // Copy the stream to the response's output stream.
//        IOUtils.copy(inputStream, response.getOutputStream());
//        response.flushBuffer();
//    }
//
//    @PostMapping
//    public HashMap addAttachement(@RequestParam("file") MultipartFile file , HttpServletRequest request ) {
//        Path path = Path.of(file.getOriginalFilename());
//        try {
//            String type = file.getContentType().split("/")[0];
//            HashMap<String, String> map = new HashMap<>();
//
//            CreateMediaRequest createMediaRequest = new CreateMediaRequest();
//            Media media_doc  =  mediaService.createMedia(createMediaRequest);
//            String id = media_doc.getId();
//
//            if(type.equals("image")) {
//
//                CreateMediaRequest createMediaRequest_compressed = new CreateMediaRequest();
//                Media media_doc_compressed = mediaService.createMedia(createMediaRequest_compressed);
//                String compressed_id = media_doc_compressed.getId();
//                minioService.upload(Path.of(compressed_id),  compressImage(file), file.getContentType());
//
//                ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
//                URI newUri = builder.build().toUri();
//                ServletUriComponentsBuilder.fromCurrentRequestUri();
//                URL serverURL;// port
//                serverURL = new URL(newUri.getScheme(),      // http
//                            newUri.getHost(),  // host
//                            newUri.getPort(),
//                            "/files/"+compressed_id);
//                map.put( "low_quality_link", String.valueOf(serverURL));
//
//            }
//
//
//            minioService.upload(Path.of(id), file.getInputStream(), file.getContentType());
//
//            ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
//           // builder.scheme("https");
//            URI newUri = builder.build().toUri();
//            ServletUriComponentsBuilder.fromCurrentRequestUri();
//            URL serverURL;// port
//            serverURL = new URL(newUri.getScheme(),      // http
//                    newUri.getHost(),  // host
//                    newUri.getPort(),
//                    "/files/"+id);
//            map.put( "link", String.valueOf(serverURL));
//
//            return map;
//
//        } catch (MinioException e) {
//            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
//        } catch (IOException e) {
//            throw new IllegalStateException("The file cannot be read", e);
//        }
//    }
//
//    private InputStream compressImage(MultipartFile mpFile) {
//        float quality = 0.0f;
//        String imageName = mpFile.getOriginalFilename();
//        String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
//        // Returns an Iterator containing all currently registered ImageWriters that claim to be able to encode the named format.
//        // You don't have to register one yourself; some are provided.
//        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(imageExtension).next();
//        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
//        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Check the api value that suites your needs.
//        // A compression quality setting of 0.0 is most generically interpreted as "high compression is important,"
//        // while a setting of 1.0 is most generically interpreted as "high image quality is important."
//        imageWriteParam.setCompressionQuality(quality);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        // MemoryCacheImageOutputStream: An implementation of ImageOutputStream that writes its output to a regular
//        // OutputStream, i.e. the ByteArrayOutputStream.
//        ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(baos);
//        // Sets the destination to the given ImageOutputStream or other Object.
//        imageWriter.setOutput(imageOutputStream);
//        BufferedImage originalImage = null;
//        try (InputStream inputStream = mpFile.getInputStream()) {
//            originalImage = ImageIO.read(inputStream);
//        } catch (IOException e) {
//            String info = String.format("compressImage - bufferedImage (file %s)- IOException - message: %s ", imageName, e.getMessage());
//          System.out.println(info);
//            InputStream myInputStream = new ByteArrayInputStream(baos.toByteArray());
//
//            return myInputStream;
//        }
//        IIOImage image = new IIOImage(originalImage, null, null);
//        try {
//            imageWriter.write(null, image, imageWriteParam);
//        } catch (IOException e) {
//            String info = String.format("compressImage - imageWriter (file %s)- IOException - message: %s ", imageName, e.getMessage());
//            System.out.println(info);
//        } finally {
//            imageWriter.dispose();
//        }
//        InputStream myInputStream = new ByteArrayInputStream(baos.toByteArray());
//
//        return myInputStream;    }
//}