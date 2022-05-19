package com.verylinkedin.mypost.CreateMedia;

import com.verylinkedin.mypost.CreateMedia.CreateMediaRequest;
import com.verylinkedin.mypost.MediaRepository;
import com.verylinkedin.mypost.models.Media;

public record CreateMedia(CreateMediaRequest request, MediaRepository mediaRepository) {
    public Media execute() {
        Media media = Media.builder()
                .build();
        mediaRepository.save(media);
        return media;
    }
}
