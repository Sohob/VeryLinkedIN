package com.verylinkedin.mypost;

import com.verylinkedin.mypost.commands.CreateMedia.CreateMedia;
import com.verylinkedin.mypost.commands.CreateMedia.CreateMediaRequest;


import com.verylinkedin.mypost.models.Media;
import org.springframework.stereotype.Service;

@Service
public record MediaService(MediaRepository mediaRepository) {
    public Media createMedia(CreateMediaRequest request){
        CreateMedia createMedia = new CreateMedia(request, mediaRepository);
       return createMedia.execute();
    }



}
