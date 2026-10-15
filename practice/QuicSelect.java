public class QuicSelect {
    // retrieve the first K elements in sorted order 
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 12, 15, 3};
        int k = 4;
        quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    // QuickSelect, an algorithm based on Quick Sort that finds the element at a
    // particular sorted position without sorting the entire array...
    public static void quickSelect(int[] arr, int s, int e, int k) {
        if(s > e){
            return;
        }

        int p = pivot(arr, s, e);
        if(p == k){
            for(int i=0;i<=k;i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        else if(p > k){
            quickSelect(arr, s, p-1, k);
        }
        else{
            quickSelect(arr, p+1, e, k);
        }
    }

    public static int pivot(int[] arr, int start, int end) {
        int p = arr[start];
        int i = start + 1;
        int j = end;

        while (i < j) {
            while (i <= end && arr[i] <= p) {
                i++;
            }
            while (j >= start && arr[j] > p) {
                j--;
            }
            if (i < j) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int temp = arr[start];
        arr[start] = arr[j];
        arr[j] = temp;
        return j;
    }
}
