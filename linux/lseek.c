#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int main(int argc, char *argv[]) {
    size_t len;
    off_t offset;
    int fd, openFlags;
    char *buf;
    ssize_t numRead, numWritten;
    mode_t filePerms;

    if (argc < 3) {
        printf("\nUsage: %s <file> {r<length>|w<string>|s<offset>...}\n", argv[0]);
        exit(-1);
    }

    openFlags = O_RDWR | O_CREAT;
    filePerms = S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH;

    fd = open(argv[1], openFlags, filePerms);
    if (fd < 0) {
        printf("\nFailed to open file: %s\n", argv[1]);
        exit(-1);
    }

    for (int i = 2; i < argc; i++) {
        switch (argv[i][0]) {
            case 'w':
                numWritten = write(fd, &argv[i][1], strlen(&argv[i][1]));

                if (numWritten == -1) {
                    printf("\nWrite operation failed.\n");
                    close(fd);
                    exit(-1);
                }
                printf("\nSuccessfully wrote %zd byte(s).\n", numWritten);
                break;

            case 'r':
                len = atoi(&argv[i][1]);
                buf = malloc(len);
                if (buf == NULL) {
                    printf("\nMemory allocation failed.\n");
                    close(fd);
                    exit(-1);
                }
                numRead = read(fd, buf, len);
                if (numRead == -1) {
                    printf("\nRead operation failed.\n");
                    free(buf);
                    close(fd);
                    exit(-1);
                }

                if (numRead == 0) {
                    printf("\nReached the end of the file (EOF).\n");
                    free(buf);
                    close(fd);
                    exit(-1);
                }
                printf("\nData read from file:\n");
                for (int j = 0; j < numRead; j++) {
                    printf("%c", (unsigned char)buf[j]);
                }
                printf("\nRead completed successfully.\n");

                free(buf);
                break;

            case 's':
                offset = atoi(&argv[i][1]);
                off_t pos = lseek(fd, offset, SEEK_SET);
                if (pos == -1) {
                    printf("\nSeek operation failed.\n");
                    close(fd);
                    exit(-1);
                }

                printf("\nSeek successful. Current file offset: %lld\n",
                       (long long)pos);
                break;

            default:
                printf("\nInvalid argument.\n");
                printf("Use one of the following:\n");
                printf("  w<string>  - Write a string to the file\n");
                printf("  r<length>  - Read specified number of bytes\n");
                printf("  s<offset>  - Move file offset using SEEK_SET\n");

                close(fd);
                exit(-1);
        }
    }

    close(fd);
    printf("\nProgram executed successfully.\n");
    return 0;
}