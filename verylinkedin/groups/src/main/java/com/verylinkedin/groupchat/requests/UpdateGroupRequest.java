package com.verylinkedin.groupchat.requests;

import java.util.ArrayList;

public record UpdateGroupRequest(
        String userId,
        String groupId,
        ArrayList<String> participants,
        String title,
        String description,
        String admin,
        String groupPhoto
) {
}
