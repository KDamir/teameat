package kz.app;

import java.lang.reflect.Field;
import java.util.Comparator;




import kz.app.entity.LeavingsEntity;

import org.primefaces.model.SortOrder;

public class LazySorterLeavingsEntity implements Comparator<LeavingsEntity> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorterLeavingsEntity(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(LeavingsEntity car1, LeavingsEntity car2) {
		try {
			Field field1 = LeavingsEntity.class.getDeclaredField(this.sortField);
			field1.setAccessible(true);
			Object value1 = field1.get(car1);
			Object value2 = field1.get(car2);
				
			if (value1 == null && value2 != null){
				return 1;
			}
			if (value1 != null && value2 == null){	
				return -1;
			}
			if (value1 == null && value2 == null){	
				return 0;
			}
			
			int value = ((Comparable) value1).compareTo(value2);
			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
}
