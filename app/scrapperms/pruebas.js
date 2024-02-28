import puppeteer from "puppeteer";
import crypto from "crypto";
const SOUL_AUTO_URL_MAIN_PAGE = "https://soulauto.com/";
const SOUL_AUTO_MAIN_PAGE_CAR_DOM_SELECTOR =
  "div#subastas-list > div.et_pb_code_inner > div.facetwp-template > div.woocommerce > ul.products.columns-3 > li";

async function getInformationFromData() {
  const browser = await puppeteer.launch({
    headless: "new",
  });

  const page = await browser.newPage();

  await page.goto(SOUL_AUTO_URL_MAIN_PAGE);

  const data = await evaluatePageFromSelector(page);
  console.log(data);
  await page.close();
}

getInformationFromData();

async function evaluatePageFromSelector(page) {
  return await page.evaluate((selector) => {
    const data = [];
    const carsList = [...document.querySelectorAll(selector)];

    for (const car of carsList) {
      const description = car.querySelector(
        `div[itemprop="description"] > p`
      ).innerText;
      const imageURL = car.querySelector("a").href;
      const title = car.querySelector("h2").innerText;
      const currentBet = car.querySelector(
        "span.price > span.woocommerce-Price-amount.amount>bdi"
      ).innerText;
      const reservePrice =
        car.querySelector("div.reserve_super").attributes.reserve_price.value;
      const betAmount = car.querySelector(
        "div.listingpujas.pujas > div.listing__statBids.currentbid"
      ).innerText;
      data.push({
        id: crypto.randomUUID(),
        title,
        description,
        imageURL,
        source: "https://soulauto.com/",
        currentBet,
        betAmount,
        reservePrice,
      });
    }

    return data;
  }, SOUL_AUTO_MAIN_PAGE_CAR_DOM_SELECTOR);
}
