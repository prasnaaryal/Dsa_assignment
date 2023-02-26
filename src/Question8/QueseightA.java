package Question8;

import java.util.Stack;

class QueseightA {


    /*Question 8
    a)
    Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
    INPUT: 1 0 1 0 0
    0 1 1 1 1
    0 0 0 0 1
    0 0 0 1 1
    0 1 0 1 1
    OUTPUT: 4
    */

        public static int findMaximumSquareArea(int[][] matrix) {
            int maximumArea = 0;
            int row = matrix.length;
            int column = matrix[0].length;
            int[][] heights = new int[row][column];

            // calculate height of each column
            for (int i = 0; i < column; i++) {
                heights[0][i] = matrix[0][i];
                for (int j = 1; j < row; j++) {
                    if (matrix[j][i] == 1) {
                        heights[j][i] = 0;
                    } else {
                        heights[j][i] = heights[j-1][i] + 1;
                    }
                }
            }

            for (int i = 0; i < row; i++) {
                int area = maxAreaInHistogram(heights[i]);
                if (area > maximumArea) {
                    maximumArea = area;
                }
            }
            return maximumArea;
        }

        public static int maxAreaInHistogram(int[] heights) {
            int maximumArea = 0;
            int n = heights.length;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i <= n; i++) {
                int h = (i == n ? 0 : heights[i]);
                if (stack.isEmpty() || h >= heights[stack.peek()]) {
                    stack.push(i);
                } else {
                    int tp = stack.pop();
                    int area = heights[tp] * (stack.isEmpty() ? i : i - 1 - stack.peek());
                    if (area > maximumArea) {
                        maximumArea = area;
                    }
                    i--;
                }
            }

            return maximumArea;
        }
        public static void main(String[] args) {
            int[][] matrix = {
                    {1, 0, 1, 0, 0},
                    {0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1},
                    {0, 0, 0, 1, 1},
                    {0, 1, 0, 1, 1}};
            int maximumArea = findMaximumSquareArea(matrix);
            System.out.println("Max square area of 0s  is: " + maximumArea);
        }
    }

