## Jagged Array in Java

*In Java, a Jagged array is a multidimensional array where each row can have a different number of columns. When we work with a jagged array, one thing to keep in mind is that the inner array can be of different lengths. It is like a 2D array, but each row can have a different number of elements.*

The image below demonstrates a Jagged array, where each row in a 2D array can have a different number of elements.

![App Screenshot](https://media.geeksforgeeks.org/wp-content/uploads/20250526110303024861/JaggedArray.webp)


### Declaration and Initialization of Jagged Array

The declaration of Jagged array is:
```
data_type array_name[][] = new data_type[n][];     // n= no. of rows

array_name[0] = new data_type[n1]        //n1= no. of columns in row-1
array_name[1] = new data_type[n2]       //n2= no. of columns in row-2
array_name[2] = new data_type[n3]       //n3= no. of columns in row-3
```

The Alternative ways to initialize a Jagged array is listed below:

```
// Method 1
int arr_name[][] = new int[][]  { 
                                  new int[] {10, 20, 30 ,40},
                                   new int[] {50, 60, 70, 80, 90, 100},
                                   new int[] {110, 120}
                              }; 
```
```
// Method 2  
int[][] arr_name = { 
                                new int[] {10, 20, 30 ,40},
                                 new int[] {50, 60, 70, 80, 90, 100},
                                 new int[] {110, 120}
                             };     
```
```
// Method 3  
int[][] arr_name = {
                                  {10, 20, 30 ,40},
                                  {50, 60, 70, 80, 90, 100},
                                  {110, 120}
                              };
```

### More Examples of Jagged Array

- **Example 1:** Here row i has i + 1 columns.

```
class JaggedArray {
    public static void main(String[] args) {
        int r = 5;

        // Declaring 2-D array with 5 rows
        int arr[][] = new int[r][];

        // Creating a 2D array such that first row has 1 element, second row has two elements and so on
        for (int i = 0; i < arr.length; i++)
            arr[i] = new int[i + 1];

        // Initializing array
        int count = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = count++;

        // Displaying the values of 2D Jagged array
        System.out.println("Contents of 2D Jagged Array");
      
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }
}
```

**OUTPUT:**
```
Contents of 2D Jagged Array
0 
1 2 
3 4 5 
6 7 8 9 
10 11 12 13 14 
```
- **Example 2:** User Input Jagged Array

```
import java.util.Scanner;

public class JaggedInputArray {
    public static void main(String[] args) {
      
        Scanner scan = new Scanner(System.in);
        
      	System.out.print("Enter the number of sub-arrays: ");
        int numberOfArrays = scan.nextInt();
        
        // Declare the jagged array
        int[][] jaggedArray = new int[numberOfArrays][];
        
        // Allocate memory to each sub-array
        for (int i = 0; i < numberOfArrays; i++) {
            System.out.print("Enter the size of sub-array " + (i + 1) + ": ");
            int sizeOfSubArray = scan.nextInt();
            jaggedArray[i] = new int[sizeOfSubArray];
        }
        
        // Initialize the elements of each sub-array
        for (int i = 0; i < numberOfArrays; i++) {
            System.out.println("Enter the elements of sub-array " + (i + 1) + ":");
          
            for (int j = 0; j < jaggedArray[i].length; j++) {
                jaggedArray[i][j] = scan.nextInt();
            }
        }
        
        // Print the elements of the jagged array
        System.out.println("The jagged array is:");
        for (int i = 0; i < numberOfArrays; i++) {
            
          	for (int j = 0; j < jaggedArray[i].length; j++) {
                System.out.print(jaggedArray[i][j] + " ");
            }
            System.out.println();
        }
        
        scan.close();
    }
}
```

**INPUT:**
```
Enter the number of sub-arrays: 4
Enter the size of sub-array 1: 3
Enter the size of sub-array 2: 4
Enter the size of sub-array 3: 2
Enter the size of sub-array 4: 4

Enter the elements of sub-array 1:
1 2 3

Enter the elements of sub-array 2:
5 6 7 8 

Enter the elements of sub-array 3:
6 7

Enter the elements of sub-array 4:
1 2 3 4
```

**OUTPUT:**
```
The jagged array is:
1 2 3 
5 6 7 8 
6 7 
1 2 3 4 
```

### Advantages

The advantages of using a jagged array is listed below:

- With jagged array we can decide the size at runtime.
- Jagged array saves memory because we only create space for what we actually needed.
- Sometimes we have to work with data that has rows of different lengths, in that case we prefer using jagged array.
- Jagged arrays are faster because we only store what we needed and that why accessing the data becomes faster.
**Note:** *Jagged arrays can make the code a bit harder to write and read, so it’s best to use them only when really needed.*


## Arrays Class in Java

*The Arrays class in the java.util package is a utility class that provides a collection of static methods for performing common operations on Java arrays, such as sorting, searching, comparing and converting arrays to strings.
It does not extend any special classes like all Java classes, it implicitly extends Object. The Arrays class itself cannot be instantiated and is designed solely for utility purposes.*

### Why do we need the Java Arrays class
Java provides a utility class called java.util.Arrays to help developers perform common array operations easily and efficiently. like:

- Fill an array with a particular value.
- Sort an array
- Search in an array
And many more...

**Class Declaration**  

Arrays is a final utility class in java.util package that extends Object class, which is the root of the Java class hierarchy

```
public class Arrays extends Object
```
To use Arrays,
```
Arrays.<function name>;
Arrays.sort(array_name);
```

### Methods in Java Array Class 
The Arrays class of the java.util package contains several static methods that can be used to fill, sort, search, etc in arrays. Let's take a look at methods and their implementation:

**1. asList() method.**

This method converts an array into a list.

```
import java.util.Arrays;

class ArraysList{
  
    public static void main(String[] args){
        // Get the Array
        int intArr[] = { 10, 20, 15, 22, 35 };

        // To convert the elements as List
        System.out.println("Integer Array as List: "
                           + Arrays.asList(intArr));
    }
}
```

**OUTPUT:**
```
Integer Array as List: [[I@19469ea2]
```
**Note:** When asList() is used with primitive arrays, it shows the memory reference of the array instead of the list contents. This happens because the asList() method returns a fixed-size list backed by the original array, and for primitive types like int[], it treats the array as an object, not as a list of values.

**2. binarySearch() Method**

This methods search for the specified element in the array with the help of the binary search algorithm.

```
import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args){

        // Get the Array
        int intArr[] = { 10, 20, 15, 22, 35 };

        Arrays.sort(intArr);

        int intKey = 22;

        // Print the key and corresponding index
        System.out.println(
            intKey + " found at index = "
            + Arrays.binarySearch(intArr, intKey));
    }
}
```
**OUTPUT:**
```
22 found at index = 3
```
**3. binarySearch(array, fromIndex, toIndex, key, Comparator) Method** 

This method searches a range of the specified array for the specified object using the binary search algorithm.

```
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {

        // Get the Array
        int intArr[] = { 10, 20, 15, 22, 35 };

        Arrays.sort(intArr);

        int intKey = 22;

        System.out.println(
            intKey
            + " found at index = "
            + Arrays
                  .binarySearch(intArr, 1, 3, intKey));
    }
}
```

**OUTPUT:**
```
22 found at index = -4
```

**4. compare(array 1, array 2) Method** 

This method returns the difference as an integer lexicographically.

```
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {

        // Get the Array
        int intArr[] = { 10, 20, 15, 22, 35 };

        // Get the second Array
        int intArr1[] = { 10, 15, 22 };

        // To compare both arrays
        System.out.println("Integer Arrays on comparison: "
                           + Arrays.compare(intArr, intArr1));
    }
}
```

**OUTPUT:**
```
Integer Arrays on comparison: 1
```
<img width="1024" height="1536" alt="0df3bfde-b6f1-4cac-98ef-35814819e783" src="https://github.com/user-attachments/assets/00a7d802-d13c-44be-869c-6e4418422faf" />

## Final Arrays in Java

*In Java, the final keyword makes a variable's reference constant, not its contents. When an array is declared as final, you cannot reassign it to point to a new array. However, you can still modify the elements within the array.*

### What Does Final Mean for Arrays?
If an array is final, it means we can not make it point to a new array, but we can change the elements inside it.

**Sample Example:** 
```
final int[] arr = {10, 20, 30};

Modifying elements inside the array is allowed

arr[2] = 99;

Reassigning the array reference is NOT allowed

arr = new int[]{600, 700, 800};  // Compilation error
```

**Explanation:** Here we have declared an array arr as final, which means we can not point arr to a different array, but we can change the value inside the array.

**Here is Java program to illustrate final arrays where compilation error is thrown**

```
// Main class
class FinalArray {

    int p = 20;

    // Main driver method
    public static void main(String args[])
    {

        // Creating objects of above class
        final FinalArray t1 = new FinalArray();
        FinalArray t2 = new FinalArray();

        // Assigning values into other objects
        t1 = t2;

        System.out.println(t1.p);
    }
}
```

**Note:** We cannot assign a value to final variable t1.

**Here is Java Program to Illustrate Reassignment Error in Final Array**

```
// Main class
class FinalArray {

    // Main driver method
    public static void main(String args[])
    {
        // Declaring a final array
        final int arr1[] = { 1, 2, 3, 4, 5 };

        // Declaring normal integer array
        int arr2[] = { 10, 20, 30, 40, 50 };

        // Assigning values to each other
        arr2 = arr1;
        arr1 = arr2;

        // Now iterating over normal integer array
        for (int i = 0; i < arr2.length; i++)

            // Printing the elements of above array
            System.out.println(arr2[i]);
    }
}
```

**Here is demonstrating how to change value inside a final array**

```
// Import Arrays class for toString() method
import java.util.Arrays; 

public class FinalArray {
  
    public static void main(String[] args)
    {
        final int[] numbers = { 1, 2, 3, 4, 5 };

        // Assigning new element into the array
        numbers[0] = 10;
        System.out.println(
            "Array after modifying first element: "
            + Arrays.toString(numbers));
    }
}
```

**What we can and cannot do with final arrays and objects in Java:**

<img width="1536" height="1024" alt="e52d3678-7706-4764-87ad-c2ebdc616bea" src="https://github.com/user-attachments/assets/6b67cba3-bac7-4c07-8a71-085d8fe89305" />



