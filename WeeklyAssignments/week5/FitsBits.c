#include <stdio.h>
int fitsBits(int x, int n) {
    // - = 2's complement
    int shift = 32 + (~n + 1); // 32 - n
    int restored = (x << shift) >> shift;

    return (restored ^ x) == 0 ? 1 : 0;
}

int main(){
    int res = fitsBits(-6, 3);

    if (res == 0) {
        printf("Does not fit");
    } else {
        printf("Fits");
    }
    return 0;
}



