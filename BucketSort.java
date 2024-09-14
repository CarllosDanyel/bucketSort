
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    public static void bucketSort(int[] arr, int numBuckets) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int minValue = arr[0];
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        int bucketSize = (maxValue - minValue) / numBuckets + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            buckets.get(bucketIndex).add(arr[i]);
        }

        int currentIndex = 0;
        for (int i = 0; i < numBuckets; i++) {
            Collections.sort(buckets.get(i));
            for (int j = 0; j < buckets.get(i).size(); j++) {
                arr[currentIndex++] = buckets.get(i).get(j);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 13};
        int numBuckets = 5;

        System.out.println("Array antes da ordenação:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        long startTime = System.nanoTime();
        bucketSort(arr, numBuckets);
        long endTime = System.nanoTime();

        System.out.println("\nArray depois da ordenação:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        long duration = (endTime - startTime) / 1000000;

        System.out.println("\nTempo de execução: " + duration + " ms");
    }
}
