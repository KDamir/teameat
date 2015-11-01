package kz.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;



import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import kz.app.Ostatki;
import kz.app.entity.LeavingsEntity;



/**
 * 
 * 
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a
 * database.
 */
public class LeavingsEntityDataModel extends LazyDataModel<LeavingsEntity> {

	private static final Logger logger = Logger.getLogger(LeavingsEntityDataModel.class.getName());
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<LeavingsEntity> datasource;

	public LeavingsEntityDataModel(List<LeavingsEntity> datasource) {
		this.datasource = datasource;
	}

	@Override
	public LeavingsEntity getRowData(String rowKey) {
		for (LeavingsEntity ost : datasource) {
			if (ost.getBarcode().toString().equals(rowKey)) {
				return ost;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(LeavingsEntity ost) {
		return ost.getBarcode();
	}

	@Override
	public List<LeavingsEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<LeavingsEntity> data = new ArrayList<>();

		//filter
		for (LeavingsEntity type : datasource) {
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
			Collections.sort(data, new LazySorterLeavingsEntity(sortField, sortOrder));
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
