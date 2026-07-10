#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>


int main(int argc, char *argv[]) {
    if(argc < 2){
        printf("Usage: %s \"message\"\n", argv[0]);
        exit(-1);
    }
    write(1,argv[1],strlen(argv[1]));
    return 0;
}