package club.wljyes.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {
    @SerializedName(value = "fromUser", alternate = {"from_user", "from_username", "sender"})
    public final String fromUser;
    @SerializedName(value = "toUser", alternate = {"to_user", "to_username", "receiver"})
    public final String toUser;
    public final String content;
    private final Date date;

    public Message(String fromUser, String toUser, String content) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        date = new Date();
    }

    @Override
    public String toString() {
        return "[fromUser=" + fromUser + ",toUser=" + toUser + ",content=" + content + ",date=" + date + "]";
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
