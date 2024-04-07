export interface CriteriaJSON {
    filters : Filter[],
    orderBy : string,
    orderType : string
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
