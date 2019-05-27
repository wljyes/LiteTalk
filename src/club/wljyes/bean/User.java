package club.wljyes.bean;

public class User {

    private String name;
    private String nickname;
    private String avatarUrl;
    private String password;

    public User(String name, String nickname, String avatarUrl) {
        this.name = name;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    public User(String name, String nickname, String avatarUrl, String password) {
        this(name, nickname, avatarUrl);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPassword() {
        return password;
    }
}
