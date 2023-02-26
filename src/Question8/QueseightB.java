package Question8;

import java.util.ArrayList;
import java.util.List;

class QueseightB {






        public static int findKthMissingEvenNumber(int[] a, int k) {
            List<Integer> missingEvens = new ArrayList<>();
            int j = 0;
            int i = a[0];
            // Loop until we have found k missing even numbers

            while (missingEvens.size() < k) {
                // If the current even number is present in the array, skip it

                if (j < a.length && a[j] == i) {
                    j++;
                } else {
                    // Otherwise, add it to the list of missing even numbers

                    missingEvens.add(i);
                }
                // Move to the next even number

                i += 2;
            }
            return missingEvens.get(k - 1);
        }

        public static void main(String[] args) {
            int[] a = {0, 2, 6, 18, 22};
            int k = 6;
            // Find the 6th missing even number in the array

            System.out.println(findKthMissingEvenNumber(a, k));
        }}
