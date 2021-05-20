package sample.models;

public class Sticker
{
    private int id;
    private String name;
    private String location;
    private int userId;

    public Sticker()
    {
        this.name = "";
        this.location = "";
        this.userId = 0;
    }

    public Sticker(int id, String name, int userId, String location)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Sticker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", userId=" + userId +
                '}';
    }
}
