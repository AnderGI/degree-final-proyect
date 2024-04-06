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

export async function createFilterFromFormControlData(filtro:FormGroup) : Promise<Filter[]>{
    const filtroRawValue = filtro.getRawValue() // {brand:'', minprice:''}
    const filters: Filter[] = [];
    // objeto con key del formControl -> relacion con formControlNames
    // y el valor que el usuario haya puesto
    for(const [key, value] of Object.entries(filtroRawValue)){
       filters.push(createFilter([key, value as string]));
    }
   console.log(filters)
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
    console.log(filter)
    return filter;
    
}