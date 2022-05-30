package com.verylinkedin.mypost.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Media {
    @Id
    private String id;
    private String type;
}
