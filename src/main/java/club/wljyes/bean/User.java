package club.wljyes.bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class User {

    private int id;
    private String username;
    private String nickname;
    private String avatarUrl;
    private String password;

    {
        avatarUrl = "https://wljyes.club/avatar-default.jpg";
    }

    public User() {}

    public User(String username, String nickname, String avatarUrl) {
        this.username = username;
        this.nickname = nickname;
        if (avatarUrl != null)
            this.avatarUrl = avatarUrl;
    }

    public User(String username, String nickname, String avatarUrl, String password) {
        this(username, nickname, avatarUrl);
        this.password = password;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[id=" + id + ",username=" + username + ",nickname=" + nickname + ",avatar=" + avatarUrl
                + ",password=" + password + "]";
    }

    @Override
    public Object clone() {
        User user = new User(username, nickname, avatarUrl, password);
        user.setId(id);
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        } else {
            User user = (User) obj;
            return user.username.equals(username) & id == user.id & nickname.equals(user.nickname)
                    & avatarUrl.equals(user.avatarUrl) & Objects.equals(password, user.password);
        }
    }

    @Override
    public int hashCode() {
        int result = 0;
        result += 31 * result + Integer.hashCode(id);
        result += 31 * result + username.hashCode();
        result += 31 * result + nickname.hashCode();
        result += 31 * result + avatarUrl.hashCode();
        result += 31 * result + Objects.hashCode(password);
        return result;
    }
}
