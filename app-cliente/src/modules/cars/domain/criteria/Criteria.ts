export interface Criteria {
    filters: Filter[];
    order: Order;
}

export interface Filter{
    field: FilterField;
	operator: FilterOperator;
	value: FilterValue;
}

export interface FilterField{
    value: string;
}

export interface FilterOperator{
    value: string; // deberia de ser un enum o algo por el estilo ocn constantes
}

export interface FilterValue{
    value: string;
}

export interface Order{
    orderBy: OrderBy;
	orderType: OrderType;
}

export interface OrderBy{
    value: string;
}

export interface OrderType{
    value : string; // habria que hacer lo mismo que con el filteroperator, hay constantes
}