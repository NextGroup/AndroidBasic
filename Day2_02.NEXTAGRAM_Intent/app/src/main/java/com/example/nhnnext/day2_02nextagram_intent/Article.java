package com.example.nhnnext.day2_02nextagram_intent;

public class Article {

    private int articleNumber;
    private String title;
    private String writer;
    private String id;
    private String content;
    private String writeDate;
    private String imgName;

    public Article (int articleNumber, String title, String writer,
                    String id, String content, String writeDate, String imgName){
        this.articleNumber = articleNumber;
        this.title = title;
        this.writer = writer;
        this.id = id;
        this.content = content;
        this.writeDate = writeDate;
        this.imgName = imgName;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public String getImgName() {
        return imgName;
    }
}













