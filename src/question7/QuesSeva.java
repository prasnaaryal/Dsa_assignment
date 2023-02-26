package question7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class  QuesSeva{

    // Determine the number of threads to be used based on the number of processors available
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        int n = 1000; // Define the size of the square matrices to be multiplied
        int[][] A = generateRandomMatrix(n); // Generate a random matrix A of size n x n
        int[][] B = generateRandomMatrix(n); // Generate a random matrix B of size n x n
        int[][] C = new int[n][n]; // Create an empty matrix C of size n x n to store the result

        long start = System.currentTimeMillis(); // Record the start time of the matrix multiplication

        // Create a thread pool with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // Create a task for each element in the resulting matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MatrixMultiplicationTask task = new MatrixMultiplicationTask(A, B, C, i, j);
                executor.execute(task); // Submit the task to the thread pool
            }
        }

        executor.shutdown(); // Shut down the thread pool once all tasks are submitted

        try {
            // Wait for all tasks to finish
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis(); // Record the end time of the matrix multiplication
        System.out.println("Elapsed time: " + (end - start) + "ms");

    }

    // Generate a random matrix of size n x n
    private static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
        return matrix;
    }

    // Task for multiplying a single element in the resulting matrix
    static class MatrixMultiplicationTask implements Runnable {

        private final int[][] A;
        private final int[][] B;
        private final int[][] C;
        private final int i;
        private final int j;

        public MatrixMultiplicationTask(int[][] A, int[][] B, int[][] C, int i, int j) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int k = 0; k < A.length; k++) {
                sum += A[i][k] * B[k][j]; // Multiply corresponding elements of matrix A and B and add to sum
            }
            C[i][j] = sum; // Assign the result to the corresponding element of matrix C
        }
    }
}