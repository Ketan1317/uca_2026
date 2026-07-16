#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int helper(char *str,char *part){
    int idx = -1;
    int i=0;
    while(str[i] != '\0'){
        if(str[i] == part[0]){
            idx = i;
            int j = 0;
            while(part[j] != '\0' && str[idx] == part[j]){
                    idx++;
                    j++;
            }
            if(part[j] == '\0'){
                return i;
            }
        }
        i++;
    }
    return -1;
}

int main(int argc,char *argv[]){
    if(argc < 3){
        printf("Usage : %s <string> <substring>\n",argv[0]);
        exit(-1);
    }

    char *str = argv[1];
    char *part = argv[2];

    int idx = helper(str,part);
    if(idx == -1){
        printf("%s is not present in %s\n",part,str);
        return 0;
    }
    printf("Index of %s in %s is: %d\n",part,str,idx);
    return 0;
}