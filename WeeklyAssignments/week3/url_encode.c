#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void shift(char *str,int s,int e){
    for(int i=e;i>=s;i--){
        str[i+2] = str[i];
    }
}

void url_encode(char *str,int len){
    int i = 0;
    while(i<len){
        char ch = str[i];
        if(ch == ' '){
            shift(str,i+1,len);
            str[i] = '%';
            str[i+1] = '2';
            str[i+2] = '0';
            len+=2;
            i+=3;
        }
        else{
            i++;
        }
    }
}


int main(int argc,char *argv[]){
    if(argc < 3){
        printf("Usage : %s <string> <true_length>\n",argv[0]);
        exit(-1);
    }

    char *str = argv[1];
    
    int len = atoi(argv[2]);
    url_encode(str,len);
    printf("String after Modification is: %s\n",str);

    return 0;
}