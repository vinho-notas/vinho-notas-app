const CACHE_NAME = "version-1";
const urlsToCache = [
  "/",
  "/home",
  "/registration",
  "/user-registration",
  "/users",
  "/persons",
  "/address",
  "/wine-registration",
  "/wine-list",
  "/wine-review-list",
  "/tasting-card",
  "/tasting-list",
  "/pairing-list",
  '/about',
  '/styles.css',
  '/script.js',
  '/images/logo.png'
];

const self = this;
self.addEventListener("install", (event) => {
  self.skipWaiting();
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log("Opened cache", CACHE_NAME);
      return cache.addAll(urlsToCache);
    })
  );
});

self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches.keys().then((cacheNames) => {
      return Promise.all(
        cacheNames.map((cacheName) => {
          if (cacheName !== CACHE_NAME) {
            return caches.delete(cacheName);
          }
        })
      );
    })
  );
});

if ("serviceWorker" in navigator) {
  window.addEventListener("load", () => {
    navigator.serviceWorker
      .register("./serviceworker.js")
      .then((reg) => {
        console.log(
          "Service Worker Registration Success: ",
          reg.scope
        ).catch((err) =>
          console.log(
            "Service Worker Registration Failure: ",
            err
          )
        );
      });
  }
  );
}