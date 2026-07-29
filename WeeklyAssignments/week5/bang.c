#include <stdio.h>
// !0        = 1
// !anything = 0

int bang(int n){
    int neg = ~n+1;
    int msb = (neg | n) >> 31; // For any non-zero x, either x or -x has its sign bit set to 1
    return msb+1;
    // x = 0: 0(msb) + 1 = 1 
    // x != 0: -1(msb) + 1 = 0 
}
int main(){
    printf("%d\n",bang(0));
    return 0;
}