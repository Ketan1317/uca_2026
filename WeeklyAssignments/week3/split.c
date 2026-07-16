#include <stdio.h>
#include <string.h>

int main(){
    char str[] = "C,C++,Java,Python,Rust";
    char *split = strtok(str,",");

    while(split != NULL){
        printf("%s\n",split);
        split = strtok(NULL,","); // Continue from where you stopped.
    }
}