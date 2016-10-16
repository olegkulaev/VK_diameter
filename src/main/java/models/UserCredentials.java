package models;

public class UserCredentials {
    public UserCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static UserCredentials getDefault(){
        return new UserCredentials("2", "4");
    }
}
