import { FORM_CONTROL_NAMES } from "../car-criteria-form-control-names/CarCriteriaFormControlNames";
import { CriteriaJSON, Filter } from "./Criteria";

import { createFilterFromPrimitives } from "./createFilterFromPrimitives";

const htmlPropToFilter:Record<string, (value:string) => Filter > = {
    [FORM_CONTROL_NAMES.BRAND]: (value:string) => createFilterFromPrimitives('brand', '=', value),
    [FORM_CONTROL_NAMES.MIN_PRICE]: (value:string) => createFilterFromPrimitives('price', '>', value),
  }

export function createJsonCriteriaFromHtmlForm(form:HTMLFormElement):CriteriaJSON{
    // podemos iterar sobre una pareja de clave valor
    // key -> el valor del name de los elementos del formulario´
    // value -> el valor por defecto o el del usuario
    const formData = new FormData(form);
    const filters:Filter[] = [];
    let orderType = '';
    let orderBy = 'price';
    formData.forEach((value, key) => {
      const filterCreator = htmlPropToFilter[key];
      if(key === 'ordertype') orderType = (value as string)
      if (filterCreator) {
        // Crea el filtro y agrégalo a la lista
        filters.push(filterCreator(value as string));
      }
    })
    console.log({
      filters,
      orderType,
      orderBy
  })
    return {
        filters,
        orderType,
        orderBy
    }
}