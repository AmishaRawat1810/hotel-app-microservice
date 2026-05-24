import { initRedis } from "./config/redis_config.js";
import ReceiptGenerator from "./src/service/receipt_generator.js";

const main = async () => {
  const redis = await initRedis();
  const worker = new ReceiptGenerator(redis);
  startWorking(worker);
}

main();