package kz.app;

import java.lang.reflect.Field;
import java.util.Comparator;



import org.primefaces.model.SortOrder;

public class LazySorterOstatki implements Comparator<Ostatki> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorterOstatki(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Ostatki car1, Ostatki car2) {
		try {
			Field field1 = Ostatki.class.getDeclaredField(this.sortField);
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
