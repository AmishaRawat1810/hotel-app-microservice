import { MongoClient } from "@db/mongo";

export const initDb = async () => {
  const uri = Deno.env.get("MONGO_SEARCH_URI") || "mongodb://localhost:27017";
  const client = new MongoClient();
  await client.connect(uri);

  console.log("Connected to Search MongoDB via Deno Driver");
  const db = client.database("searchDb");
  return db;
}
