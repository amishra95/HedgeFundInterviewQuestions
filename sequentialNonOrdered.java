import java.util.Arrays;

public class sequentialNonOrdered {
	public int func(int[] arr, int num) {
	int[] brian = new int[arr.length];
	Arrays.sort(arr);
for(int i = 1; i < arr.length; i++) {
	if(arr[i]- arr[i-1] > 1) {
		brian[i] = arr[i];
		}
	}
	return 0;
	}
}
