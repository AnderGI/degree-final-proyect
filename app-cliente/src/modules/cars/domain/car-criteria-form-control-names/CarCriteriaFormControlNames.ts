// Aqui se va a gestionar de manera centralizada tanto los form controls como sus relaciones con el criteria
// los filters (field, operator) y el order (type y by)

import { FormGroup } from "@angular/forms";
import { Filter, FilterFieldValue, FilterOperatorValue } from "../criteria/Criteria";

const formControlNames = {
    BRAND : "brand",
    MIN_PRICE : "minprice"
  }
const fromFormControlToFilterMap: Map<string, { field: FilterFieldValue, operator: FilterOperatorValue }> = new Map([
    [formControlNames.BRAND, {
      field : FilterFieldValue.BRAND,
      operator : FilterOperatorValue.EQUAL
    }],
    [formControlNames.MIN_PRICE, {
      field : FilterFieldValue.PRICE,
      operator : FilterOperatorValue.GREATER_THAN
    }],
]);

export async function createFilterFromFormControlData(filtro:FormGroup){
    const filtroRawValue = filtro.getRawValue()
    /*
    const filters = [
        {
          "field":"brand",
          "operator":"=",
          "value":brand.value
        },
        {
          "field":"price",
          "operator":">",
          "value":parseInt(minprice.value)
        }
        ]
        */
       const filters = [];
    // objeto con key del formControl -> relacion con formControlNames
    // y el valor que el usuario haya puesto
    for(const [key, value] of Object.entries(filtroRawValue)){
        const mapEntry = fromFormControlToFilterMap.get(key);
        if (mapEntry) {
            const { field, operator } = mapEntry;
            filters.push({
                "field": field,
                "operator": operator,
                "value": value
            });
        }
    }
    console.log(filters);
    
}

