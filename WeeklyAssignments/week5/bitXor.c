#include <stdio.h>
int bitXOR(int x, int y) {
    // ~(x & y) -> It means give 0 where both bits are 1
    // ~(~x & ~y) -> It means give 0 where both bits are 0
    return ~(x & y) & ~(~x & ~y);
}

int main(){
    int a = 4;
    int b = 5;
    printf("Answer with ^ operator: %d\n", (a ^ b));
    printf("Answer without ^ operator: %d\n", bitXOR(a, b));
    return 0;
}



