package kz.app;

import kz.app.dao.GoodsDao;
import kz.app.entity.GoodsEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.data.FilterEvent;

import java.util.Date;
import java.util.List;

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
    private Double filteredSum;
    
   

	@PostConstruct
    public void init() {
        dao = new GoodsDao();
        begin = new Date();
        end = new Date();
        sum = 0.0;
        filteredSum = 0.0;
      //  searchGoods();
    }
    
    //SUM  списке товаров на продажу
    public Double getTotalSum() {
        sum=0.0;
        if (getListGoods()!=null)
        	getListGoods().forEach(e -> {
        		sum = sum + e.getSum();
        	});
        return sum;
    }
    
    public Double getFilteredSum() {
    	filteredSum=0.0;
        if (getFilteredGoods()!=null)
        	getFilteredGoods().forEach(e -> {
        		filteredSum = filteredSum + e.getSum();
        	});
		return filteredSum;
	}

	public void setFilteredSum(Double filteredSum) {
		this.filteredSum = filteredSum;
	}
    
 
    
    public void searchGoods() {
    	System.out.println(begin+"   "+end);
        Date beginSql = new Date(begin.getTime());
        Date endSql = new Date(end.getTime());
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
