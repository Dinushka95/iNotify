const puppeteer = require("puppeteer");

var admin = require("firebase-admin");

var serviceAccount = require("./key.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});



var today = new Date();
var dd = String(today.getDate()).padStart(2, "0");
var mm = String(today.getMonth() + 1).padStart(2, "0");
var yyyy = today.getFullYear();

const date = mm + dd + yyyy;
const dbName = "topApplications";
const appcount = 30;


function sleep(millis) {
  return new Promise(resolve => setTimeout(resolve, millis));
}


 // GAMING 
// topselling_free apps
(async () => {

  var db = admin.firestore();

  const category = "GAMING";
  const collection = "topselling_free";  // topselling_free       topselling_paid      topgrossing
  const rank = "";
  const applicationName = "";
  const packageName = "";

  const browser = await puppeteer.launch({
    headless: true,
    slowMo: 0,
    devtools: false
  });

  const page = await browser.newPage();
  page.setExtraHTTPHeaders({ Cookie: "language=en" });
  page.setUserAgent(
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36"
  );

  await page.goto(
    `https://play.google.com/store/apps/category/${category}/collection/${collection}`,
    {
      waitUntil: "networkidle2",
      timeout: 0
    }
  );

  const resultPackageNames = await page.evaluate(() =>
    Array.from(
      document.querySelectorAll(
        'div[class="id-card-list card-list two-cards"] > div > div[class="card-content id-track-click id-track-impression"]'
      ),
      element => element.getAttribute("data-docid")
    )
  );

  const resultApplicationNames = await page.evaluate(() =>
    Array.from(
      document.querySelectorAll(
        'div[class="id-card-list card-list two-cards"] > div > div[class="card-content id-track-click id-track-impression"]'
      ),
      element =>
        element.textContent
          .replace("පෙර-ඇණවුම් කළ", "")
          .substring(
            0,
            element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
          )
          .trim()
          .substring(
            0,
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .indexOf("   ")
          )
          .substring(
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .substring(
                0,
                element.textContent
                  .replace("පෙර-ඇණවුම් කළ", "")
                  .substring(
                    0,
                    element.textContent
                      .replace("පෙර-ඇණවුම් කළ", "")
                      .indexOf("1 ")
                  )
                  .trim()
                  .indexOf("   ")
              )
              .indexOf(".") + 1,
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .substring(
                0,
                element.textContent
                  .replace("පෙර-ඇණවුම් කළ", "")
                  .substring(
                    0,
                    element.textContent
                      .replace("පෙර-ඇණවුම් කළ", "")
                      .indexOf("1 ")
                  )
                  .trim()
                  .indexOf("   ")
              ).length
          )
          .trim()
    )
  );

  var batch = db.batch();

  for (i = 0; i < appcount; i++) {
    var nycRef = db.collection(dbName).doc();

    var setAda = {
      date: date,
      category: category,
      collection: collection,
      packageName: resultPackageNames[i],
      applicationName: resultApplicationNames[i],
      rank: i
    };

    batch.set(nycRef, setAda);
  }

  batch.commit().then(function() {
    console.log(category +"-"+ collection+" - Data added successfully");
  });

  await browser.close();
  //await page.on('console', msg => console.log('PAGE LOG:', msg.text()));
  //await page.evaluate(() => console.log(`url is ${location.href}`));
})();

// GAMING 
// topselling_paid apps
(async () => {

  var db = admin.firestore();

  const category = "GAMING";
  const collection = "topselling_paid";  // topselling_free       topselling_paid      topgrossing
  const rank = "";
  const applicationName = "";
  const packageName = "";

  const browser = await puppeteer.launch({
    headless: true,
    slowMo: 0,
    devtools: false
  });

  const page = await browser.newPage();
  page.setExtraHTTPHeaders({ Cookie: "language=en" });
  page.setUserAgent(
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36"
  );

  await page.goto(
    `https://play.google.com/store/apps/category/${category}/collection/${collection}`,
    {
      waitUntil: "networkidle2",
      timeout: 0
    }
  );

  const resultPackageNames = await page.evaluate(() =>
    Array.from(
      document.querySelectorAll(
        'div[class="id-card-list card-list two-cards"] > div > div[class="card-content id-track-click id-track-impression"]'
      ),
      element => element.getAttribute("data-docid")
    )
  );

  const resultApplicationNames = await page.evaluate(() =>
    Array.from(
      document.querySelectorAll(
        'div[class="id-card-list card-list two-cards"] > div > div[class="card-content id-track-click id-track-impression"]'
      ),
      element =>
        element.textContent
          .replace("පෙර-ඇණවුම් කළ", "")
          .substring(
            0,
            element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
          )
          .trim()
          .substring(
            0,
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .indexOf("   ")
          )
          .substring(
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .substring(
                0,
                element.textContent
                  .replace("පෙර-ඇණවුම් කළ", "")
                  .substring(
                    0,
                    element.textContent
                      .replace("පෙර-ඇණවුම් කළ", "")
                      .indexOf("1 ")
                  )
                  .trim()
                  .indexOf("   ")
              )
              .indexOf(".") + 1,
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .substring(
                0,
                element.textContent
                  .replace("පෙර-ඇණවුම් කළ", "")
                  .substring(
                    0,
                    element.textContent
                      .replace("පෙර-ඇණවුම් කළ", "")
                      .indexOf("1 ")
                  )
                  .trim()
                  .indexOf("   ")
              ).length
          )
          .trim()
    )
  );

  var batch = db.batch();

  for (i = 0; i < appcount; i++) {
    var nycRef = db.collection(dbName).doc();

    var setAda = {
      date: date,
      category: category,
      collection: collection,
      packageName: resultPackageNames[i],
      applicationName: resultApplicationNames[i],
      rank: i
    };

    batch.set(nycRef, setAda);
  }

  batch.commit().then(function() {
    console.log(category +"-"+ collection+" - Data added successfully");
  });

  await browser.close();
  //await page.on('console', msg => console.log('PAGE LOG:', msg.text()));
  //await page.evaluate(() => console.log(`url is ${location.href}`));
})();
 
// GAMING 
// top grossing apps
(async () => {

  var db = admin.firestore();

  const category = "GAMING";
  const collection = "topgrossing";  // topselling_free       topselling_paid      topgrossing
  const rank = "";
  const applicationName = "";
  const packageName = "";

  const browser = await puppeteer.launch({
    headless: true,
    slowMo: 0,
    devtools: false
  });

  const page = await browser.newPage();
  page.setExtraHTTPHeaders({ Cookie: "language=en" });
  page.setUserAgent(
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36"
  );

  await page.goto(
    `https://play.google.com/store/apps/category/${category}/collection/${collection}`,
    {
      waitUntil: "networkidle2",
      timeout: 0
    }
  );

  const resultPackageNames = await page.evaluate(() =>
    Array.from(
      document.querySelectorAll(
        'div[class="id-card-list card-list two-cards"] > div > div[class="card-content id-track-click id-track-impression"]'
      ),
      element => element.getAttribute("data-docid")
    )
  );

  const resultApplicationNames = await page.evaluate(() =>
    Array.from(
      document.querySelectorAll(
        'div[class="id-card-list card-list two-cards"] > div > div[class="card-content id-track-click id-track-impression"]'
      ),
      element =>
        element.textContent
          .replace("පෙර-ඇණවුම් කළ", "")
          .substring(
            0,
            element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
          )
          .trim()
          .substring(
            0,
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .indexOf("   ")
          )
          .substring(
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .substring(
                0,
                element.textContent
                  .replace("පෙර-ඇණවුම් කළ", "")
                  .substring(
                    0,
                    element.textContent
                      .replace("පෙර-ඇණවුම් කළ", "")
                      .indexOf("1 ")
                  )
                  .trim()
                  .indexOf("   ")
              )
              .indexOf(".") + 1,
            element.textContent
              .replace("පෙර-ඇණවුම් කළ", "")
              .substring(
                0,
                element.textContent.replace("පෙර-ඇණවුම් කළ", "").indexOf("1 ")
              )
              .trim()
              .substring(
                0,
                element.textContent
                  .replace("පෙර-ඇණවුම් කළ", "")
                  .substring(
                    0,
                    element.textContent
                      .replace("පෙර-ඇණවුම් කළ", "")
                      .indexOf("1 ")
                  )
                  .trim()
                  .indexOf("   ")
              ).length
          )
          .trim()
    )
  );

  var batch = db.batch();

  for (i = 0; i < appcount; i++) {
    var nycRef = db.collection(dbName).doc();
    var setAda = {
      date: date,
      category: category,
      collection: collection,
      packageName: resultPackageNames[i],
      applicationName: resultApplicationNames[i],
      rank: i
    };

    batch.set(nycRef, setAda);
  }

  batch.commit().then(function() {
    console.log(category +"-"+ collection+" - Data added successfully");
  });

  await browser.close();
  //await page.on('console', msg => console.log('PAGE LOG:', msg.text()));
  //await page.evaluate(() => console.log(`url is ${location.href}`));
})();
