import createApp from "./src/app.js";
import { initDb } from "./src/repository/initSearchRepository.js";
import SearchRepository from "./src/repository/SearchRepository.js";
import SearchService from "./src/service/SearchService.js";

const main = async () => {
  const db = await initDb();
  const searchRepository = new SearchRepository(db);
  const searchService = new SearchService(searchRepository);
  const app = createApp(searchService);
  const port = 3000;
  console.log(`Search service running on port ${port}`);
  Deno.serve({ port }, app.fetch);
}

main();