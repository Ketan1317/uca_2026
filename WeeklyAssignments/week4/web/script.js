const { Worker } = require("worker_threads");
const worker = new Worker("./worker.js");

worker.postMessage({
  url: "https://dummyjson.com/users",
});

// Receive message from worker
worker.on("message", (users) => {
  if (users.error) {
    console.error("Worker error:", users.error);
    return;
  }

  users.forEach((user) => {
    console.log(`${user.firstName} ${user.lastName} - ${user.email}`);
  });
});

worker.on("error", (error) => {
  console.error("Worker Script Error:", error);
});