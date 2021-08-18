/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author nguye
 */
public class Digital {
    private int id;
    private String title;
    private String content;
    private String description;
    private String image;
    private Timestamp time;
    private String author;

    public Digital() {
    }

    public Digital(int id, String title, String content, String description, String image, Timestamp time, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.image = image;
        this.time = time;
        this.author = author;
    }
     public String getDateConvert() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - h:mm", Locale.ENGLISH);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("a", Locale.ENGLISH); // get am or pm
        String date = dateFormat1.format(this.time) + dateFormat2.format(this.time).toLowerCase();
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Digital{" + "id=" + id + ", title=" + title + ", content=" + content + ", description=" + description + ", image=" + image + ", time=" + time + ", author=" + author + '}';
    }
    
}
