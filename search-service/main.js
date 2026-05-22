import createApp from "./src/app.js";
import { intiDb } from "./src/repository/initSearchRepository.js";
import SearchRepository from "./src/repository/SearchRepository.js";
import SearchService from "./src/service/SearchService.js";

const main = async () => {
  const db = await intiDb();
  const searchRepository = new SearchRepository(db);
  const searchService = new SearchService(searchRepository);
  const app = createApp(searchService);

  Deno.serve({ port: 8080 }, app.fetch);
}

main();