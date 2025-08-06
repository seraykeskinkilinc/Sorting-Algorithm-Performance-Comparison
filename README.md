# Sorting Algorithm Performance Comparison

This project implements and compares the performance of four sorting algorithms in Java:

- Insertion Sort
- Merge Sort
- Heap Sort
- Quick Sort

The goal is to evaluate their efficiency on various types and sizes of input data.

---

## 🧠 Algorithm Summary

Each algorithm is implemented in the main Java class `Sorting.java`, located in the `src` folder. The program performs the following:

- Reads **random integers** from input files
- Generates **increasing** and **decreasing** order arrays
- Sorts each type of data using all 4 algorithms
- Measures execution time using `System.nanoTime()`
- Outputs results into text files with standardized naming

---

## 🖥️ System Info

Experiments were run on the following machine:

- **Processor**: Apple M2  
- **RAM**: 16 GB  
- **OS**: macOS  
- **Java Version**: Java SE 1.8  
- **IDE**: Eclipse 2023-12  

---

## 📈 Observations & Results

### ✅ Insertion Sort
- **Best** on small or already sorted (increasing) data → `0 ms even for 100K`
- **Worst** on reverse-sorted (decreasing) data → `1142 ms for 100K`

### ✅ Merge Sort
- **Most consistent and stable**
- Reliable across all input types and sizes
- Completed all tasks in a few milliseconds

### ✅ Heap Sort
- Performed consistently well
- Slightly **slower than Merge Sort**, but no major weaknesses

### ❌ Quick Sort
- **Very fast on random data**
- **Extremely poor** performance on sorted and reverse-sorted inputs due to bad pivot choices
- Caused `StackOverflowError` in early tests
- Took nearly `4 seconds` for 100K increasing/decreasing inputs

---

## 🏆 Conclusion

- **Merge Sort** was the most reliable overall.
- **Quick Sort** showed the fastest results on some inputs but worst on others.
- **Insertion Sort** is only viable for small or sorted data.
- **Heap Sort** is solid but not the best.

> The project clearly demonstrates the **importance of input data and pivot selection** when comparing sorting algorithms.

---

## 📁 File Structure

- `Sorting.java`: Main source code
- `1K_random_input.txt`, `10K_random_input.txt`, `100K_random_input.txt`: Input data
- Output files: Generated for each algorithm + input type + input size

---

## 📝 Author

- **Name**: Seray Keskinkılınç  

- **Course**: CME2204 - Algorithm Analysis  
- **Semester**: 2024-2025 Spring  
