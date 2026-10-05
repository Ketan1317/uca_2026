#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void* foo(void* var) {
    int id = *(int*)var;
    printf("Thread ID: %d\n", id);
    return NULL;
}

int main() {
    const int NUM_THREADS = 4;
    pthread_t threads[NUM_THREADS];  // Each member represents a thread
    int thread_id[NUM_THREADS];      // Each thread is recommended to have a separate id

    for (int i = 0; i < NUM_THREADS; i++) {
        thread_id[i] = i;
        pthread_create(&threads[i], NULL, foo, &thread_id[i]);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        // void* res;
        pthread_join(threads[i], NULL);
    }
    printf("All threads have returned\n");
    return 0;
}