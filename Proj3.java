import java.io.*;
import java.util.*;

public class Proj3{
	public static void main(final String[] args) throws FileNotFoundException {
        final Scanner s = new Scanner(new File("input3.txt"));
        final ArrayList<Integer> data = new ArrayList<Integer>();
        while (s.hasNext()) {
            if (s.hasNextInt()) {
                data.add(s.nextInt());
            }
        }
        final int[] arr = new int[data.size()+1];
        for (int i = 0; i < (arr.length-1); i++) {
            arr[i] = data.get(i).intValue();
        }

        System.out.println(mergeSort(arr, (data.size()-1))); // recursive kick off

    }

    public static int mergeSort(final int[] a, final int n) {
        int left;
        int right;
        // base case - if the size of the array is 1 or less....return
        if (n < 2) {
            return 0;
        }

        final int mid = n / 2; // define your mid point
        final int[] l = new int[mid]; // making a new left array of size mid (for example 4)
        final int[] r = new int[n - mid]; // making a new right array of size n-mid (9 - 4 = 5)

        // we fill the new left array with all the values of the left half
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }

        // we fill the new right array with all the values of the right half
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }

        // recursive calls
        //saving left and right to add to merge inversions for total inversions
        left = mergeSort(l, mid); // taking the new left array and recursing until merged
        right = mergeSort(r, n - mid); // taking new right array and recursing until merged

        //return left + right + merge results for total inversions
        return (left + right + merge(a, l, r, mid, n - mid)); // merge both the left and the right after they have recursed
    }

    public static int merge(final int[] a, final int[] l, final int[] r, final int left, final int right) { // inversions
                                                                                                             // come
                                                                                                             // into
                                                                                                             // play
                                                                                                             // here
		int i = 0;
		int j = 0;
        int k = 0;
        int inversions = 0;
		
		while(i < left && j < right) {
			//if the value in left is less than or equal to the value in right 
			if(l[i] <= r[j]) {
				//we put the value in left in the new array first and update i and k
                a[k++] = l[i++];
			}
			//if the value in right is less than the value in left 
			else{
				//we put the value in right in the new array first, and update j and k 
                a[k++] = r[j++];
                //inversion here
                inversions += left-i;
			}
		}
		
		while (i < left) {
            a[k++] = l[i++];
            //more inversions
            inversions++;
		}
		
		while (i < right) {
            a[k++] = r[j++];
        }
        
        return inversions;
	}
}