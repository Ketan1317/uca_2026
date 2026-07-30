public class QuickSelect {

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 12, 15, 3};
        int k = 4;
        quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    public static void quickSelect(int[] arr, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int p = pivot(arr, start, end);
        if (p == k) {
            for (int i = 0; i < k; i++) {
                System.out.println(arr[i]);
            }
        } else if (p > k) {
            quickSelect(arr, start, p - 1, k);
        } else {
            quickSelect(arr, p + 1, end, k);
        }

    }

    public static int pivot(int[] arr, int start, int end) {
        int p = arr[start];
        int i = start + 1;
        int j = end;

        while (i <= j) {
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
