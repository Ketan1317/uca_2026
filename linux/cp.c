#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define BUF_SIZE 1024
// argc -> argument counter
// argv -> argument vector ... It is an array of pointers to characters (strings)
int main(int argc, char* argv[]){ // %s expects a pointer (char *) to the first character of a null-terminated string
	int inputFd,outputFd, openFlags;
	mode_t filePerms; // mode_t is an integer type used for File permissions
	ssize_t numRead; // Signed Size Type
	char buf[BUF_SIZE];
	
	if(argc != 3){
		printf("Usage ./a.out File1 File2");
		exit(-1);	
	}	
	// STDIN_FILENO   = 0
	// STDOUT_FILENO  = 1
	// STDERR_FILENO  = 2

	inputFd = open(argv[1],O_RDONLY);
	if(inputFd == -1){
		printf("Error opening the file %s\n",argv[1]);
		exit(-1);
	}

	openFlags = O_CREAT | O_WRONLY | O_TRUNC;
	filePerms = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH; // rw-r--r-- 644
	outputFd = open(argv[2],openFlags,filePerms);
	if(outputFd == -1){
		printf("Error opening the file %s\n",argv[2]);
		exit(-1);
	// use error number to check what was the actual error	
	}
	while((numRead = read(inputFd,buf,BUF_SIZE)) > 0){
		if(write(outputFd,buf,numRead) != numRead){
			printf("Some problem during reading or writing\n");
			exit(-1);
		}
		
	}
	close(inputFd);
	close(outputFd);
	return 0;
	// return 1; "Some error occurred."
}
