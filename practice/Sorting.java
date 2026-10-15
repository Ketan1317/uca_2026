public class Sorting {
    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // TC (Best) - N;
    // TC (Avg & Worst) - N^2;
    // Cache Friendly
    // Auxiliary Space - O(1)
    // Stable
    // In-Place
    // Adaptive
    // Repeatedly swaps adjacent elements, moving the largest unsorted element
    // toward the end...
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }

    // TC (Best) - N;
    // TC (Avg & Worst) - N^2;
    // Cache Friendly
    // Auxiliary Space - O(1)
    // Stable
    // In-Place
    // Adaptive
    // Moves each new element to the left, until it reaches its correct position
    // in the sorted order
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(j, j - 1, arr);
                } else {
                    break;
                }
            }
        }
    }

    public static int getMinIdx(int s, int e, int[] arr) {
        int mini = s;
        for (int i = s; i <= e; i++) {
            if (arr[i] < arr[mini]) {
                mini = i;
            }
        }
        return mini;
    }

    // TC (Best & Avg & Worst) - N^2;
    // Cache Friendly
    // Auxiliary Space - O(1)
    // Not-Stable
    // In-Place
    // most n-1 swaps
    // Comparisons - N*(N-1)/2
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int mini = getMinIdx(i, n - 1, arr);
            if (mini != i) {
                swap(i, mini, arr);
            }
        }
    }

    public static void merge(int s, int m, int e, int[] arr) {
        int[] temp = new int[e - s + 1];
        int l = s;
        int r = m + 1;
        int k = 0;

        while (l <= m && r <= e) {
            if (arr[l] <= arr[r]) {
                temp[k++] = arr[l++];
            } else {
                temp[k++] = arr[r++];
            }
        }
        while (l <= m) {
            temp[k++] = arr[l++];
        }
        while (r <= e) {
            temp[k++] = arr[r++];
        }

        for (int i = s; i <= e; i++) {
            arr[i] = temp[i - s];
        }
    }

    public static void mergeSortHelper(int s, int e, int[] arr) {
        if (s >= e) {
            return;
        }

        int m = s + (e - s) / 2;
        mergeSortHelper(s, m, arr);
        mergeSortHelper(m + 1, e, arr);
        merge(s, m, e, arr);
    }

    public static void mergeSort(int[] arr) {
        // TC (Best & Avg & Worst) - O(N*LongN);
        // Cache Friendly
        // Auxiliary Space - O(N)
        // Stable
        // Not-In-Place
        // Sorting approach - Divide and Conquer
        mergeSortHelper(0, arr.length - 1, arr);
    }

    public static int partition(int s, int e, int[] arr) {
        int p = arr[s];
        int i = s;
        int j = e;

        while (i < j) {
            while (i <= e && arr[i] <= p) {
                i++;
            }
            while (j >= s && arr[j] > p) {
                j--;
            }

            if (i < j) {
                swap(i, j, arr);
            }
        }

        swap(s, j, arr);
        return j;
    }

    public static void quickSortHelper(int s, int e, int[] arr) {
        if (s >= e) {
            return;
        }
        int p = partition(s, e, arr);
        quickSortHelper(s, p - 1, arr);
        quickSortHelper(p + 1, e, arr);
    }

    public static void quickSort(int[] arr) {
        // TC (Best & Avg) - O(N*LongN);
        // TC (Worst) - O(N^2);
        // Cache Friendly - Generally good
        // Auxiliary Space (Avg) - O(LogN)
        // Auxiliary Space (Worst) - O(N)
        // Not-Stable
        // In-Place
        // Sorting approach - Divide and Conquer
        quickSortHelper(0, arr.length - 1, arr);
    }

    public static void heapify(int i, int size, int[] arr) {
        int start = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[start]) {
            start = left;
        }
        if (right < size && arr[right] > arr[start]) {
            start = right;
        }
        if (start != i) {
            swap(i, start, arr);
            heapify(start, size, arr);
        }
    }

    // TC (Best & Avg & Best) - O(N*LongN);
    // Cache Friendly - Not good
    // Auxiliary Space (Recursive Heapify) - O(LogN)
    // Not-Stable
    // In-Place
    public static void heapSort(int[] arr) {
        int n = arr.length;
        // Build Heap TC - O(LogN)
        // Why is building a heap (O(n)), not (O(nlog n))? Most nodes are near the leaves and require very little heapification work...
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n, arr);
        }
        // TC - O(N) * O(LogN)
        for (int i = n - 1; i > 0; i--) {
            swap(0, i, arr);
            heapify(0, i, arr);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 8, 5, 4, 7, 2, 1 };
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        heapSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

// An algorithm is adaptive if its performance improves when the input already has some useful order or structure