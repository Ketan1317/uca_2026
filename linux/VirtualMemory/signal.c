#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void handler(int sig) {
    printf("SIGINT received!\n");
}

int main() {

    signal(SIGINT, handler);

    while (1) {
        printf("Running...\n");
        sleep(3); // Sleep for 1 second
    }

    return 0;
}