package kz.app.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import kz.app.dao.GoodsDao;
import kz.app.dao.GoodsSupDao;
import kz.app.dao.InventoryDao;
import kz.app.dao.MeatCategoryDao;
import kz.app.dao.MeatTypeDao;
import kz.app.dao.ReceiverDao;
import kz.app.dao.SupplierDao;
import kz.app.entity.GoodsEntity;
import kz.app.entity.GoodsSupEntity;
import kz.app.entity.InventoryEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.SupplierEntity;

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
			

			type = new MeatTypesEntity();
			typeDao     = new MeatTypeDao();
			if (!typeDao.existByBarcode(BigInteger.valueOf(cb.longValue()))) {
				FacesMessage msg = null;
		        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Не существует товара со штрих-кодом " + cb.longValue(),
		                "Информация о новых типах сохранена в базе данных");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        continue;
			}
				
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
	
	public static void parse_exportmeatType(String name){
		FileOutputStream out = null;
		HSSFWorkbook wb =null;
		File fileout =new File(name);
		if (fileout.exists())
			fileout.delete();
		try{
			out = new FileOutputStream(name);
			wb = new HSSFWorkbook();			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		Sheet sheet = wb.createSheet("Sheet 1");
		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Штрих-код");
		row1.createCell(1).setCellValue("Наименование");
		row1.createCell(2).setCellValue("Цена");
		row1.createCell(3).setCellValue("Вознаграждение");
		row1.createCell(4).setCellValue("Категория");
		row1.createCell(5).setCellValue("Цена закупа");
		typeDao     = new MeatTypeDao();
		List<MeatTypesEntity> lis = typeDao.getListMeatType();
		//ListIterator <MeatTypesEntity> it=lis.listIterator();
		short a=1;
		for (MeatTypesEntity typ:lis) {
			//type =it.next();
			Row row = sheet.createRow(a);
			if (typ.getBarcode()!=null)
				row.createCell(0).setCellValue(typ.getBarcode().doubleValue());
			row.createCell(1).setCellValue(typ.getName());
			if (typ.getPrice_std()!=null)
				row.createCell(2).setCellValue(typ.getPrice_std());
			if (typ.getReward()!=null)
				row.createCell(3).setCellValue(typ.getReward());
			row.createCell(4).setCellValue(typ.getCategoryId().getName());
			if (typ.getPrice_zakup()!=null)
				row.createCell(5).setCellValue(typ.getPrice_zakup());
			a++;
		}
		try {
			wb.write(out);
			out.flush();
			out.close();
			System.out.println(name + " is successfully written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = null;
	    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные типов товаров выгружены",
	            "Информация о новых типах сохранена в файле export.xls");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}
	
	
	
	public static void parse_exportGoodsG(String name){

		FileOutputStream out = null;
		HSSFWorkbook wb =null;
		File fileout =new File(name);
		if (fileout.exists())
			fileout.delete();
		try{
			out = new FileOutputStream(name);
			wb = new HSSFWorkbook();			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		Sheet sheet = wb.createSheet("Sheet 1");
		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Категория");
		row1.createCell(1).setCellValue("Наименование");
		row1.createCell(2).setCellValue("Вес");
		row1.createCell(3).setCellValue("Цена");
		row1.createCell(4).setCellValue("Продавец");
		row1.createCell(5).setCellValue("Покупатель");
		row1.createCell(6).setCellValue("Дата продажи");
		row1.createCell(7).setCellValue("Сумма");
		GoodsDao goodsDao = new GoodsDao();
		List<GoodsEntity> goodsList = goodsDao.getListGoodsFull();
		//ListIterator <MeatTypesEntity> it=lis.listIterator();
		short a=1;

		for (GoodsEntity typ:goodsList) {
			//type =it.next();
			Row row = sheet.createRow(a);
			row.createCell(0).setCellValue(typ.getCategory());
			row.createCell(1).setCellValue(typ.getType());
			row.createCell(2).setCellValue(typ.getWeight());
			row.createCell(3).setCellValue(typ.getPrice());
			row.createCell(4).setCellValue(typ.getSender());
			row.createCell(5).setCellValue(typ.getCompany_name());
			row.createCell(6).setCellValue(typ.getDate());
			row.createCell(7).setCellValue(typ.getSum());
			a++;
		}
		try {
			wb.write(out);
			out.flush();
			out.close();
			System.out.println(name + " is successfully written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = null;
	    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные о продажах выгружены",
	            "Информация о новых типах сохранена в файле export.xls");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}
	
	public static void parse_exportGoodsSup(String name){

		FileOutputStream out = null;
		HSSFWorkbook wb =null;
		File fileout =new File(name);
		if (fileout.exists())
			fileout.delete();
		try{
			out = new FileOutputStream(name);
			wb = new HSSFWorkbook();			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		Sheet sheet = wb.createSheet("Sheet 1");
		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Категория");
		row1.createCell(1).setCellValue("Наименование");
		row1.createCell(2).setCellValue("Вес");
		row1.createCell(3).setCellValue("Цена");
		row1.createCell(4).setCellValue("Получатель");
		row1.createCell(5).setCellValue("Поставщик");
		row1.createCell(6).setCellValue("Дата покупки");
		row1.createCell(7).setCellValue("Сумма");
		GoodsSupDao goodsDao = new GoodsSupDao();
		List <GoodsSupEntity> goodsSupList = goodsDao.getListGoodsSup();
		//ListIterator <MeatTypesEntity> it=lis.listIterator();
		short a=1;

		for (GoodsSupEntity typ:goodsSupList) {
			//type =it.next();
			Row row = sheet.createRow(a);
			row.createCell(0).setCellValue(typ.getCategory());
			row.createCell(1).setCellValue(typ.getType());
			row.createCell(2).setCellValue(typ.getWeight());
			if (typ.getPrice()!=null)
				row.createCell(3).setCellValue(typ.getPrice());
			if (typ.getReceiver()!=null)
				row.createCell(4).setCellValue(typ.getReceiver());
			row.createCell(5).setCellValue(typ.getCompany_name());
			if (typ.getDate()!=null)
				row.createCell(6).setCellValue(typ.getDate());
			if (typ.getSum()!=null)
				row.createCell(7).setCellValue(typ.getSum());
			a++;
		}
		try {
			wb.write(out);
			out.flush();
			out.close();
			System.out.println(name + " is successfully written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = null;
	    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные о покупках выгружены",
	            "Информация о новых типах сохранена в файле export.xls");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}
	
	public static void parse_exportCategory(String name){

		FileOutputStream out = null;
		HSSFWorkbook wb =null;
		File fileout =new File(name);
		if (fileout.exists())
			fileout.delete();
		try{
			out = new FileOutputStream(name);
			wb = new HSSFWorkbook();			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		Sheet sheet = wb.createSheet("Sheet 1");
		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Категория");
		MeatCategoryDao goodsDao = new MeatCategoryDao();
		List <MeatCategoryEntity> categoryList = goodsDao.getListCategory();
		//ListIterator <MeatTypesEntity> it=lis.listIterator();
		short a=1;

		for (MeatCategoryEntity typ:categoryList) {
			//type =it.next();
			Row row = sheet.createRow(a);
			row.createCell(0).setCellValue(typ.getName());
			a++;
		}
		try {
			wb.write(out);
			out.flush();
			out.close();
			System.out.println(name + " is successfully written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = null;
	    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные о категориях выгружены",
	            "Информация о новых типах сохранена в файле export.xls");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}
	
	public static void parse_exportReceiver(String name){

		FileOutputStream out = null;
		HSSFWorkbook wb =null;
		File fileout =new File(name);
		if (fileout.exists())
			fileout.delete();
		try{
			out = new FileOutputStream(name);
			wb = new HSSFWorkbook();			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		Sheet sheet = wb.createSheet("Sheet 1");
		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Компания");
		row1.createCell(1).setCellValue("Вознаграждение");
		ReceiverDao goodsDao = new ReceiverDao();
		List <ReceiverEntity> goodsReceiverList = goodsDao.getListReceiver();
		//ListIterator <MeatTypesEntity> it=lis.listIterator();
		short a=1;

		for (ReceiverEntity typ:goodsReceiverList) {
			//type =it.next();
			Row row = sheet.createRow(a);
			row.createCell(0).setCellValue(typ.getCompanyName());
			row.createCell(1).setCellValue(typ.getReward());
			a++;
		}
		try {
			wb.write(out);
			out.flush();
			out.close();
			System.out.println(name + " is successfully written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = null;
	    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные о получателях выгружены",
	            "Информация о новых типах сохранена в файле export.xls");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}
	
	
	public static void parse_exportSupplier(String name){

		FileOutputStream out = null;
		HSSFWorkbook wb =null;
		File fileout =new File(name);
		if (fileout.exists())
			fileout.delete();
		try{
			out = new FileOutputStream(name);
			wb = new HSSFWorkbook();			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		Sheet sheet = wb.createSheet("Sheet 1");
		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Компания");
		row1.createCell(1).setCellValue("Адрес");
		row1.createCell(2).setCellValue("Телефон");
		row1.createCell(3).setCellValue("Заметка");
		SupplierDao goodsDao = new SupplierDao();
		List <SupplierEntity> goodsSupList = goodsDao.getListSupplier();
		//ListIterator <MeatTypesEntity> it=lis.listIterator();
		short a=1;

		for (SupplierEntity typ:goodsSupList) {
			//type =it.next();
			Row row = sheet.createRow(a);
			row.createCell(0).setCellValue(typ.getCompanyName());
			row.createCell(1).setCellValue(typ.getAddress());
			row.createCell(2).setCellValue(typ.getPhoneNumber());
			row.createCell(3).setCellValue(typ.getNote());
			a++;
		}
		try {
			wb.write(out);
			out.flush();
			out.close();
			System.out.println(name + " is successfully written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = null;
	    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Данные о поставщиках выгружены",
	            "Информация о новых типах сохранена в файле export.xls");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	}
}
