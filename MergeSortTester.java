/*****************
 * Adris Jautakas, Dima Hvirtsman, Ibnul Jahan
 * APCS pd2
 * HW#07 : What Does the Data Say?
 * 2017-02-15
 ******************/

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;


/*======================================
  class MergeSortTester

  ALGORITHM:
  There is a method that writes a random array of any specified length, and there is a method that uses that as a helper to writes a 2 dimensional array (int[][]) containing arrays of size 1 to n, where n is specified.
  There is a method that finds the runtime in nanoseconds for each of these arrays a specified number of times, and then averages it. These averages are then put into an array, so that the indices match with the 2D array.
  The length of the list, and the average runtime is then written to a csv file for easier access.
  

  BIG-OH CLASSIFICATION OF ALGORITHM:
  O(nlogn)

  Mean execution times for dataset of size n:
  Batch 100:<# of times each dataset size was run>
  n=1       time: 26322 //this is an exceptionally large outlier
  n=10      time: 3393
  n=100     time: 39290
  ...
  n=1000    time: 456677

  ANALYSIS:
  Our algorithm gives us data for the first 1000 array sizes, and when graphed trends start to show. For one thing, it does not seem linear. There is a clear curve in the graph, so it seemingly must be higher than O(n). The next thing is it seems to grow at a much slower rate than a quadratic. The curve is not very steep, and only really starts to become visible around the size 500-700 range, if even then. This places it lower than O(n^2). This makes it a very like candidate for O(nlogn), which was out trio's original guess for the algorithm.
  ======================================*/

public class MergeSortTester 
{
    // Number of tests per "run time" check
    private static final int NUM_TESTS = 100;
    private static final int GEN_RANGE = 1000; // range for random numbers.

    private static final String OUTPUT_CSV_FILENAME = "runtimes.csv";

    public static long startTime;// = System.nanoTime();
    public static long constantrun;// = System.nanoTime() - startTime;

    
    // Gens random array of length "length". Each number
    //      is within range (-range/2, range/2)
    private static int[] genRandom(int length, int range) {
        int[] result = new int[length];
        for(int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * range - range/2);
        }
        return result;
    }

    // Generate arrays of increasing length
    // Incremented by 10s
    private static int[][] genArrays(int numArrays) {
        int[][] result = new int[numArrays][0];
        for(int i = 0; i < numArrays; i++) {
            result[i] = genRandom(500*(i+1), GEN_RANGE);
	    System.out.println("Generating: " + (500*(i+1)));
        }
        return result;
    }

    // Gets average runtime (per function call) of the sort algorithm for an array.
    private static long getRunTimeNano(int[] arr, int numTests) {
        long startTime = System.nanoTime();
        for(int repeat = 0; repeat < numTests; repeat++) {
            int[] result = MergeSort.sort(arr);
        }
	System.out.println(arr.length + " complete");
        return (System.nanoTime() - startTime) / numTests;
    }

    private static void writeRuntimeCSV(long[] runtimes) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < runtimes.length; i++) {
            result.append(500*(i + 1)).append(",").append(runtimes[i]).append("\n");
        }
        String content = result.toString();
        writeTextFile(content, OUTPUT_CSV_FILENAME);        
    }

    // Writes text to text file
    private static void writeTextFile(String content, String fname) {
        BufferedWriter writer = null;
        try {
            File result = new File(fname);

            writer = new BufferedWriter(new FileWriter(result));
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Write to file \"" + fname + "\" failed!");
            e.printStackTrace();
        // Close regardless
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                System.err.println("Welp we're screwed");
                e.printStackTrace();
            }
        }
    }

    /******************************
     * execution time analysis : O(nlogn)
     * Generate a two dimensional array, that contains randomly generated arrays from size 1 to 1000.
     * Generate a new array with corresponding indices to the previous 2D array that takes the average runtime for the corresponding array at each index run 100 times.
     * Export this information as a csv file for easy viewing off the command line.
     ******************************/
    public static void main( String[] args ) 
    {
        System.out.println("Starting....");


	System.out.print("Average O(1) speed:");
	long startTimea = System.nanoTime();
	long tempconstantrun = System.nanoTime() - startTimea;
	System.out.println(tempconstantrun);
	constantrun = tempconstantrun;


	int[][] arrays = genArrays(200);
        long[] runTimes = new long[arrays.length];
        for(int i = 0; i < runTimes.length; i++) {
            runTimes[i] = getRunTimeNano(arrays[i], NUM_TESTS);
            //System.out.println((i+1) + " : " + runTimes[i]);
        }
        System.out.println("Finished");
        writeRuntimeCSV(runTimes);

	
    }//end main

}//end class
