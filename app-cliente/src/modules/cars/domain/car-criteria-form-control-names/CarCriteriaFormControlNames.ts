// Aqui se va a gestionar de manera centralizada tanto los form controls como sus relaciones con el criteria
// los filters (field, operator) y el order (type y by)

import { FormGroup } from "@angular/forms";
import { Filter, FilterField, FilterFieldValue, FilterOperator, FilterOperatorValue, FilterValue } from "../criteria/Criteria";

const fromFormControlToFilterMap: Map<string, { field: FilterFieldValue, operator: FilterOperatorValue }> = new Map([
    ["brand", {
      field : FilterFieldValue.BRAND,
      operator : FilterOperatorValue.EQUAL
    }],
    ["minprice", {
      field : FilterFieldValue.PRICE,
      operator : FilterOperatorValue.GREATER_THAN
    }],
]);

export async function createFilterFromFormControlData({brand , minprice}: {brand:string, minprice:string}) : Promise<Filter[]>{
    const filtersObj = {brand, minprice};
    const filters: Filter[] = [];
    for(const [key, value] of Object.entries(filtersObj)){
      if(key != 'ordertype'){
       filters.push(createFilter([key, value as string]));
      }
      }
    return filters;
}

function createFilter([key, value]: [string, string]): Filter{
    const mapEntry = fromFormControlToFilterMap.get(key);
    let filter!:Filter;
    if (mapEntry) {
        const { field, operator } = mapEntry;
        filter = {
                "field": field as unknown as FilterField,
                "operator": operator as unknown as FilterOperator,
                "value": value as unknown as FilterValue
        }
    }   
    return filter;
    
}