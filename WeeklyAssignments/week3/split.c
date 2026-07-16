#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc,char *argv[]){
    if(argc < 2){
        printf("Usage : %s <string> \n",argv[0]);
        exit(-1);
    }
    char *str = argv[1];
    char *split = strtok(str,",");

    while(split != NULL){
        printf("%s\n",split);
        split = strtok(NULL,","); // Continue from where you stopped.
    }
}