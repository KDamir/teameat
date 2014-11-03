package kz.app;

import java.util.Date;

public class Purchase {
    private int id;

    public int getId() {
        return id;
    }
    private String receiver;
    private int supplier_id;
    private Date date;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
