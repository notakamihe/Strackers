package sample.models;

public class User {
    private int id;
    private String username, password, email;

    public User(int id, String username, String email, String password)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        id = 1;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        email = "";
        id = 1;
    }

    public User () {
        this.username = "";
        this.email = "";
        this.password = "";
        this.id = 1;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
