import { Filter, FilterField, FilterOperator, FilterValue } from "./Criteria";

export function createFilterFromPrimitives(field:string, operator:string, value:string):Filter{
    return {
        "field": field as unknown as FilterField,
        "operator": operator as unknown as FilterOperator,
        "value": value as unknown as FilterValue
    }
}