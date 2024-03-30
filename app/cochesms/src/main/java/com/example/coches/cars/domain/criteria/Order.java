package com.example.coches.cars.domain.criteria;

final public class Order {
	private OrderBy orderBy;
	private OrderType orderType;
	
	public Order(OrderBy orderBy, OrderType orderType) {
		this.orderBy = orderBy;
		this.orderType = orderType;
	}
	
	public static Order fromPrimitives(String orderBy, String orderType) {
		return new Order(
				new OrderBy(orderBy), 
				new OrderType(orderType == null ? OrderType.NONE : orderType)
		);
	}
}
