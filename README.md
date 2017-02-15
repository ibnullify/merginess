# merginess

Adris Jautakas, Dima Hvirtsman, Ibnul Jahan
APCS pd2
HW#07 : What Does the Data Say?
2017-02-15



Test the runtime of MergeSort

##The algorithm is set up with the following:
1)There is a method that writes a random array of any specified length, and there is a method that uses that as a helper to writes a 2 dimensional array (int[][]) containing arrays of size 1 to n, where n is specified. 

2)There is a method that finds the runtime in nanoseconds for each of these arrays a specified number of times, and then averages it. ---

3)These averages are then put into an array, so that the indices match with the 2D array.

4)The length of the list, and the average runtime is then written to a csv file for easier access.

Specifics:
--Generates a two dimensional array, that contains randomly generated arrays from size 1 to 1000.

--Generates a new array with corresponding indices to the previous 2D array that takes the average runtime for the corresponding array at each index run 100 times.

--Export sthis information as a csv file for easy viewing off the command line.

##ANALYSIS:
 Our algorithm gives us data for the first 1000 array sizes, and when graphed, trends start to show. For one thing, it does not seem linear. There is a clear curve in the graph, so it seemingly must be higher than O(n). The next thing is it seems to grow at a much slower rate than a quadratic. The curve is not very steep, and only really starts to become visible around the size 500-700 range, if even then. This places it lower than O(n^2). This makes it a very like candidate for O(nlogn), which was out trio's original guess for the algorithm.


See our graphical data over [here](https://docs.google.com/document/d/1MZgWqBBrV99B9krH5H2i8c_CbM0Husmi6IYUl8YRbuM/pub)

If the link is broken, it is repeated below:
https://docs.google.com/document/d/1MZgWqBBrV99B9krH5H2i8c_CbM0Husmi6IYUl8YRbuM/pub
