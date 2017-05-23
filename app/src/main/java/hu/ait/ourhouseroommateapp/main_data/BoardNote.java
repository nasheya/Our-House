package hu.ait.ourhouseroommateapp.main_data;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class BoardNote extends GeneralItem {
    private String title;
    private String body;

    public BoardNote(){}

    public BoardNote(String title, String body){
        this.title = title;
        this.body = body;
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
}
