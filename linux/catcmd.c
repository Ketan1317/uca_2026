#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>

#define BUFFER_SIZE 1024

int main(int argc,char* argv[]){
    ssize_t numRead;
    char buffer[BUFFER_SIZE];
    if(argc < 2){
        printf("Usage: %s file.txt\n", argv[0]);
        exit(-1);
    }

    int fd = open(argv[1],O_RDONLY);
    if(fd == -1){
        printf("Error opening the file %s\n",argv[1]);
        exit(-1);
    }

    while((numRead = read(fd,buffer,BUFFER_SIZE)) > 0){
        if(write(1,buffer,numRead) != numRead){
            printf("Some problem during reading or writing\n");
            close(fd);
			exit(-1);
        }
    }

    close(fd);
    return 0;
}