FROM denoland/deno:alpine

WORKDIR /app

COPY deno.json .
COPY deno.lock .

COPY . .

RUN deno cache main.js

EXPOSE 3000

CMD ["task", "start"]
