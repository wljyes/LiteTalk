package club.wljyes.bean;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName(value = "fromUser", alternate = {"from_user", "from_username", "sender"})
    public final String fromUser;
    @SerializedName(value = "toUser", alternate = {"to_user", "to_username", "receiver"})
    public final String toUser;
    public final String content;

    public Message(String fromUser, String toUser, String content) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
    }
}
