#include <stdio.h>

void helper(int n, char from, char aux, char to) {
    if (n == 0){
        return;
    }
    // Move top n-1 disks to auxiliary rod
    helper(n - 1, from, to, aux);
    // Move largest disk
    printf("Move Disk %d from %c to %c\n", n, from, to);
    // Move n-1 disks to destination rod
    helper(n - 1, aux, from, to);
}

int main() {
    helper(5, 'A', 'B', 'C');
    return 0;
}