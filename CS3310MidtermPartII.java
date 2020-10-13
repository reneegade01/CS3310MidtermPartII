/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3310midtermpartii;
/* CS 3310 Section 2
   S. Renee Eller
   Midterm Part II 
   Parts A & B 
*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;  

public class CS3310MidtermPartII {

    public static void main(String[] args) {
        
        int[] array1 = new int[100];
        int[] array2 = new int[100];
        int[] array3 = new int[100];
        int[] array4 = new int[100];
                               
        int range = (100 + 100) + 1; 
        array1 = addArray(array1, range);
        array2 = addArray(array2, range);
        array3 = addArray(array3, range);
        array4 = addArray(array4, range);
       
        int sum1 = generateSum(-200, 200);
        int sum2 = generateSum(-200, 200);
        int sum3 = generateSum(-200, 200);
        int sum4 = generateSum(-200, 200);

        System.out.println("For Part A Solution 1: Sorted and utilizing binary search: ");
        Arrays.sort(array1);
        printArray(array1);
        long startTime1aSolution1 = System.nanoTime();         
        int[] answer1aSolution1 = partASolution1(array1, sum1);
        long endTime1aSolution1 = System.nanoTime();
        long elapsedTimelaSolution1 = endTime1aSolution1 - startTime1aSolution1 ; 
        printSolutions(array1, answer1aSolution1, sum1, elapsedTimelaSolution1);
        
        System.out.println("For Part A Solution 2: Sorted and utilizing the approach of moving from the outside indices to the middle"
                            + " (Optimized): ");        
        Arrays.sort(array2);
        printArray(array2); 
        long startTime1aSolution2 = System.nanoTime();             
        int[] answer1aSolution2 = partASolution2(array2, sum2);
        long endTime1aSolution2 = System.nanoTime();    
        long elapsedTime1aSolution2 = endTime1aSolution2 - startTime1aSolution2;             
        printSolutions(array2, answer1aSolution2, sum2, elapsedTime1aSolution2);
        
        System.out.println("For Part B Solution 1: Unsorted and utilizing a brute forch approach: ");
        printArray(array3);
        long startTime1bSolution1 = System.nanoTime();             
        int[] answer1bSolution1 = partBSolution1(array3, sum3);
        long endTime1bSolution1 = System.nanoTime();
        long elapsedTime1bSolution1 = endTime1bSolution1 - startTime1bSolution1;             
        printSolutions(array3, answer1bSolution1, sum3, elapsedTime1bSolution1);
        
        System.out.println("For Part B Solution 2: Unsorted and utilizing a hash map (Optimized): ");
        printArray(array4);
        long startTime1bSolution2 = System.nanoTime();
        int[] answer1bSolution2 = partBSolution2(array4, sum4); 
        long endTime1bSolution2 = System.nanoTime(); 
        long elapsedTime1bSolution2 = endTime1bSolution2 - startTime1bSolution2;         
        printSolutions(array4, answer1bSolution2, sum4, elapsedTime1bSolution2);
    }
    
    public static void printArray(int[] A)
    {
        for(int i = 0; i < A.length; i += 10)
        { 
            for(int j = i; j < 10+i && j < A.length; j++)
            { 
                System.out.print("[" + A[j] + "]" + " ");
            }  
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }
    
    public static void printSolutions(int[] A, int[]answer, int sum, long time)
    {
        if (answer[0] == 0 && answer[1] == 0 && sum != 0)
        {
            System.out.println("There is no answer for the specificed sum in this array");
            System.out.println("-------------------------------------------------------");
        }
        else 
        {
            System.out.println("In an array of " + A.length + " , we get: " + answer[0] + " + " + answer[1] + " = " + sum);
            System.out.println("Nanoseconds elapsed: " + time);
            System.out.println("-------------------------------------------------------");
        }
    }
    
    public static int[] addArray(int[] A, int range)
    {
        for(int i = 0; i <  A.length; i++)
        {
            A[i] = (int)(Math.random() * range) - 100;
        }
        return A;
    }
    
    public static int generateSum(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    
    public static int[] partASolution1(int[]A, int sum) //Binary Search
    {
        for (int i = 0; i < A.length; i++)
        {
            int returnIndex = Arrays.binarySearch(A, sum - A[i]);
            if (returnIndex >= 0) //success (returns index)
            {                                          
                if ((returnIndex != i) || (i > 0 && A[i-1] == A[i])
                     || (i < A.length - 1 && A[i+1] == A[i]))
                {
                    return new int[] {A[i], A[returnIndex]};       
                }
            }
        }    
        return new int[] {0, 0};
    }     
    
    public static int[] partASolution2(int[] A, int sum) //Traverse Array From End Elements To The Middle 
    {
        int leftSide = 0; 
        int rightSide = A.length -1; 
        while (leftSide < rightSide)
        {
            if (A[leftSide] + A[rightSide] == sum)
            {
                return new int[] {A[leftSide], A[rightSide]};
            }
            else if (A[leftSide] + A[rightSide] < sum)
            {
                leftSide++;
                        
            }
            else
            {
                rightSide--;
            }
        }
        return new int[] {0, 0};
    }   
    
    public static int[] partBSolution1(int[] A, int sum) //Brute Force
    {
        for (int i = 0; i < A.length; i++)
        {
            for (int j = i + 1; j < A.length; j++)
            {
                if (A[i] + A[j] == sum)
                {
                    return new int[] {A[i], A[j]};
                }
            }
        }
        return new int[] {0, 0}; 
    }
    
    public static int[] partBSolution2(int[]A, int sum) //Hash Map
    {
        int difference;  
        HashMap<Integer, Integer> elements = new HashMap<>();
        for (int i = 0; i < A.length; i++)
        {
            difference = sum - A[i];
            if (elements.containsKey(difference))
            {
                return new int[] {A[i], A[elements.get(difference)]}; 
            }
            elements.put(A[i], i);
        }
        return new int[] {0, 0}; 
    }
    
}
