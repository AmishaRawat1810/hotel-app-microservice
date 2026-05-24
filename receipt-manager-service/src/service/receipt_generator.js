export default class ReceiptGenerator {
  #taskStore;

  constructor(redis) {
    this.#taskStore = redis;
  }

  works() {
    console.log("works fine");
  }

  #executeTask(task) {
    return "";
  }

  #savePdfUrl(pdfUrl) {
    fetch("http://localhost:8080/saveUrl", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ pdfUrl }),
    });
  }

  startWorking() {
    while (true) {
      const task = redis.brpop();
      const pdfUrl = this.#executeTask(task);
      this.#savePdfUrl(pdfUrl);
    }
  }
}
