package kz.app;

import java.util.Date;

public class Invoice {
    private int id;

    public int getId() {
        return id;
    }
    private String sender;
    private int receiver;
    private Date date = java.util.Calendar.getInstance().getTime();;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
