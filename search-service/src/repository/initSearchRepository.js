import { MongoClient } from "@db/mongo";
import { connect } from "@db/redis";

export const initMongo = async () => {
  const uri = Deno.env.get("MONGO_SEARCH_URI") || "mongodb://localhost:27017";
  const client = new MongoClient();
  await client.connect(uri);

  console.log("Connected to Search MongoDB via Deno Driver");
  const db = client.database("searchDb");
  return db;
}

export const initRedis = async () => {
  const redisUri = Deno.env.get("REDIS_URI") || "redis://localhost:6379";
  const url = new URL(redisUri);
  const client = await connect({
    hostname: url.hostname,
    port: Number(url.port) || 6379,
  });

  console.log("Connected to Search RedisDb via Deno Driver");
  return client;
}