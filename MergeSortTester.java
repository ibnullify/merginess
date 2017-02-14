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
  <INSERT YOUR DISTILLATION OF ALGO HERE>

  BIG-OH CLASSIFICATION OF ALGORITHM:
  <INSERT YOUR EXECUTION TIME CATEGORIZATION OF MERGESORT HERE>

  Mean execution times for dataset of size n:
  Batch size: <# of times each dataset size was run>
  n=1       time: 
  n=10      time: 
  n=100     time: 
  ...
  n=<huge>  time: 

  ANALYSIS:
  <INSERT YOUR RESULTS ANALYSIS HERE>
  ======================================*/

public class MergeSortTester 
{
    // Number of tests per "run time" check
    private static final int NUM_TESTS = 1000;
    private static final int GEN_RANGE = 1000; // range for random numbers.

    private static final String OUTPUT_CSV_FILENAME = "runtimes.csv";

    // Gens random array of length "length". Each number
    //      is within range (-range/2, range/2)
    private static int[] genRandom(int length, int range) {
        int[] result = new int[length];
        for(int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * range - range/2);
        }
        return result;
    }

    // Gets average runtime (per function call) of the sort algorithm for an array.
    private static long getRunTimeNano(int[] arr, int numTests) {
        long startTime = System.nanoTime();
        for(int repeat = 0; repeat < numTests; repeat++) {
            int[] result = MergeSort.sort(arr);
        }
        return (System.nanoTime() - startTime) / numTests;
    }

    // Gets average runtime (per function call) of sort algorithm for random 
    //      array of length "n"
    private static long getNanoForN(int n) {
        return getRunTimeNano (genRandom(n, GEN_RANGE) , NUM_TESTS);
    }

    private static void writeRuntimeCSV(long[] runtimes) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < runtimes.length; i++) {
            result.append(i + 1).append(",").append(runtimes[i]).append("\n");
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
     * execution time analysis 
     * <INSERT YOUR DESCRIPTION HERE OF 
     *  YOUR APPARATUS FOR GENERATING EXECUTION 
     *  TIME DATA...>
     ******************************/
    public static void main( String[] args ) 
    {
        // Get list of runtimes from length 1-128

        long[] runTimes = new long[1000];
        for(int i = 0; i < runTimes.length; i++) {
            runTimes[i] = getNanoForN(i + 1);
        }
        writeRuntimeCSV(runTimes);

    }//end main

}//end class
