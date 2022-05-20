package com.verylinkedin.mypost;

import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibility;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.CreateMedia.CreateMedia;
import com.verylinkedin.mypost.CreateMedia.CreateMediaRequest;


import com.verylinkedin.mypost.models.Media;
import org.springframework.stereotype.Service;

@Service
public record MediaService(MediaRepository mediaRepository) {
    public Media createMedia(CreateMediaRequest request){
        CreateMedia createGroup = new CreateMedia(request, mediaRepository);
       return createGroup.execute();
    }



}