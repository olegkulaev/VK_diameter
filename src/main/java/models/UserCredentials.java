package models;

public class UserCredentials {
    public UserCredentials(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    private Integer id;
    private String login;
    private String password;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static UserCredentials getDefault(){
        return new UserCredentials(0, "2", "4");
    }
}
