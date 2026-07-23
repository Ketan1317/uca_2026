#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>


#define BUFFER_SIZE 1024

int main(int argc, char *argv[]) {
    int inputFd, outputFd, openFlags;
    char *cleanCode = "/mnt/c/Users/MANI COMPUTER LNG/Desktop/uca_2026/WeeklyAssignments/week4/removeComments/output.c";
    char buf[BUFFER_SIZE];
    mode_t filePerms;
    ssize_t numRead;

    if (argc < 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        exit(-1);
    }

    inputFd = open(argv[1], O_RDONLY);
    if (inputFd < 0) {
        printf("\nFailed to open: %s\n", argv[1]);
        exit(-1);
    }

    openFlags = O_CREAT | O_WRONLY | O_TRUNC;
    filePerms = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH; // rw-r--r--

    bool partOfString = false;

    outputFd = open(cleanCode, openFlags, filePerms);
    if (outputFd < 0) {
        printf("\nFailed to create output file!\n");
        close(inputFd);
        exit(-1);
    }

    while ((numRead = read(inputFd, buf, BUFFER_SIZE)) > 0) {

        for(int i = 0; i < numRead; i++) {
            if(buf[i] == '"'){
                partOfString = !partOfString;
                write(outputFd,&buf[i],1);
            }

            else if(!partOfString && buf[i] == '/' && i + 1 < numRead && buf[i+1] == '/') {
                // single line comment...
                while(i < numRead && buf[i] != '\n') {
                    i++;
                }
                if(i < numRead){
                    write(outputFd,&buf[i],1);
                }
            }

            else if(!partOfString && buf[i] == '/' && i+1 < numRead && buf[i+1] == '*'){
                // multiline comment...
                i++;
                while(i < numRead){
                    if(buf[i] == '\n'){
                        write(outputFd,&buf[i],1);
                    }
                    if(buf[i] == '*' && i + 1 < numRead && buf[i+1] == '/'){
                        i++;
                        break;
                    }
                    i++;
                }
                
            }

            else {
                if(write(outputFd, &buf[i], 1) != 1) {
                    close(inputFd);
                    close(outputFd);
                    printf("\nFailed to write into file!");
                    exit(-1);
                }
            }

        }
    }
    
    close(inputFd);
    close(outputFd)
    return 0;
}