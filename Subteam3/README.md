# Documentation

## Notifications
-type(String): Messages, Bans, Likes or Comments
-userID(int): ID of the user
-senderID(int): ID of the user causing the notification
-content(String): Content of the notifications
-seen(bool): Whether the user have viewed the notification or not
-createdAt(datetime): Date and time of the notification

## Chats
-chatID(int): ID for the chat session
-chatName(String): Chat name
-participants(List of integers): List of participants
-messages(List of objects): Object of this format-> {sender: int, content: String,time: time}
-createdAt(datetime): Date and time of the chat
-createdBy(int): ID of the chat creator



Please document your code! :)