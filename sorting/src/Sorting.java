
import java.io.*;
import java.util.*;

public class Sorting {

	public static void insertionSort(int[] A) {
		int n = A.length;
		for (int j = 1; j < n; j++) {
			int key = A[j];
			int i = j - 1;
			while (i >= 0 && A[i] > key) {
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = key;
		}
	}

	public static void mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	public static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0; i < n1; i++)
			L[i] = A[p + i];
		for (int j = 0; j < n2; j++)
			R[j] = A[q + 1 + j];
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		int i = 0, j = 0;
		for (int k = p; k <= r; k++) {
			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}

	public static void maxHeapify(int[] A, int i, int n) {
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		int largest;
		if (l < n && A[l] > A[i])
			largest = l;
		else
			largest = i;
		if (r < n && A[r] > A[largest])
			largest = r;
		if (largest != i) {
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			maxHeapify(A, largest, n);
		}
	}

	public static void heapSort(int[] A) {
		int n = A.length;
		for (int i = n / 2 - 1; i >= 0; i--)
			maxHeapify(A, i, n);
		for (int i = n - 1; i > 0; i--) {
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			maxHeapify(A, 0, i);
		}
	}

	public static void quickSort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);
		}
	}

	public static int partition(int[] A, int p, int r) {
		int x = A[r];
		int i = p - 1;

		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= x) {
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}

		int temp = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp;

		return i + 1;
	}

	public static int[] readInputFile(String filename) {
		List<Integer> numbers = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				numbers.add(Integer.parseInt(line));
			}
		} catch (IOException e) {
			System.out.println("Error while reading: " + filename);
		}
		return numbers.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void writeOutputFile(String filename, int[] array) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			for (int val : array) {
				writer.write(val + "\n");
			}
		} catch (IOException e) {
			System.out.println("Error while writing: " + filename);
		}
	}

	public static int[] generateIncreasingInput(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i;
		}
		return array;
	}

	public static int[] generateDecreasingInput(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = size - i - 1;
		}
		return array;
	}

	public static void sortOperations(String algorithm, String dataType, String sizeLabel, int[] input) {
		int[] arr = input.clone();
		long startTime = System.nanoTime();

		switch (algorithm) {
		case "insertionsort":
			insertionSort(arr);
			break;
		case "mergesort":
			mergeSort(arr, 0, arr.length - 1);
			break;
		case "heapsort":
			heapSort(arr);
			break;
		case "quicksort":
			quickSort(arr, 0, arr.length - 1);
			break;
		}

		long endTime = System.nanoTime();

		long durationMs = (endTime - startTime) / 1_000_000;

		System.out.println(algorithm + " on " + sizeLabel + " " + dataType + " -> " + durationMs + " ms");

		String outputName = algorithm + "_" + sizeLabel + "_" + dataType + "_output.txt";
		writeOutputFile(outputName, arr);
	}

	public static void main(String[] args) {

		String[] algorithms = { "insertionsort", "mergesort", "heapsort", "quicksort" };
		String[] sizeLabels = { "1K", "10K", "100K" };
		String[] fileNames = { "1K_random_input.txt", "10K_random_input.txt", "100K_random_input.txt" };
		int[] sizes = { 1000, 10000, 100000 };

		for (int i = 0; i < sizes.length; i++) {

			int[] increasingData = generateIncreasingInput(sizes[i]);
			int[] decreasingData = generateDecreasingInput(sizes[i]);

			for (String algorithm : algorithms) {
				int[] randomData = readInputFile(fileNames[i]);
				sortOperations(algorithm, "random", sizeLabels[i], randomData);
				sortOperations(algorithm, "increasing", sizeLabels[i], increasingData);
				sortOperations(algorithm, "decreasing", sizeLabels[i], decreasingData);
			}
		}
	}

	// for testing if my sorts working true
	public static void testAllSortsOnSmallInput() {
		String filename = "test_input_small.txt";
		int[] originalData = readInputFile(filename);

		String[] algorithms = { "insertionsort", "mergesort", "heapsort", "quicksort" };

		for (String algorithm : algorithms) {
			int[] arr = originalData.clone();
			System.out.println(algorithm + ": " + Arrays.toString(arr));

			switch (algorithm) {
			case "insertionsort":
				insertionSort(arr);
				break;
			case "mergesort":
				mergeSort(arr, 0, arr.length - 1);
				break;
			case "heapsort":
				heapSort(arr);
				break;
			case "quicksort":
				quickSort(arr, 0, arr.length - 1);
				break;
			}

			System.out.println("Sorted with " + algorithm + ": " + Arrays.toString(arr));

		}
	}

}
