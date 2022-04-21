package com.verylinkedin.groupchat;

import java.util.ArrayList;

public record CreateGroupRequest(ArrayList<Integer> participants,
                                 String title,
                                 String description,
                                 Integer owner,
                                 String groupPhoto) {
}
