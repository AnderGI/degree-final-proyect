export interface Criteria {
    filters: Filter[];
    order: Order;
}

export interface Filter{
    field: FilterField;
	operator: FilterOperator;
	value: FilterValue;
}

export enum FilterFieldValue {
    BRAND = "brand",
    PRICE = "price"
}

export interface FilterField{
    value: FilterFieldValue;
}

export enum FilterOperatorValue {
	EQUAL = "=",
	NOT_EQUAL = "!=",
	GREATER_THAN = ">",
	LOWER_THAN = "<",
}

export interface FilterOperator{
    value: FilterOperatorValue; // deberia de ser un enum o algo por el estilo ocn constantes
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

export enum OrderTypeValue {
    ASC = "asc",
	DESC = "desc",
	NONE = "none",
}

export interface OrderType{
    value : OrderTypeValue; // habria que hacer lo mismo que con el filteroperator, hay constantes
}