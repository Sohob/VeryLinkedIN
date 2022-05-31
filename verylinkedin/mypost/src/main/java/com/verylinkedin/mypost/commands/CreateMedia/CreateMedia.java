package com.verylinkedin.mypost.commands.CreateMedia;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.MediaRepository;
import com.verylinkedin.mypost.models.Media;

public record CreateMedia(CreateMediaRequest request, MediaRepository mediaRepository)implements Command {
    public String execute() {
        Media media = Media.builder()
                .build();
        mediaRepository.save(media);
        String json = new Gson().toJson(media );
        return json ;
    }
}
