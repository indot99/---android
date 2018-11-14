package org.richcode.koreatoron.ModelClass;

import java.util.PriorityQueue;

public class ToronModel {

    private String title;
    private String string_true;
    private String string_false;
    private String date;
    private String content;
    private String comment_count;
    private String href;

    public ToronModel(String title, String string_true, String string_false, String date, String content, String comment_count, String href) {
        this.title = title;
        this.string_true = string_true;
        this.string_false = string_false;
        this.date = date;
        this.content = content;
        this.comment_count = comment_count;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getString_true() {
        return string_true;
    }

    public void setString_true(String string_true) {
        this.string_true = string_true;
    }

    public String getString_false() {
        return string_false;
    }

    public void setString_false(String string_false) {
        this.string_false = string_false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
