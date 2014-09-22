package kz.app;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategory;
import kz.app.entity.MeatTypes;
import kz.app.utils.HibernateUtil;

@ManagedBean
@SessionScoped
public class InvoiceService {
    private List<MeatPart> meatPartList;
    private Invoice invoice;
    private List<String> list1;
    private List<String> list2;

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @PostConstruct
    public void init() {
        invoice = new Invoice();
        meatPartList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            meatPartList.add(new MeatPart());
        }
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        MeatPartDao meatPartDao = new MeatPartDao();
        HibernateUtil.getSession().beginTransaction();
        List<MeatCategory> list = meatPartDao.getListCategory();
        HibernateUtil.getSession().getTransaction().commit();
        list.forEach(e -> list1.add(e.getName()));
        
        HibernateUtil.getSession().beginTransaction();
        List<MeatTypes> lista = meatPartDao.getListMeatTypes();
        HibernateUtil.getSession().getTransaction().commit();
        lista.forEach(e -> list2.add(e.getType()));
    }
    
    public List<MeatPart> getMeatPartList() {
        return meatPartList;
    }

}
