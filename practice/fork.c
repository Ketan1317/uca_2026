
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(void) {
    pid_t pid = fork();
    int x = 10;
    int *p = &x;
    printf("%d\n",x);
    printf("%p\n",&x);
    printf("%d\n",*p);

    if (pid < 0) {
        perror("fork");
        return 1;
    }

    if (pid == 0) {
        printf("I am the child process\n");
        printf("Child PID: %ld\n", (long)getpid());
        printf("Parent PID: %ld\n", (long)getppid());
    } else {
        printf("I am the parent process\n");
        printf("Parent PID: %ld\n", (long)getpid());
        printf("Child PID: %ld\n", (long)pid);

        wait(NULL);
    }

    return 0;
}
