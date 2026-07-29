#include <stdio.h>
int conditional(int x, int y, int z) {
    int mask = ~!!x + 1;
    return (mask & y) | (~mask & z);
}

int main(){
    printf("%d\n",conditional(0,5,6));
    return 0;
}