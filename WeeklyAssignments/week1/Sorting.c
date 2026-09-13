#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ITERATIONS 8

// ARRAY GENERATION FUNCTONS
void generateRandom(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = rand() % 100000;
}

void generateAscending(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = i + 1;
}

void generateDescending(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = n - i;
}

// UTIL FUNCTONS
void copyArray(int source[], int destination[], int n)
{
    for(int i = 0; i < n; i++)
        destination[i] = source[i];
}

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}


// SORTING ALGOS...
void bubbleSort(int arr[], int n)
{
    for(int i = 0; i < n - 1; i++){
        int swapped = 0;

        for(int j = 0; j < n - i - 1; j++){
            if(arr[j] > arr[j + 1]){
                swap(&arr[j], &arr[j + 1]);
                swapped = 1;
            }
        }

        // If no swapping happened, array is already sorted
        if(swapped == 0)
            break;
    }
}

void insertionSort(int arr[], int n)
{
    for(int i = 1; i < n; i++){
        int key = arr[i];
        int j = i - 1;

        while(j >= 0 && arr[j] > key){
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = key;
    }
}

void selectionSort(int arr[], int n)
{
    for(int i = 0; i < n - 1; i++){
        int minIndex = i;

        for(int j = i + 1; j < n; j++){
            if(arr[j] < arr[minIndex]){
                minIndex = j;
            }
        }

        if(minIndex != i){
            swap(&arr[i], &arr[minIndex]);
        }
    }
}


// QUICK SORT
int partition(int arr[], int low, int high){
    int pivot = arr[high];
    int i = low - 1;

    for(int j = low; j < high; j++){
        if(arr[j] <= pivot){
            i++;
            swap(&arr[i], &arr[j]);
        }
    }

    swap(&arr[i + 1], &arr[high]);

    return i + 1;
}
void quickSort(int arr[], int low, int high){
    if(low < high){
        int pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}


// MERGE SORT
void merge(int arr[], int left, int mid, int right){
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int L[n1], R[n2];

    for(int i = 0; i < n1; i++)
        L[i] = arr[left + i];

    for(int i = 0; i < n2; i++)
        R[i] = arr[mid + 1 + i];

    int i = 0;
    int j = 0;
    int k = left;

    while(i < n1 && j < n2){
        if(L[i] <= R[j]){
            arr[k++] = L[i++];
        }
        else{
            arr[k++] = R[j++];
        }
    }

    while(i < n1)
        arr[k++] = L[i++];

    while(j < n2)
        arr[k++] = R[j++];
}
void mergeSort(int arr[], int left, int right){
    if(left < right){
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }
}


// HEAP SORT
void heapify(int arr[], int n, int i){
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if(left < n && arr[left] > arr[largest])
        largest = left;

    if(right < n && arr[right] > arr[largest])
        largest = right;

    if(largest != i){
        swap(&arr[i], &arr[largest]);
        heapify(arr, n, largest);
    }
}

void heapSort(int arr[], int n){
    // create Max Heap
    for(int i = n / 2 - 1; i >= 0; i--){
        heapify(arr, n, i);
    }
    for(int i = n - 1; i > 0; i--){
        swap(&arr[0], &arr[i]);
        heapify(arr, i, 0);
    }
}

void testSort(void (*sortFunc)(int[], int), char name[], int n, int type)
{
    int arr[n];

    // Generate array
    if(type == 0)
        generateRandom(arr, n);
    else if(type == 1)
        generateAscending(arr, n);
    else
        generateDescending(arr, n);

    clock_t start = clock();
    sortFunc(arr, n);
    clock_t end = clock();
    double time = (double)(end - start) / CLOCKS_PER_SEC;
    printf("%-10s  Size = %-6d  Time = %.6f sec\n", name, n, time);
}

int main()
{
    srand(time(NULL));

    int n = 8000;

    printf("Random Array\n");
    testSort(bubbleSort, "Bubble", n, 0);
    testSort(insertionSort, "Insertion", n, 0);
    testSort(selectionSort, "Selection", n, 0);
    testSort(heapSort, "Heap", n, 0);

    return 0;
}