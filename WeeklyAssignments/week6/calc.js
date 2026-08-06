function* calculator(start) {
  let init = start;
  while (true) {
    const inp = yield init;
    let op = inp.operation;
    let val = inp.value;
    switch (op) {
      case "add":
        init += val;
        break;
      case "sub":
        init -= val;
        break;
      case "div":
        init = init / val;
        break;
      case "mul":
        init *= val;
        break;
      default:
        break;
    }
  }
}

const calc = calculator(50);
console.log(calc.next()); // 50

console.log(calc.next({ operation: "add", value: 30 }));
console.log(calc.next({ operation: "mul", value: 2 }));
console.log(calc.next({ operation: "sub", value: 20 }));
