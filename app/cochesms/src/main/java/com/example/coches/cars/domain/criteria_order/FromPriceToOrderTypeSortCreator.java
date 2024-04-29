package com.example.coches.cars.domain.criteria_order;

import org.springframework.data.domain.Sort;

import com.example.coches.cars.domain.criteria.OrderType;

public class FromPriceToOrderTypeSortCreator implements FromOrderByToOrderTypeSortCreator {

	private OrderType orderType = null;
	public FromPriceToOrderTypeSortCreator(OrderType orderType) {
		this.orderType = orderType;
	}
	@Override
	public Sort createSortFormOrderByAndType(OrderType orderType) {
		// TODO Auto-generated method stub
		if(orderType.getOrderTypeValue().equalsIgnoreCase(OrderType.ASC))
			return Sort.by(Sort.Direction.ASC, "price.value");
		//}else if(orderType.getOrderTypeValue().equalsIgnoreCase(OrderType.DESC)) {
			return Sort.by(Sort.Direction.DESC, "price.value");
		//}
		
		//return null;
		
	}

}
