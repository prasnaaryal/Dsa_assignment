package question3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuesthreeA {

// returns the product of all elements in the array.
        public static int productans(int[] subaaray){
            if (subaaray.length == 0){
                return 0;
            }
            int res = 1;
            for (int i = 0;i<subaaray.length;i++){
                res = res*subaaray[i];
            }
            return res;
        }
// returns the min diff between the products of all possible pairs of two sub-arrays
        public static int minimumdiff(int[] array){
            int mindiff = Integer.MAX_VALUE;
            int n = array.length;
            for (int i = 0; i < (1 << n); i++) {
//                generates all possible combinations of sub-arrays of length n/2
                if (Integer.bitCount(i) == n / 2) {
                    int[] subarray1 = new int[n / 2];
                    int[] subarray2 = new int[n / 2];
                    int index1 = 0;
                    int index2 = 0;
                    for (int j = 0; j < n; j++) {
                        if ((i & (1 << j)) > 0) {
                            subarray1[index1++] = array[j];
                        } else {
                            subarray2[index2++] = array[j];
                        }
                    }
//                     calculates the product of the elements of each sub-array using the productan
                    int subarray1product = productans(subarray1);
                    int subarray2product = productans(subarray2);
                    int curr_min_diff = 0;
                    if (subarray2product>subarray1product){
                        curr_min_diff = subarray2product-subarray1product;
                    }else{
                        curr_min_diff = subarray1product-subarray2product;
                    }
                    if (curr_min_diff<mindiff){
                        mindiff = curr_min_diff;
                    }

                }
            }
            return mindiff;


        }


        public static void main(String[] args) {
            int[] array = {5, 2, 4, 11};
            int answer = minimumdiff(array);
            System.out.println(answer);

        }
    }