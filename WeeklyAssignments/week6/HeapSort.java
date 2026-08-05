
import java.util.Arrays;

public class HeapSort {
    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }

    public static int parent(int i) {
        return (i - 1) / 2;
    }

    public static void heapify(int[] arr, int i,int end) {
        int curr = i;
        if (left(i) < end && arr[left(i)] > arr[curr]) {
            curr = left(i);
        }
        if (right(i) < end && arr[right(i)] > arr[curr]) {
            curr = right(i);
        }

        if (curr != i) {
            int temp = arr[curr];
            arr[curr] = arr[i];
            arr[i] = temp;

            heapify(arr,curr,end);
        }
    }

    public static int[] sortArray(int[] nums) {
        int n = nums.length;
        for(int i=n/2-1;i>=0;i--){
            heapify(nums,i,n);
        }

        for(int i=n-1;i>0;i--){
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            heapify(nums,0,i);
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] arr ={2143,24,3353,4,12,43,14242};
        sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}