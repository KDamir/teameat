package kz.app;

import java.util.Date;

public class Invoice {
    private int id;

    public int getId() {
        return id;
    }
    private String sender;
    private String receiver;
    private Date date;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
