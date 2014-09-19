package kz.app.dao;

import java.util.List;
import kz.app.Invoice;
import kz.app.InvoicePart;

public interface InvoiceDao {

    public List<Invoice> getListInvoice();
    
    public void saveInvoice(Invoice inv);
    
    public List<InvoicePart> getListInvoicePart(Invoice inv);
    
    public void saveInvoicePart(Invoice inv);

}
