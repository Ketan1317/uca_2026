#include <stdio.h>
int main(){
    int arr[] = {1, 2, 3, 4, 1, 2, 4, 1, 2, 3, 4, 3, 3};
    int n = sizeof(arr)/sizeof(arr[0]);
    int ans = 0;
    for(int i=0;i<=31;i++){
        int cnt = 0;
        for(int j=0;j<n;j++){
            int num = arr[j];
            if((num & (1 << i)) != 0){
                cnt++;
            }
        }
        if(cnt%3 != 0){
            ans = ans | (1 << i);
        }
    }
    printf("The number that appears 4 times is: %d",ans);
}