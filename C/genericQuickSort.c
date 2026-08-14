#include <stdio.h>
#include <string.h>
#include <stddef.h>

typedef struct {
    int id;
    char name[50];
    float marks;
} Student;
void swap(void *a,void *b,size_t size){
    char temp[size];

    memcpy(temp,a,size); 
    memcpy(a,b,size); 
    memcpy(b,temp,size); 
}

int compareInt(void *a,void* b){
    int x = *(int *)a;
    int y = *(int *)b;

    if(x > y){
        return 1;
    }
    else if(y > x) return -1;
    return 0;
}
int compareFloat(void *a,void* b){
    float x = *(float *)a;
    float y = *(float *)b;

    if(x > y){
        return 1;
    }
    else if(y > x) return -1;
    return 0;
}
int compareDouble(void *a,void* b){
    double x = *(double *)a;
    double y = *(double *)b;

    if(x > y){
        return 1;
    }
    else if(y > x) return -1;
    return 0;
}
int compareStruct(void *a, void *b) {

    Student *x = (Student *)a;
    Student *y = (Student *)b;

    if (x->marks > y->marks)
        return 1;

    if (x->marks < y->marks)
        return -1;

    return 0;
}

int findPivot(void *arr,int s,int e,size_t size,int (*compare)(void *, void *)){
    int l = s+1;
    int h = e;
    while(l < h){
        while(l <= e && compare((char *)arr + l*size,(char *)arr + s*size) < 0){
            l++;
        }
        while(h >= s && compare((char *)arr + h*size,(char *)arr + s*size) > 0){
            h--;
        }
        if(l < h){
            swap((char *)arr + l*size,(char *)arr + h*size,size);
        }
    }
    swap((char *)arr + s*size,(char *)arr + h*size,size);
    return h;
}

void quickSort(void *arr,int s,int e,size_t size,int (*compare)(void *,void *)){
    if(s >= e) return;
    int p = findPivot(arr,s,e,size,compare);
    quickSort(arr,s,p-1,size,compare);
    quickSort(arr,p+1,e,size,compare);
}

int main(){
    int arr[4] = {43,213,32,34};
    int n = sizeof(arr)/sizeof(arr[0]);

    printf("Before Sorting:\n");
    for(int i=0;i<n;i++){
        printf("%d ",arr[i]);
    }
    printf("\n");

    quickSort(arr,0,n-1,sizeof(arr[0]),compareInt); // compareInt is a function pointer

    printf("After Sorting:\n");
    for(int i=0;i<n;i++){
        printf("%d ",arr[i]);
    }
    printf("\n");
}