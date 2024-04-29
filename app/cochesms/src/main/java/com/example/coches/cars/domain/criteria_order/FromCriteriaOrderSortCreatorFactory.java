package com.example.coches.cars.domain.criteria_order;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.data.domain.Sort;

import com.example.coches.cars.domain.criteria.Order;
import com.example.coches.cars.domain.criteria.OrderType;

final public class FromCriteriaOrderSortCreatorFactory {
	private Order order = null;
	private Map<String, FromOrderByToOrderTypeSortCreator> fromOrderByToSort = new TreeMap<>();
			
	public FromCriteriaOrderSortCreatorFactory(Order order) {
		this.order = order;
		fromOrderByToSort.put("price", new FromPriceToOrderTypeSortCreator(this.order.getOrderType()));
	}
	
	public Sort create_sort_from_criteria_order() {
		String orderByValue = this.order.getOrderBy().getOrderByValue();
		return fromOrderByToSort.get(orderByValue).createSortFormOrderByAndType(this.order.getOrderType());
	}
}
