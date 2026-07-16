#include <stdio.h>
#include <string.h>

int my_strlen(const char *str){
    const char *ptr = str;
    while(*ptr != '\0'){
        ptr++;
    }
    return ptr - str;
}

int main(){
    char str[100];
    // scanf("%s",str);
    fgets(str,sizeof(str),stdin);

    printf("Length of string is: %d\n",my_strlen(str));
}