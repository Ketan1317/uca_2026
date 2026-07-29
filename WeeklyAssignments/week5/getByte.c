#include <stdio.h>
int getByte(int x,int n){
    // 32 bits = 4 bytes
    // 8,16,24,32
    // n * 2^k == n << k (2*8 = 2 << 3)
    int shift = n << 3;
    return (x >> shift) & 0xFF;
}
int main(){
    int num = getByte(0x12345678,1);
    printf("%d\n",num);
    return 0;
}