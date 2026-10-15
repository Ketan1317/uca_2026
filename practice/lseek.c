#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main(void) {
    int fd = open("sparse.dat",
                  O_WRONLY | O_CREAT | O_TRUNC,
                  0644);

    if (fd == -1) {
        perror("open");
        return 1;
    }

    if (write(fd, "A", 1) != 1) {
        perror("write A");
        close(fd);
        return 1;
    }

    if (lseek(fd, 1000, SEEK_SET) == (off_t)-1) {
        perror("lseek");
        close(fd);
        return 1;
    }

    if (write(fd, "B", 1) != 1) {
        perror("write B");
        close(fd);
        return 1;
    }

    close(fd);
    return 0;
}