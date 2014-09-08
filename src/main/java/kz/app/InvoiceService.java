package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class InvoiceService {
	private List<Invoice> invoiceList;

	public List<Invoice> getInvoice() {
		return invoiceList;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoiceList = invoice;
	}
	@PostConstruct
	public void init() {
		Invoice inv = new Invoice();
		invoiceList = new ArrayList<>();
		invoiceList.add(inv);
	}
	public void updateInvoice(Invoice inv) {
		if(invoiceList.lastIndexOf(inv) == invoiceList.size() - 1) {
			Invoice in = new Invoice();
			invoiceList.add(in);
		}
	}
}
