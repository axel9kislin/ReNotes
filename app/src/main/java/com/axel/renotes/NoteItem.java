package com.axel.renotes;

/**
 * Created by Александр on 08.04.2016.
 */
public class NoteItem {
    private String title;
    private String disc;
    private String imagePath;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }
    public void setDisc(String text) {
        this.disc = text;
    }

    public String getImagePath()
    {
        return imagePath;
    }
    public void setImagePath(String path)
    {
        this.imagePath = path;
    }
}
