const worker = new Worker("./worker.js")

const calcSum = document.getElementById("totalSum");
const changeBg = document.getElementById("changeBg");

calcSum.addEventListener("click",() => {
    worker.postMessage("sum")
})

worker.onmessage = (msg) => {
    alert(msg.data)
}

changeBg.addEventListener("click",() => {
    if(document.body.style.background !== "red"){
        document.body.style.background = "red";
    }
    else{
        document.body.style.background = "white"
    }
})