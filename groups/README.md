# Groups Service

## Commands:
### CreateGroup: _Creates a group chat_
#### Inputs:
- ArrayList<String> participants,
- String title,
- String description,
- String admin,
- String groupPhoto

### DeleteGroup: _Deletes a group chat from the database_
#### Inputs:
- String userId,
- String groupId

### DeleteMessage: _Deletes a given message from a given chat_
#### Inputs:
- String userId,
- String groupId,
- String messageId

### EditMessage: _Edits a given message in a given chat_
#### Inputs:

- String userId,
- String groupId,
- String messageId,
- String editedMessage

### SendMessage: _Sends a given message to a given chat_
#### Inputs:

- String userId,
- String groupId,
- String message

### UpdateGroup: _Updates the group chat in the database with the given changes_
#### Inputs:

- String userId,
- String groupId,
- ArrayList<String> participants,
- String title,
- String description,
- String admin,
- String groupPhoto

### ViewGroup: _Displays the given chat and marks messages as read for the user_
#### Inputs:

- String userId,
- String groupId