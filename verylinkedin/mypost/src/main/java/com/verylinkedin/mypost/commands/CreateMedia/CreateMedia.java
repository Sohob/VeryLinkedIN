package com.verylinkedin.mypost.commands.CreateMedia;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.MediaRepository;
import com.verylinkedin.mypost.models.Media;

public record CreateMedia(CreateMediaRequest request, MediaRepository mediaRepository)implements Command {
    public Media execute() {
        Media media = Media.builder()
                .build();
        mediaRepository.save(media);
        return media;
    }
}
