package kz.app.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import kz.app.dao.InventoryDao;
import kz.app.dao.MeatTypeDao;
import kz.app.entity.InventoryEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

//   экспорт excel данных в таблицу mysql
public class Parser {
	
	static public MeatTypesEntity type;
	static public MeatTypeDao typeDao;
	
	static public InventoryEntity inventory;
	static public InventoryDao inventoryDao;
	
	public static void parse_meatType(String name){
		
		InputStream in = null;
		HSSFWorkbook wb =null;
		try{
			in = new FileInputStream(name);
			wb = new HSSFWorkbook(in);			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		type = new MeatTypesEntity();
		typeDao     = new MeatTypeDao();
		while (it.hasNext()){
			Row row = it.next();
			Iterator<Cell> cells= row.cellIterator();
				
			Cell cell_barcode = row.getCell(0);
			Cell cell_name = row.getCell(1);
			Cell cell_price_std = row.getCell(2);
			Cell cell_reward = row.getCell(3);
			Cell cell_category_id = row.getCell(4);
			Cell cell_price_zakup = row.getCell(5);
				
				
				/*
				Cell cell_barcode = cells.next();
				Cell cell_name = cells.next();
				Cell cell_price_std = cells.next();
				Cell cell_reward = cells.next();
				Cell cell_category_id = cells.next();
				Cell cell_price_zakup = cells.next();        */
			
			Double cb =cell_barcode.getNumericCellValue();
			Double cc = cell_category_id.getNumericCellValue();
			
			if (!typeDao.existByBarcode(BigInteger.valueOf(cb.longValue())))
			{
				type.setBarcode(BigInteger.valueOf(cb.longValue()));	
				type.setName(cell_name.getStringCellValue());
				type.setPrice_std(cell_price_std.getNumericCellValue());
				type.setReward(cell_reward.getNumericCellValue());
				type.setCategoryId(new MeatCategoryEntity(cc.intValue()));
				type.setPrice_zakup(cell_price_zakup.getNumericCellValue());
				typeDao.saveType(type);
			
				 /*для создания след. объекта*/
		        type = new MeatTypesEntity();
			}
			
			
		}
		FacesMessage msg = null;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные типов товаров загружены",
                "Информация о новых типах сохранена в базе данных");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}

	public static void parse_inventory(String name){
		inventory = new InventoryEntity();
		inventoryDao     = new InventoryDao();
		
		// очитска старых данных
		inventoryDao.deleteInventory();
		
		InputStream in = null;
		HSSFWorkbook wb =null;
		try{
			in = new FileInputStream(name);
			wb = new HSSFWorkbook(in);			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		
		while (it.hasNext()){
			Row row = it.next();
			Iterator<Cell> cells= row.cellIterator();
				
			Cell cell_barcode = row.getCell(0);
			Cell cell_quantity = row.getCell(1);
			Cell cell_date = row.getCell(2);
			
			
			Double cb =cell_barcode.getNumericCellValue();
			InventoryEntity ie = inventoryDao.getByBarcode(BigInteger.valueOf(cb.longValue()));
			
			if (ie == null)
			{
				
				inventory.setBarcode(BigInteger.valueOf(cb.longValue()));					
				inventory.setQuantity(cell_quantity.getNumericCellValue());
				inventory.setDate(cell_date.getDateCellValue());
				
				inventoryDao.saveInventory(inventory);
							 
				inventory = new InventoryEntity();
			
			}
			else
			{
				ie.setQuantity(ie.getQuntity() + cell_quantity.getNumericCellValue());
				inventoryDao.saveInventory(ie);
			}
			
		}

		FacesMessage msg = null;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные инвентаризации загружены",
                "Информация о новых типах сохранена в базе данных");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
		return;
		
	}
}
