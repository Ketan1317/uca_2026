
#include <stdio.h>
#include <pthread.h>

void *task(void *arg) {
    int id = *(int *)arg;
    printf("Thread %d is running\n", id);
    return NULL;
}

int main(void) {
    pthread_t thread;
    int id = 1;

    int result = pthread_create(
        &thread,
        NULL,
        task,
        &id
    );

    if (result != 0) {
        fprintf(stderr, "pthread_create failed: %d\n", result);
        return 1;
    }

    pthread_join(thread, NULL);

    printf("Main thread finished\n");
    return 0;
}
