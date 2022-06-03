package com.verylinkedin.groupchat;

import org.json.JSONException;

public interface Command {
    Object execute() throws JSONException;
}
