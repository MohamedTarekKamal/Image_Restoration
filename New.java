import java.util.Arrays;

public class New {

    // Iterative Bubble Sort
    public static void iterativeBubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Recursive Bubble Sort
    public static void recursiveBubbleSort(int[] arr, int n) {
        if (n == 1) return;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        recursiveBubbleSort(arr, n - 1);
    }

    // Iterative Selection Sort
    public static void iterativeSelectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Recursive Selection Sort
    public static void recursiveSelectionSort(int[] arr, int n, int i) {
        if (i == n) return;
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        int temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
        recursiveSelectionSort(arr, n, i + 1);
    }

    // Iterative Insertion Sort
    public static void iterativeInsertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Recursive Insertion Sort
    public static void recursiveInsertionSort(int[] arr, int n) {
        if (n <= 1) return;
        recursiveInsertionSort(arr, n - 1);
        int key = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        // Example usage of sorting algorithms
        System.out.println("Choose the operation you want to try:");
        System.out.println("1. Try Sorting Algorithms");
        System.out.println("2. Try Searching Algorithms");
        System.out.println("3. Try Both");

        // Example array to sort
        int[] arr = {64, 25, 12, 22, 11};
        int[] arr2 = Arrays.copyOf(arr, arr.length);

        // Iterative Bubble Sort Test
        long startTime = System.nanoTime();
        iterativeBubbleSort(arr);
        long endTime = System.nanoTime();
        System.out.println("Time taken to sort using Iterative Bubble Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Reset array for other sorting algorithms
        arr = new int[]{64, 25, 12, 22, 11};

        // Recursive Bubble Sort Test
        startTime = System.nanoTime();
        recursiveBubbleSort(arr, arr.length);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Recursive Bubble Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Iterative Selection Sort Test
        startTime = System.nanoTime();
        iterativeSelectionSort(arr);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Iterative Selection Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Reset array for other sorting algorithms
        arr = new int[]{64, 25, 12, 22, 11};

        // Recursive Selection Sort Test
        startTime = System.nanoTime();
        recursiveSelectionSort(arr, arr.length, 0);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Recursive Selection Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Iterative Insertion Sort Test
        startTime = System.nanoTime();
        iterativeInsertionSort(arr);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Iterative Insertion Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Reset array for other sorting algorithms
        arr = new int[]{64, 25, 12, 22, 11};

        // Recursive Insertion Sort Test
        startTime = System.nanoTime();
        recursiveInsertionSort(arr, arr.length);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Recursive Insertion Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Quick Sort Test
        arr = new int[]{64, 25, 12, 22, 11};
        startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Quick Sort: " + (endTime - startTime) / 1000000.0 + " ms");

        // Merge Sort Test
        arr = new int[]{64, 25, 12, 22, 11};
        startTime = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort using Merge Sort: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}