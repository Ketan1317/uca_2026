#include <stdio.h>
#include <string.h>
#include <stddef.h>
// "void *" means "pointer to a memory location of an unknown type" and is called generic pointer

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

void heapify(void *arr,int n,int i,size_t size,int (*compare)(void *, void *)){
    int left = 2*i+1;
    int right = 2*i+2;
    int largest = i;

    if(left < n && compare((char *)arr + left*size,(char *)arr + largest*size) > 0){
        largest = left;
    }
    if(right < n && compare((char *)arr + right*size,(char *)arr + largest*size) > 0){
        largest = right;
    }


    if(largest != i){
        swap((char *)arr + largest*size,(char *)arr + i*size,size);
        heapify(arr,n,largest,size,compare);
    }

}

void heapSort(void *arr,int n,size_t size,int (*compare)(void *, void *)){
    for(int i=n/2-1;i>=0;i--){
        heapify(arr,n,i,size,compare);
    }

    for(int i=n-1;i>0;i--){
        swap(arr,(char *)arr + i*size,size);
        heapify(arr,i,0,size,compare);
    }

}

int compareInt(void *a, void *b){
    int x = *(int *)a;
    int y = *(int *)b;

    if(x > y){
        return 1;
    }
    else if(y > x){
        return -1;
    }
    return 0;
}
int compareFloat(void *a, void *b){
    float x = *(float *)a;
    float y = *(float *)b;

    if(x > y){
        return 1;
    }
    else if(y > x){
        return -1;
    }
    return 0;
}
int compareDouble(void *a, void *b){
    double x = *(double *)a;
    double y = *(double *)b;

    if(x > y){
        return 1;
    }
    else if(y > x){
        return -1;
    }
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

int main(){
    

    int intArr[3] = {34,11,1};
    float floatArr[3] = {3.4,11.0,1.1};
    double DoubleArr[3] = {34.44,11.11,1.90};
    Student students[] = {
        {3, "Karan", 85.5},
        {1, "Rahul", 92.0},
        {2, "Aman", 78.5}
    };

    int n = sizeof(DoubleArr)/sizeof(DoubleArr[0]);

    printf("Before Sorting:\n");
    for(int i=0;i<n;i++){
        printf("%lf ",DoubleArr[i]);
    }
    printf("\n");

    heapSort(DoubleArr,n,sizeof(DoubleArr[0]),compareDouble); // compareInt is a function pointer

    printf("After Sorting:\n");
    for(int i=0;i<n;i++){
        printf("%lf ",DoubleArr[i]);
    }
    printf("\n");
}


