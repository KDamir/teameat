package kz.app.dao;

import kz.app.Invoice;
import kz.app.MeatPart;

import java.util.List;

public interface InvoiceDao {

    public List<Invoice> getListInvoice();
    
    public void saveInvoice(Invoice inv);
    
    public List<MeatPart> getListInvoicePart(Invoice inv);
    
    public void saveInvoicePart(Invoice inv);

}
