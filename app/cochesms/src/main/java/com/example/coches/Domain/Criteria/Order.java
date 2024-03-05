package com.example.coches.Domain.Criteria;

import com.example.coches.Domain.Criteria.ValueObjects.OrderBy;
import com.example.coches.Domain.Criteria.ValueObjects.OrderType;

final public class Order {
	private OrderBy orderBy;
	private OrderType orderType;
	
	public Order(OrderBy orderBy, OrderType orderType) {
		this.orderBy = orderBy;
		this.orderType = orderType;
	}
}
