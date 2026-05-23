import { Hono } from "hono";
export const createApp = () => {
  const app = new Hono();
  app.get("/", (c) => c.text("hello"));
  return app;
}