import { MongoClient } from "mongodb";

export const intiDb = async () => {
  const uri = "mongodb://localhost:27017";
  const client = new MongoClient(uri);
  await client.connect();

  const db = client.db("searchDb");
  return db;
}