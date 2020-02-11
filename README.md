Project: BitonicSort Parallization Project  
Author: Brandon Bires-Navel (brn5915@rit.edu) 

Running the program:  
The main program is located in the src directory. Java version 1.8 is required.  
Name:  
    BitonicSort - sorts an array of random doubles using the bitonic sort algorithm  

Synopsis 
    BitonicSort N R [OPTIONS]  

Description  
    If -s nor -p is specified, the program defaults to the parallel implementation, 
    by default only the statistics generated are sent to the standard output stream.
 
    N
        number of doubles to generate for the array that will be sorted (must be power of 2)
    R
        number of times to sort different arrays
    -s 
        specifies to run a serial implementation of the bitonic sort algorithm
    -p[X]
        specifies to run a parallel implementation of the bitonic sort algorithm.
        X is a one digit number representing how many processor nodes will 
        be used (optional). If -p is specified with no number, it defaults to 4.
    -o
        outputs the sorted array to the standard output stream

Example program usage:  
java BitonicSort 1024 19 -s // Sorts a random array of size 1024 19 times serially  
java BitonicSort 16 1 -s -0 // Sorts a random array of size 16 1 time serially and prints the result  
java BitonicSort 16384 1 -p // Sorts a random array of size 16384 1 time with 4 processing nodes  
java BitonicSort 8192 19 -p8 // Sorts a random array of size 8192 19 times with 8 processing nodes  
