/*
 * Linux Process Management - fork(), wait(), zombie, orphan, exec()
 *
 * This file contains small, independent examples of:
 *   1. fork() - create a child process
 *   2. PID / PPID and fork() return value
 *   3. Process memory after fork()
 *   4. Multiple fork()
 *   5. wait()
 *   6. waitpid()
 *   7. Zombie process
 *   8. Orphan process
 *   9. exec()
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

static void example_fork_basic(void);
static void example_pid_ppid(void);
static void example_memory(void);
static void example_multiple_fork(void);
static void example_wait(void);
static void example_waitpid(void);
static void example_zombie(void);
static void example_orphan(void);
static void example_exec(void);
static void example_fork_exec(void);

int main(void)
{
    int choice;

    while (1) {
        printf("1.  Basic fork()\n");
        printf("2.  PID / PPID / fork() return value\n");
        printf("3.  Process memory after fork()\n");
        printf("4.  Multiple fork()\n");
        printf("5.  wait()\n");
        printf("6.  waitpid()\n");
        printf("7.  Zombie process\n");
        printf("8.  Orphan process\n");
        printf("9.  exec()\n");
        printf("10. fork() + exec()\n");
        printf("0.  Exit\n");
        printf("========================================\n");
        printf("Enter choice: ");

        if (scanf("%d", &choice) != 1) {
            printf("Invalid input.\n");
            return 1;
        }

        switch (choice) {
            case 1:  example_fork_basic();  break;
            case 2:  example_pid_ppid();    break;
            case 3:  example_memory();      break;
            case 4:  example_multiple_fork(); break;
            case 5:  example_wait();        break;
            case 6:  example_waitpid();     break;
            case 7:  example_zombie();      break;
            case 8:  example_orphan();      break;
            case 9:  example_exec();        break;
            case 10: example_fork_exec();   break;
            case 0:  return 0;
            default: printf("Choose a number from 0 to 10.\n");
        }
    }
}

/*
 * 1. BASIC fork()
 *
 * fork() creates a new child process.
 * After fork(), BOTH parent and child continue from the next line.
 */
static void example_fork_basic(void)
{
    printf("\n--- Basic fork() ---\n");

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        return;
    }

    if (pid == 0) {
        printf("Child: I am the child process.\n");
    } else {
        printf("Parent: I am the parent process.\n");
    }

    printf("This line is executed by BOTH processes.\n");
}

/*
 * 2. PID / PPID / fork() RETURN VALUE
 *
 * Parent receives child's PID.
 * Child receives 0.
 * Failure returns -1.
 */
static void example_pid_ppid(void)
{
    printf("\n--- PID / PPID / fork() return value ---\n");

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        return;
    }

    if (pid == 0) {
        printf("\nCHILD PROCESS\n");
        printf("fork() returned: %d\n", pid);
        printf("My PID (getpid): %d\n", getpid());
        printf("My Parent PID (getppid): %d\n", getppid());
    } else {
        printf("\nPARENT PROCESS\n");
        printf("fork() returned: %d (child PID)\n", pid);
        printf("My PID (getpid): %d\n", getpid());
        printf("My Child PID: %d\n", pid);
    }
}

/*
 * 3. PROCESS MEMORY AFTER fork()
 *
 * The child gets a logically independent copy of the parent's
 * address space. Linux uses Copy-on-Write for efficiency.
 *
 * The variables below have separate values after fork().
 */
static void example_memory(void)
{
    printf("\n--- Process memory after fork() ---\n");

    int x = 10;
    printf("Before fork: x = %d\n", x);

    pid_t pid = fork();
    if (pid < 0) {
        perror("fork");
        return;
    }

    if (pid == 0) {
        x = 100;
        printf("Child:  x = %d, address of x = %p\n",
               x, (void *)&x);
    } else {
        x = 200;
        printf("Parent: x = %d, address of x = %p\n",
               x, (void *)&x);
    }

    /*
     * The virtual address can look the same in both processes,
     * but the processes have separate virtual address spaces.
     */
}

/*
 * 4. MULTIPLE fork()
 *
 * If every process reaches both fork() calls:
 *
 *     1 process
 *        |
 *      fork()
 *        |
 *     2 processes
 *        |
 *      fork()
 *        |
 *     4 processes
 */
