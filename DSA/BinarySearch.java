
public class BinarySearch {

    public static int binarySearch(int[] arr, int t, int s, int e) {

        if (s > e) {
            return -1;
        }
        int m = s + (e - s) / 2;

        if (arr[m] == t) {
            return m;
        }

        if (arr[m] > t) {
            return binarySearch(arr, t, s, m - 1);
        } else {
            return binarySearch(arr, t, m + 1, e);
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int t = 94;
        System.out.println("Index of target present in array: " + binarySearch(arr, t, 0, arr.length - 1));
    }
}
