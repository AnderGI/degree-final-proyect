import { getDOMSelectors } from "../../Domain/Utils/DomSelectors.js";

const sharedDOMSelectors = getDOMSelectors({
  ALL_CARS_LIST_DOM_SELECTOR: "div.container-fluid.listado article.anuncio",
  CAR_TITLE_DOM_SELECTOR_QUERY_FROM_CAR: "div.contenido-anuncio h2",
  CAR_PRICE_DOM_SELECTOR_QUERY_FROM_CAR: "p.precio > span",
  CAR_DESCRIPTION_DOM_SELECTOR_QUERY_FROM_CAR: "div.contenido-anuncio ul",
  CAR_ANNOUNCMENT_DOM_SELECTOR_FROM_CAR: "a",
  CAR_IMAGE_URL_DOM_SELECTOR_QUERY_FROM_CAR: "a figure picture source",
});

export const AutoocasionDomSelectors = {
  ...sharedDOMSelectors,
};
