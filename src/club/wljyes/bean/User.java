package club.wljyes.bean;

public class User {
    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    private String name;
    private String nickname;
    private String avatarUrl;

    public User(String name, String nickname, String avatarUrl) {
        this.name = name;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }
}
