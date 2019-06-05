package club.wljyes.bean;

public class User {

    private String username;
    private String nickname;
    private String avatarUrl;
    private String password;

    public User(String username, String nickname, String avatarUrl) {
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    public User(String username, String nickname, String avatarUrl, String password) {
        this(username, nickname, avatarUrl);
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
