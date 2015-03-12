package kz.app;

import kz.app.dao.GoodsDao;
import kz.app.dao.InvoiceDao;
import kz.app.dao.MeatPartDao;
import kz.app.entity.GoodsEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.utils.HibernateUtil;
import kz.app.utils.MeatPartConverter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import kz.app.utils.Constants;

/**
 *
 * @author damir.keldibekov
 */
@ManagedBean(name = "goodsHistoryService")
@SessionScoped
public class GoodsHistoryService extends AbstractMeatPartController{
    private List<GoodsEntity> listGoods;
    private List<GoodsEntity> filteredGoods;
    
    private Date begin;
    private Date end;
	private static GoodsDao dao;
    private Double sum;
    
    @PostConstruct
    public void init() {
        dao = new GoodsDao();
        begin = new Date();
        end = new Date();
        sum = 0.0;
      //  searchGoods();
    }
    
    //SUM  списке товаров на продажу
    public Double getTotalSum() {
        

    	listGoods.forEach(e -> {
            sum = sum + e.getSum();
        });
        return sum;
    }
    
    public void searchGoods() {
        java.sql.Date beginSql = new java.sql.Date(begin.getTime());
        java.sql.Date endSql = new java.sql.Date(end.getTime());
        setListGoods(dao.getListGoods(beginSql,endSql));
    }
    
	@Override
	public void updateOrder() {
		// TODO Auto-generated method stub
		
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<GoodsEntity> getListGoods() {
		return listGoods;
	}

	public void setListGoods(List<GoodsEntity> listGoods) {
		this.listGoods = listGoods;
	}

	public List<GoodsEntity> getFilteredGoods() {
		return filteredGoods;
	}

	public void setFilteredGoods(List<GoodsEntity> filteredGoods) {
		this.filteredGoods = filteredGoods;
	}
}
