import java.util.ArrayList;

public class Count_Significant_Reverse_Pairs{
    public static void main(String[] args) {
        int[] arr = {1,3,2,3,1,3,4,5,2,1,2};
        System.out.println("Brute Force:" + brute(arr));
        System.out.println("Optimal Force:" + inversionCount(arr));
    }

    // OPTIMAL - Merge Sort + Inversion Count
    public static int countInversions(int[] arr,int s,int m,int e){
        int cnt = 0;
        int right = m+1;
        for(int left=s;left<=m;left++){
            while(right <= e && arr[left] > (long)1L*arr[right]*2){
                right++;
            }
            cnt += right - (m+1);
        }
        return cnt;
    }
	public static void merge(int[] arr, int s, int m, int e) {
		ArrayList<Integer> temp = new ArrayList<>();
		int low = s;
		int high = m + 1;
		
		while (low <= m && high <= e) {
			if (arr[low] <= arr[high]) {
				temp.add(arr[low]);
				low++;
			}
			else {
				temp.add(arr[high]);
				high++;
			}
		}
		while (low <= m) {
			temp.add(arr[low]);
			low++;
		}
		while (high <= e){
			temp.add(arr[high]);
			high++;
		}
		
		for(int i=s;i<=e;i++){
		    arr[i] = temp.get(i-s);
		}
	}
	public static int mergeSort(int[] arr, int s, int e) {
		if (s >= e)
			return 0;
		int m = s + (e - s)/2;
		int cnt = 0;
		cnt += mergeSort(arr, s, m);
		cnt += mergeSort(arr, m + 1, e);
		cnt += countInversions(arr,s,m,e);
		merge(arr, s, m, e);
		return cnt;
	}
	public static int inversionCount(int arr[]) {
		return mergeSort(arr,0,arr.length-1);
	}




    // BRUTE FORCE
    private static int brute(int[] arr) {
        int cnt = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]*2 < arr[i]) cnt++;
            }
        }
        return cnt;
    }
}