/**
 * Created by austin on 6/8/16.
 */
public class MergeSort {


    //Given v[start..end] (inclusive), sort the elements
    private int[] mergeSort(int[] v, int start, int end) {


        //Does v have more than one element
        if (end - start > 0) {
            int middle = (int) Math.floor((start + end) / 2.0);


            mergeSort(v, start, middle);   //sort first half
            mergeSort(v, middle + 1, end); //sorts the second half
            merge(v, start, middle, end);  //merges the halves together


        }

        return  v;
    }

    //Sorts array of ints v
    public int[] sort(int[] v) {
        return mergeSort(v, 0, v.length - 1);
    }


    public int[] merge(int[] v, int start, int middle, int end) {


        //create some scratch space
        int[] scratch = new int[v.length];


        int indxb = middle + 1;
        int indxs = 0;
        for (int indxa = start; indxa <= middle; indxa++) {

            //while b is still valid AND b < a, add b to scratch
            while (indxb <= end && v[indxb] < v[indxa]) {
                scratch[indxs++] = v[indxb++];
            }

            //Now a < b, so add a to scratch
            scratch[indxs++] = v[indxa];


        }

        //Copy remaining B's to scratch
        while (indxb <= end) scratch[indxs++] = v[indxb++];


        //Creates a reference for the amount of items in the array to be sorted, it's the start point + the end point minus 1
        int numValues = end - start + 1;


        //copy one array into another array
        System.arraycopy(scratch, 0, v, start, numValues);


        return v;

    }

}
