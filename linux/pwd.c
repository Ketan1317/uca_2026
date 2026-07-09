#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
    char cwd[1024];

    if (getcwd(cwd, sizeof(cwd)) == NULL) {
        perror("getcwd");
        exit(-1);
    }
    printf("%s\n", cwd);
    return 0;
}