/**
 * 与数据库中的表circulate_records中字段一一对应
 *
 */
package com.periodical.bean;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "circulate_records")
public class Record {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int circulateId;
    private Date borrowDate;
    private Date returnDate;
    private int periodicalId;
    private int userId;
    private int num;

    public int getCirculateId() {
        return circulateId;
    }

    public void setCirculateId(int circulateId) {
        this.circulateId = circulateId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Record{" +
                "circulateId=" + circulateId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", periodicalId=" + periodicalId +
                ", userId=" + userId +
                ", num=" + num +
                '}';
    }
}
