#include <stdio.h>
int bitAndOp(int x, int y) {
    // ~(x&y) = ~x | ~y
    // ~(~(x&y)) = ~(~x | ~y)
    return ~(~x | ~y);
}

int main(){
    int a = 6;
    int b = 5;
    printf("Answer with & operator:%d\n", (a&b));
    printf("Answer without & operator:%d\n", bitAndOp(a, b));
    return 0;
}


