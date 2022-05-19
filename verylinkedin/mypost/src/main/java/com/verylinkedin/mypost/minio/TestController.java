package com.verylinkedin.mypost.minio;

import com.verylinkedin.mypost.CreateMedia.CreateMedia;
import com.verylinkedin.mypost.CreateMedia.CreateMediaRequest;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.MediaRepository;
import com.verylinkedin.mypost.MediaService;
import com.verylinkedin.mypost.PostService;
import com.verylinkedin.mypost.minio.config.*;
import com.verylinkedin.mypost.models.Media;

import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/files")
public class TestController {

    @Autowired
    private MinioService minioService;
    private MediaService mediaService ;

    @GetMapping
    public List<Item> testMinio() throws MinioException {
        return minioService.list();
    }

    @GetMapping("/{object}")
    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
        InputStream inputStream = minioService.get(Path.of(object));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Set the content type and attachment header.
        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));

        // Copy the stream to the response's output stream.
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @PostMapping
    public HashMap addAttachement(@RequestParam("file") MultipartFile file , HttpServletRequest request ) {
        Path path = Path.of(file.getOriginalFilename());
        try {
           CreateMediaRequest createMediaRequest = new CreateMediaRequest();
            Media media_doc  =  mediaService.createMedia(createMediaRequest);
            String id = media_doc.getId();

            minioService.upload(Path.of(id), file.getInputStream(), file.getContentType());

            HashMap<String, String> map = new HashMap<>();

            ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
           // builder.scheme("https");
            URI newUri = builder.build().toUri();
            ServletUriComponentsBuilder.fromCurrentRequestUri();
            URL serverURL;// port
            serverURL = new URL(newUri.getScheme(),      // http
                    newUri.getHost(),  // host
                    newUri.getPort(),
                    "/files/"+id);
            map.put( "link", String.valueOf(serverURL));



            return map;

        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }
    }
}