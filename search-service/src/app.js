import { Hono } from "@hono/hono";
import { logger } from "@hono/hono/logger";
import { searchHotels, updateRoomAvailablity } from "./handlers/searchHandler.js";

const createApp = (searchService) => {
  const app = new Hono();

  app.use(async (ctx, next) => {
    logger();
    ctx.set('searchService', searchService);
    await next();
  });

  app.get("/api/search/hotels", searchHotels);
  app.get("/api/search/internal", updateRoomAvailablity);

  app.get('/', (ctx) => {
    return ctx.json({ 'msg': "hello" });
  })
  return app;
}

export default createApp;