#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <ctype.h>
#include <fcntl.h>

#define BUFFER_SIZE 1024

int isNum(char *str) {
    if (*str == '\0')
        return 0;

    while (*str) {
        if (!isdigit((unsigned char)*str))
            return 0;
        str++;
    }
    return 1;
}

int main(int argc, char *argv[]) {
    int fd;
    int maxi = 10;
    char *fName;
    char buffer[BUFFER_SIZE];

    ssize_t n;
    int lines = 0;

    if (argc < 2) {
        printf("Usage:\n");
        printf("  %s file.txt\n", argv[0]);
        printf("  %s number file.txt\n", argv[0]);
        exit(-1);
    }

    if (isNum(argv[1])) {
        if (argc < 3) {
            printf("Usage: %s number file.txt\n", argv[0]);
            exit(-1);
        }
        maxi = atoi(argv[1]);
        fName = argv[2];
    } else {
        fName = argv[1];
    }

    fd = open(fName, O_RDONLY);
    if (fd == -1) {
        perror("open");
        exit(-1);
    }
    while ((n = read(fd, buffer, BUFFER_SIZE)) > 0) {
        for (int i = 0; i < n; i++) {
            write(1, &buffer[i], 1);
            if (buffer[i] == '\n') {
                lines++;
                if (lines == maxi) {
                    close(fd);
                    exit(-1);
                }
            }
        }
    }

    close(fd);
    return 0;
}