#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int my_strlen(const char *str){
    const char *ptr = str;
    while(*ptr != '\0'){
        ptr++;
    }
    return ptr - str;
}

int main(int argc,char *argv[]){
    if(argc < 2){
        printf("Usage : %s <string> \n",argv[0]);
        exit(-1);
    }
    char *str = argv[1];
    printf("Length of string is: %d\n",my_strlen(str));
}