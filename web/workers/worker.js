self.onmessage = (message) => {
  console.log("Message" + message);
  if (message.data === "sum") {
    let ans = 0;
    for (let i = 1; i < 10000000000; i++) {
      ans++;
    }
    self.postMessage(ans);
  }
};
