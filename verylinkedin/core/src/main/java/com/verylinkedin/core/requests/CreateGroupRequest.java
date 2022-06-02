package com.verylinkedin.core.requests;

import java.util.ArrayList;

public record CreateGroupRequest(
        ArrayList<String> participants,
        String title,
        String description,
        String admin,
        String groupPhoto) {
}