static void example_multiple_fork(void)
{
    printf("\n--- Multiple fork() ---\n");

    pid_t p1 = fork();

    if (p1 < 0) {
        perror("fork");
        return;
    }

    pid_t p2 = fork();

    if (p2 < 0) {
        perror("fork");
        return;
    }

    printf("Hello from PID %d, PPID %d\n", getpid(), getppid());

    /*
     * Expected: 4 "Hello" lines.
     * Order is not guaranteed because the scheduler decides
     * which process runs first.
     */
}

/*
 * 5. wait()
 *
 * Parent waits until one of its children terminates.
 */
static void example_wait(void)
{
    printf("\n--- wait() ---\n");

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        return;
    }

    if (pid == 0) {
        printf("Child: working...\n");
        sleep(3);
        printf("Child: finished.\n");
        exit(42);
    }

    printf("Parent: waiting for child PID %d...\n", pid);

    int status;
    pid_t finished = wait(&status);

    if (finished == -1) {
        perror("wait");
        return;
    }

    printf("Parent: child %d has finished.\n", finished);

    if (WIFEXITED(status)) {
        printf("Child exit status = %d\n", WEXITSTATUS(status));
    }
}

/*
 * 6. waitpid()
 *
 * wait() can wait for any child.
 * waitpid() lets us specify which child to wait for.
 */
static void example_waitpid(void)
{
    printf("\n--- waitpid() ---\n");

    pid_t child1 = fork();

    if (child1 < 0) {
        perror("fork");
        return;
    }

    if (child1 == 0) {
        printf("Child 1: PID = %d\n", getpid());
        sleep(2);
        exit(11);
    }

    pid_t child2 = fork();

    if (child2 < 0) {
        perror("fork");
        return;
    }

    if (child2 == 0) {
        printf("Child 2: PID = %d\n", getpid());
        sleep(4);
        exit(22);
    }

    printf("Parent: child1 = %d, child2 = %d\n", child1, child2);

    /*
     * Specifically wait for child 2.
     */
    int status;
    waitpid(child2, &status, 0);

    if (WIFEXITED(status)) {
        printf("Parent: child 2 exited with status %d\n",
               WEXITSTATUS(status));
    }

    /*
     * Clean up child 1 as well.
     */
    waitpid(child1, NULL, 0);
}

/*
 * 7. ZOMBIE PROCESS
 *
 * Child exits first.
 * Parent stays alive and DOES NOT immediately call wait().
 *
 * During the sleep, the child is a zombie.
 *
 * You can inspect it from another terminal:
 *
 *     ps -o pid,ppid,state,cmd
 *
 * Look for state "Z".
 */
static void example_zombie(void)
{
    printf("\n--- Zombie process ---\n");

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        return;
    }

    if (pid == 0) {
        printf("Child: exiting now. PID = %d\n", getpid());
        exit(0);
    }

    printf("Parent: child PID = %d\n", pid);
    printf("Parent: sleeping for 10 seconds WITHOUT wait().\n");
    printf("During this time, the child can be observed as a zombie.\n");

    sleep(10);

    printf("Parent: now calling wait() to collect the child.\n");
    wait(NULL);
    printf("Zombie cleaned up.\n");
}

/*
 * 8. ORPHAN PROCESS
 *
 * Parent exits first.
 * Child continues running.
 *
 * Linux adopts the orphan under a system process (normally PID 1
 * in the usual Linux setup).
 */
static void example_orphan(void)
{
    printf("\n--- Orphan process ---\n");

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        return;
    }

    if (pid == 0) {
        printf("Child: PID = %d\n", getpid());
        printf("Child: original parent PID = %d\n", getppid());

        sleep(5);

        printf("Child: original parent has exited.\n");
        printf("Child: new Parent PID = %d\n", getppid());
    } else {
        printf("Parent: PID = %d\n", getpid());
        printf("Parent: exiting immediately.\n");
        exit(0);
    }
}

/*
 * 9. exec()
 *
 * exec() does NOT create a new process.
 * It replaces the current program with another program.
 *
 * If exec succeeds, code after exec() is NOT executed.
 */
static void example_exec(void)
{
    printf("\n--- exec() ---\n");

    printf("Before exec: PID = %d\n", getpid());
    printf("Replacing this program with 'ls'.\n");

    execlp("ls", "ls", "-l", NULL);

    /*
     * Reaching this line means exec failed.
     */
    perror("execlp");
}
