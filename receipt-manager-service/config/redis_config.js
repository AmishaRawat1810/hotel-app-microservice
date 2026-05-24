import { connect } from "@db/redis";

export const initRedis = async () => {
  const redisUri = Deno.env.get("REDIS_URI_FOR_PDF_GENERATORS") || "redis://localhost:6379";
  const url = new URL(redisUri);

  const client = await connect({
    hostname: url.hostname,
    port: Number(url.port) || 6379,
  });

  console.log("Connected to Search RedisDb via Deno Driver");
  return client;
};
