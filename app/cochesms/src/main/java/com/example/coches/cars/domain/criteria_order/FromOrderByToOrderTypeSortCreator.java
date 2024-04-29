package com.example.coches.cars.domain.criteria_order;

import org.springframework.data.domain.Sort;

import com.example.coches.cars.domain.criteria.OrderType;

public interface FromOrderByToOrderTypeSortCreator {
	Sort createSortFormOrderByAndType(OrderType orderType);
}
