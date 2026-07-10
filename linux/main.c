#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define BUF_SIZE 1024
int main(int argc, char* argv[]){
	printf("%d\n", argc);
	printf("%s\n", argv[0]);
	if(argc != 3){
		printf("Usage ./a.out File1 File2");
		exit(-1);
		}
	return 0;
}
