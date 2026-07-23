#include <stdio.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <time.h>


int main(int argc,char *argv[]){
    if(argc < 2){
        printf("Usage: %s <fileName | directory", argv[0]);
        exit(-1);
    }

    struct stat st;
    if(stat(argv[1],&st)==-1){
        printf("Failed to fetch info\n");
        exit(-1);
    }

    printf("=== File Attributes for: %s ===\n",argv[1]);

    if (S_ISREG(st.st_mode))
        printf("File Type :           Regular File\n");
    else if (S_ISDIR(st.st_mode))
        printf("File Type : Directory\n");

    printf("Permissions:          %04o\n", st.st_mode & 0777);
    printf("File Size:            %ld bytes\n", st.st_size);
    printf("Hard Links Count:     %ld\n", st.st_nlink);
    printf("Owner (UID):          %d\n", st.st_uid);
    printf("Group (GID):          %d\n", st.st_gid);
    printf("Last Access Time:     %s", ctime(&st.st_atime));
    printf("Last Modification:    %s", ctime(&st.st_mtime));
    printf("Status Change Time:   %s", ctime(&st.st_ctime));
    return 0;
}