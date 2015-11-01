package kz.app;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;










import kz.app.dao.InventoryDao;
import kz.app.dao.InvoiceDao;
import kz.app.dao.MeatPartDao;
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.dao.MeatTypeDao;
import kz.app.dao.PurchaseDao;
import kz.app.entity.InventoryEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.MeatPartPurchaseEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.PurchaseEntity;

import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "ostatkiController")
@SessionScoped
public class OstatkiController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private LazyDataModel<Ostatki> ostatkis;
	private List<Ostatki> ostatkisInit; 
    private List<InventoryEntity> inventory;
	private InventoryDao invDao;
	private MeatTypeDao mtDao;
	private MeatPartDao mpDao;
	private MeatPartPurchaseDao mppDao;
	private InvoiceDao iDao;
	private PurchaseDao pDao;
    
	private Ostatki selectedOstatki;
    
	

	public Ostatki getSelectedOstatki() {
		return selectedOstatki;
	}

	public void setSelectedOstatki(Ostatki selectedOstatki) {
		this.selectedOstatki = selectedOstatki;
	}

	@PostConstruct
	public void init() {
		invDao = new InventoryDao();
		mtDao = new MeatTypeDao();
		mpDao = new MeatPartDao();
		mppDao = new MeatPartPurchaseDao();
		iDao = new InvoiceDao();
		pDao = new PurchaseDao();
		
		long startTime = System.currentTimeMillis();
		inventory = invDao.getListInventory();
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("invDao.getListInventory time = " + totalTime);
		
		countOstatki();
		ostatkis = new OstatkiDataModel(ostatkisInit);
	}
	
	public void update(){
		countOstatki();
		ostatkis = new OstatkiDataModel(ostatkisInit);
	}

	public LazyDataModel<Ostatki> getOstatkis() {
		
		ostatkis = new OstatkiDataModel(ostatkisInit);
		return ostatkis;
	}

	private void countOstatki() {
		// TODO Auto-generated method stub
		Ostatki ost;
		ostatkisInit = new ArrayList<Ostatki>();
		BigInteger barcode;
		Date invDate;
		double invOst;
		double quantityOst;
		String name;
		String supplier;
		MeatTypesEntity meatTypeEntity;
		MeatPartEntity meatPartEntity;
		
		if (ostatkisInit != null)
			ostatkisInit.clear();
		
		if (inventory!=null)
			for (int i=0; i< inventory.size();i++){
			
				barcode = inventory.get(i).getBarcode();
				invDate = inventory.get(i).getDate();
				invOst = inventory.get(i).getQuntity();
			
			
				// данного штрих кода нет в таблице meattype
				meatTypeEntity = mtDao.getByBarcode(barcode);
				if (meatTypeEntity == null){
					name ="Неизвестный товар (товара с таким штрих кодом нет в базе)";
					supplier = "Неизвестный товар (товара с таким штрих кодом нет в базе)";
					quantityOst = 0.0;
				}
				else{
					name = meatTypeEntity.getName();
					meatPartEntity = mpDao.getMeatPartByBarcode(barcode);
					if (meatPartEntity == null)
						supplier = "Неизвестный товар (товар не отмечен ни в одной из поставок)";
					else	
						supplier = meatPartEntity.getInvoiceId().getSender();
				
					quantityOst = quantityOst(invOst,meatTypeEntity, invDate);
				}
			
				try{
					ost = new Ostatki(barcode,
							  name, 
							  invOst, 
							  quantityOst, 
							  supplier, 
							  invOst-quantityOst);
				}
				catch(RuntimeException e){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Неизвестный штрих код в таблице инвентаризация",barcode.toString());
					FacesContext.getCurrentInstance().addMessage(null, msg);
					throw e;
				}finally{
				
				}
				ostatkisInit.add(ost);
			}
		
		
		
	}

	private double quantityOst(double invOst, MeatTypesEntity mte, Date invDate) {
		
		List<MeatPartEntity> mpList = new ArrayList<MeatPartEntity>();
		List<MeatPartPurchaseEntity> mppList = new ArrayList<MeatPartPurchaseEntity>();
		double quantityOst =invOst;
		
		List<InvoiceEntity> iList = iDao.getListInvoice(invDate, new Date());
		
		List<PurchaseEntity> pList = pDao.getListPurchase(invDate, new Date());
		
		long startTime3 = System.currentTimeMillis();
		if (iList!=null)			
			for (int i =0; i<iList.size();i++)
			{
				mpList.addAll(iDao.getListMeatPart(iList.get(i), mte));
			}
		
		if (pList!=null)
			for (int i=0; i<pList.size();i++)
			{
				mppList.addAll(pDao.getListMeatPartPurchase(pList.get(i), mte));
			}
		
		
		for (int i =0; i< mpList.size();i++)
			quantityOst -= mpList.get(i).getWeight();
		
		for (int i =0; i< mppList.size();i++)
			quantityOst += mppList.get(i).getWeight();
		
		return quantityOst;


	}

	public void setOstatkis(LazyDataModel<Ostatki> ostatkis) {
		this.ostatkis = ostatkis;
	}

	

	

}
