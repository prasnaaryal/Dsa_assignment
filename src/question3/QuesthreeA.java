package question3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuesthreeA {

        public static List<int[]> findSubarrays(int[] arr) {
            List<int[]> subArrays = new ArrayList<>();
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int len = j - i + 1;
                    int[] subarray = new int[len];
                    for (int k = 0; k < len; k++) {
                        subarray[k] = arr[i + k];
                    }
                    subArrays.add(subarray);
                }
            }
            return subArrays;
        }
        public static void main(String[] args) {
            int[] arr = new int[]{5,4,2,11};
            List<int[]> subarrays = findSubarrays(arr);
            int lenght = subarrays.size();
            int[] productArray = new int[lenght];
            int i = 0;
            for (int[] arrP : subarrays) {
                int product = 1;
                for (int element : arrP) {
                    product *= element;
                }
                productArray[i] = product;
                i++;
            }
            Arrays.sort(productArray);
            System.out.println(Arrays.toString(productArray));
            int minimumdiff = productArray[1]-productArray[0];
            for (int j = 2 ; j < productArray.length ; j++) {
                minimumdiff = Math.min(minimumdiff, productArray[j]-productArray[j-1]);
            }
            System.out.println(minimumdiff);
        }

    }



