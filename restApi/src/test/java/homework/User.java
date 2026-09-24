package homework;

public class User {
    Integer id;
    String title = "foo";
    String body = "bar";
    Integer userId = (Integer) 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{\n" +
                "userId: " + getUserId() + "\n" +
                "id: " + getId() + "\n" +
                "title: " + getTitle() + "\n" +
                "body: " + getBody() + "\n" +
                "}";
    }
}
