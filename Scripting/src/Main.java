import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by austin on 6/8/16.
 */
public class Main {
    public static void main(String[] args) {

        //            0     2           6
        int[] test = {1, 3, 8, 1, 5, 6, 9};
        int []  test2 =  {3453, 435, 34, 23,423, 4, 53,4, 5};

        MergeSort sorted = new MergeSort();

                                    //start, middle, end

        int [] s = sorted.merge(test, 0, 2, 6);
        int [] a = sorted.sort(test2);

        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(a));

    }
}
