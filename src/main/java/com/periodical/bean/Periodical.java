/**
 * 与期刊信息表的字段一一对应
 */
package com.periodical.bean;

import javax.persistence.*;

@Entity
@Table(name="periodicals")
public class Periodical {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int periodicalId;
    private String periodicalName;
    private String author;
    private String print;
    private String publishDate;
    private int remaining;
    private int used;
    private String type;

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "periodicalId=" + periodicalId +
                ", periodicalName='" + periodicalName + '\'' +
                ", author='" + author + '\'' +
                ", print='" + print + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", remaininng=" + remaining +
                ", used=" + used +
                ", type='" + type + '\'' +
                '}';
    }
}
