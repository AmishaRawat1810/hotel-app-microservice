const main = () => {

  const app = createApp();
  Deno.serve({ port: 1234 }, app.fetch);
}

main();