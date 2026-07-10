#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define LOGFILE "audit.log"
#define BUFFER_SIZE 1024

int main(int argc, char *argv[]) {
    int inputFd,outputFd,openFlags;
    char buffer[BUFFER_SIZE];
    ssize_t numRead;
    mode_t filePerms;


    if(argc < 2){
        printf("Usage: %s --add \"message\"\n", argv[0]);
        printf("       %s --view\n", argv[0]);
        exit(-1);
    }

    if(strcmp(argv[1],"--add") == 0){
        if(argc < 3){
            printf("Usage %s --add log data",argv[0]);
            exit(-1);
        }
        filePerms = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH;
        openFlags = O_CREAT | O_WRONLY | O_APPEND;
        outputFd = open(LOGFILE,openFlags,filePerms);
        if(outputFd < 0){
            printf("Error creating log file");
		    exit(-1);
        }

        write(outputFd,argv[2],strlen(argv[2]));
        write(outputFd,"\n",1);
        close(outputFd);
    }
    else if(strcmp(argv[1],"--view") == 0){
        inputFd = open(LOGFILE,O_RDONLY);
        if(inputFd < 0){
            printf("Usage ./a.out --view ");
		    exit(-1);
        }

        while((numRead = read(inputFd,buffer,BUFFER_SIZE)) > 0){
            for(int i=0;i<numRead;i++){
                if(buffer[i] == '\n'){
                    write(1,"\n",1);
                }
                else{
                    write(1,&buffer[i],1);
                }
            }
        }
        close(inputFd);
    }
    else{
        printf("Usage: %s --add \"message\"\n", argv[0]);
        printf("       %s --view\n", argv[0]);
        close(inputFd);    
        close(outputFd); 
        exit(-1);
    }

    close(inputFd);    
    close(outputFd);    

    return 0;
}