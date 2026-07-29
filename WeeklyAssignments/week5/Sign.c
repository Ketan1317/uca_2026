#include <stdio.h>
int sign(int n) {
    int msb = (n >> 31) & 1;
    return msb;
}

int main(){
    int n = 0070;
    if(n == 0){
        printf("Number is 0");
        return 0;
    }
    int checkedSign = sign(n);
    if(checkedSign == 0){
       printf("Number is +ve"); 
    }
    else{
        printf("Number is -ve");
    }

    return 0;
}



