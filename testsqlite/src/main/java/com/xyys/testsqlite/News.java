package com.xyys.testsqlite;

import java.sql.Date;

/**
 * @ProjectName: MemoryTrain
 * @Package: com.xyys.testsqlite
 * @ClassName: news
 * @Description: 类作用描述
 * @Author: 完美中正
 * @CreateDate: 2021/11/19 20:12
 * @Version: 1.0
 */
public class News{
    private int id;

    private String title;

    private String content;

    private Date publishDate;

    private int commentCount;

    public News() {
    }

    public News(int id, String title, String content, Date publishDate, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.commentCount = commentCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public int getCommentCount() {
        return commentCount;
    }
}
