package kz.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import kz.app.entity.SupplierEntity;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a
 * database.
 */
public class SupplierDataModel extends LazyDataModel<SupplierEntity> {

	private static final Logger logger = Logger.getLogger(SupplierDataModel.class.getName());
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<SupplierEntity> datasource;

	public SupplierDataModel(List<SupplierEntity> datasource) {
		this.datasource = datasource;
	}

	@Override
	public SupplierEntity getRowData(String rowKey) {
		for (SupplierEntity sup : datasource) {
			if (sup.getId().toString().equals(rowKey)) {
				
				return sup;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(SupplierEntity sup) {
		return sup.getId();
	}

	@Override
	public List<SupplierEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<SupplierEntity> data = new ArrayList<>();

		//filter
		for (SupplierEntity sup : datasource) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);

						Field privStrField = sup.getClass().getDeclaredField(filterProperty);
						privStrField.setAccessible(true);
						String fieldValue = String.valueOf(privStrField.get(sup));

						if (filterValue == null || fieldValue.contains(filterValue.toString())) {
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
				data.add(sup);
			}
		}

		//sort
		if (sortField != null) {
			Collections.sort(data, new LazySorterSupplier(sortField, sortOrder));
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
