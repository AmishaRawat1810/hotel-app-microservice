import createApp from "./src/app.js";
import { initMongo, initRedis } from "./src/repository/initSearchRepository.js";
import SearchRepository from "./src/repository/SearchRepository.js";
import SearchService from "./src/service/SearchService.js";

const main = async () => {
  const searchMongoDb = await initMongo();
  const searchRedisDb = await initRedis();
  const searchRepository = new SearchRepository(searchMongoDb);
  const searchService = new SearchService(searchRepository, searchRedisDb);
  const app = createApp(searchService);
  const port = 3000;
  console.log(`Search service running on port ${port}`);
  Deno.serve({ port }, app.fetch);
}

main();