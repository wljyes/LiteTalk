package club.wljyes.bean;

public class FriendRequest {
    public FriendRequest(String fromUser, String toUser, int isFriend) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.isFriend = isFriend;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public int getIsFriend() {
        return isFriend;
    }

    private String fromUser;
    private String toUser;
    private int isFriend;
}
