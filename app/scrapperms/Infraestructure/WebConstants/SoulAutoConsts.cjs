const { getDOMSelectors } = require("../../Domain/Utils/DomSelectors.cjs");

const CAR_OF_LIST_OF_CARDS_CAR_RESERVE_PRICE_SELECTOR = "div.reserve_super";
const CAR_OF_LIST_OF_CARDS_CAR_BET_AMOUNT_SELECTOR =
  "div.listingpujas.pujas > div.listing__statBids.currentbid";

const sharedDOMSelectors = getDOMSelectors({
  ALL_CARS_LIST_DOM_SELECTOR:
    "div#subastas-list > div.et_pb_code_inner > div.facetwp-template > div.woocommerce > ul.products.columns-3 > li",
  CAR_TITLE_DOM_SELECTOR_QUERY_FROM_CAR: "h2",
  CAR_PRICE_DOM_SELECTOR_QUERY_FROM_CAR:
    "span.price > span.woocommerce-Price-amount.amount>bdi",
  CAR_DESCRIPTION_DOM_SELECTOR_QUERY_FROM_CAR:
    "div[itemprop='description'] > p",
  CAR_ANNOUNCMENT_DOM_SELECTOR_FROM_CAR: "a",
  CAR_IMAGE_URL_DOM_SELECTOR_QUERY_FROM_CAR: "a span.et_shop_image img",
});

const SoulAutoDomSelectors = {
  ...sharedDOMSelectors,
  CAR_OF_LIST_OF_CARDS_CAR_RESERVE_PRICE_SELECTOR,
  CAR_OF_LIST_OF_CARDS_CAR_BET_AMOUNT_SELECTOR,
};

module.exports = { SoulAutoDomSelectors };
