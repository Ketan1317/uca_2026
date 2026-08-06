// function cb(){
//     console.log("callback ran");
// }
// function sleep (ms) {
//     return new Promise((resolve) => setTimeout(()=>resolve(3), ms));
// }

// console.log( "Before sleep");
// sleep(3000).then((a)=>{
//     console.log("value returned" + a);
// });
// console.log("After sleep");
// // function sleep (ms) {
// //     return new Promise (resolve) = setTimeout (resolve, ms);
// // }
// // function demo () {
// //     while (true) {
// //     console.log ("Before")
// //     }
// // }
// // demo();


function test(){
    return new Promise((resolve)=>{
        resolve(100);
    })
}

test().then((a)=>{
    console.log(a)
})