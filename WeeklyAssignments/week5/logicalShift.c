#include <stdio.h>
// Arithmetic >>
// sign bit = 0  → 0s enter from left
// sign bit = 1  → 1s enter from left
int logicalShift(int x,int n) {
    int mask = ~((1 << 31) >> (n-1));
    return (x >> n)&mask;
}

int main(){
    printf("0x%08X\n",logicalShift(0x87654321,4));
    // x>>4: 1111 1000 0111 0110 0101 0100 0011 0010
    // mask: 0000 1111 1111 1111 1111 1111 1111 1111
    // AND:  0000 1000 0111 0110 0101 0100 0011 0010
    return 0;
}



