#include <stdio.h>
// For a power of 2 ... x & (x-1) = 0 AND...
// 1. x is NOT zero
// 2. x has exactly one 1-bit
// 3. x is NOT negative
int powerOf2(int n){
    int nonZero = !!n; // (!!4 -> !0 -> 1)
    int powOf2 = !(n & (n + ~0)); // -1 = ~0
    int positive = !(n >> 31);

    return nonZero & powOf2 & positive;

}
int main(){
    printf("%d\n",powerOf2(0));
    return 0;
}