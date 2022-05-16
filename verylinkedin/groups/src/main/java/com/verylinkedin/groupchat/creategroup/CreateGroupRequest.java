package com.verylinkedin.groupchat.creategroup;

import java.util.ArrayList;

public record CreateGroupRequest(ArrayList<String> participants,
                                 String title,
                                 String description,
                                 String owner,
                                 String groupPhoto) {
}
