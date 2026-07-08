#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define LOGFILE "audit.log"

// To write line numbers
void writeNumber(int n) {
    char buf[20];
    int i = 0;
    if (n == 0) {
        char c = '0';
        write(1, &c, 1);
        return;
    }

    while (n > 0) {
        buf[i++] = (n % 10) + '0';
        n /= 10;
    }
    while (i--)
        write(1, &buf[i], 1);
}

int main(int argc, char *argv[]) {

    if (argc < 2)
        return 1;

    if (strcmp(argv[1], "--add") == 0) {
        if (argc < 3){
            printf("Usage ./a.out --add Log Data");
		    exit(-1);
        }

        int fd = open(LOGFILE, O_WRONLY | O_CREAT | O_APPEND, 0644);
        write(fd, argv[2], strlen(argv[2]));
        write(fd, "\n", 1);
        close(fd);
    }

    else if (strcmp(argv[1], "--view") == 0) {

        int fd = open(LOGFILE, O_RDONLY);
        if (fd < 0){
            printf("Usage ./a.out --view ");
		    exit(-1);
        }
            
        char ch;
        int line = 1;

        writeNumber(line);
        write(1, ": ", 2);

        while (read(fd, &ch, 1) > 0) {
            write(1, &ch, 1);
            if (ch == '\n') {
                line++;
                writeNumber(line);
                write(1, ": ", 2);
            }
        }
        close(fd);
    }

    return 0;
}