#include <stdio.h>
#include <string.h>

void reverse(char *str){
    int length = strlen(str);
    int i = 0;
    int j = length-1;

    while(i<j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
        i++;
        j--;
    }
}

int main(){
    char str[100];
    fgets(str,sizeof(str),stdin);
    reverse(str);
    printf("Reversed String: %s\n",str);
}