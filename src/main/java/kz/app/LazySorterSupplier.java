package kz.app;

import java.lang.reflect.Field;
import java.util.Comparator;
import kz.app.entity.SupplierEntity;

import org.primefaces.model.SortOrder;

public class LazySorterSupplier implements Comparator<SupplierEntity> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorterSupplier(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(SupplierEntity car1, SupplierEntity car2) {
		try {
			Field field1 = SupplierEntity.class.getDeclaredField(this.sortField);
			field1.setAccessible(true);
			Object value1 = field1.get(car1);
			Object value2 = field1.get(car2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
}
