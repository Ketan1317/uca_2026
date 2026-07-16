#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void reverse(char *str){
    int length = strlen(str);
    int i = 0;
    int j = length-1;

    while(i<j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
        i++;
        j--;
    }
}

int main(int argc,char *argv[]){
    if(argc < 2){
        printf("Usage : %s <string> \n",argv[0]);
        exit(-1);
    }
    char *str = argv[1];
    reverse(str);
    printf("Reversed String: %s\n",str);
}