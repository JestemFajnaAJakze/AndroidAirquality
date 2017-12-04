package tim.lab7.rest.model;


public class CommentPOJO {
    private int id;
    private String date;
    private String user;
    private String subject; //temat wpisu
    private String content; //treść wpisu

    public CommentPOJO() {
    }

    public CommentPOJO(int id, String date, String user, String subject, String content) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.subject = subject;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "date: " + date + "\n" +
                "user: " + user + "\n" +
                "subject: " + subject + "\n" +
                "content: " + content;
    }
}
