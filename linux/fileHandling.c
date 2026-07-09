#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    FILE *fp = fopen("/mnt/c/Users/MANI COMPUTER LNG/Desktop/uca_2026/linux/audit.log","r");
    fscanf(fp,"%d",&x);
}

// +------------------+
// | Stack            |
// | Local Variables  |
// +------------------+

// ↓

// Heap
// malloc()

// ↓

// Data Segment
// Global & static Variables

// ↓

// Text Segment
// Program Code (Compiled machine instructions)