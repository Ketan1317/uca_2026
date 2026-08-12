#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc,char* argv[]){
    int outputFd, openFlags;
	mode_t filePerms;
    if(argc < 2){
        printf("Usage %s file.txt",argv[0]);
        exit(-1);
    }

    filePerms = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH;
    openFlags = O_CREAT | O_EXCL | O_WRONLY;

    outputFd = open(argv[1],openFlags,filePerms);
    if(outputFd < 0){
        perror("open");
        exit(-1);
    }
    printf("%s created...\n",argv[1]);
    close(outputFd);
    return 0;


}