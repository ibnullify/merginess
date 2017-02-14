/*****************
 * Adris Jautakas, Dima Hvirtsman, Ibnul Jahan
 * APCS pd2
 * HW#07 : What Does the Data Say?
 * 2017-02-15
 ******************/


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
    private static final int NUM_TESTS = 10000;
    private static final int GEN_RANGE = 1000; // range for random numbers.

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


    /******************************
     * execution time analysis 
     * <INSERT YOUR DESCRIPTION HERE OF 
     *  YOUR APPARATUS FOR GENERATING EXECUTION 
     *  TIME DATA...>
     ******************************/
    public static void main( String[] args ) 
    {
        // Get list of runtimes from length 1-128

        long[] runTimes = new long[128];
        for(int i = 0; i < runTimes.length; i++) {
            runTimes[i] = getNanoForN(i + 1);
            System.out.println ("n: " + (i + 1) + ", time: " + runTimes[i]);
        }

    }//end main

}//end class
