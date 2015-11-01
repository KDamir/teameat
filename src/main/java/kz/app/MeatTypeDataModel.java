package kz.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import kz.app.entity.MeatTypesEntity;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a
 * database.
 */
public class MeatTypeDataModel extends LazyDataModel<MeatTypesEntity> {

	private static final Logger logger = Logger.getLogger(MeatTypeDataModel.class.getName());
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<MeatTypesEntity> datasource;

	public MeatTypeDataModel(List<MeatTypesEntity> datasource) {
		this.datasource = datasource;
	}

	@Override
	public MeatTypesEntity getRowData(String rowKey) {
		for (MeatTypesEntity type : datasource) {
			if (type.getId().toString().equals(rowKey)) {
				return type;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(MeatTypesEntity type) {
		return type.getId();
	}

	@Override
	public List<MeatTypesEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<MeatTypesEntity> data = new ArrayList<>();

		//filter
		for (MeatTypesEntity type : datasource) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);

						Field privStrField = type.getClass().getDeclaredField(filterProperty);
						privStrField.setAccessible(true);
						String fieldValue = String.valueOf(privStrField.get(type));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
						match = false;
					}
				}
			} 

			if (match) {
				data.add(type);
			}
		}

		//sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		//rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		//paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}
