# Segment Tree Project

## Introduction
This project implements a segment tree, a powerful data structure used to store information about intervals or segments. This allows for efficient range queries and updates, which are essential for many computational problems.

## Project Structure
```
SegmentTreeProject/
├── MaximumSegmentTreeByArray.java
├── MaximumSegmentTreeByTree.java
├── MinimumSegmentTreeByArray.java
├── MinimumSegmentTreeByTree.java
├── NumberAnalyzer.java
├── NumberAnalyzerByArrays.java
├── NumberAnalyzerByTrees.java
├── SegmentTree.java
├── SegmentTreeByArray.java
├── SegmentTreeByTree.java
├── SegmentTreeNode.java
├── SummationSegmentTreeByArray.java
├── SummationSegmentTreeByTree.java
├── Tester.java
└── README.md
```

## Description

### Segment Trees
A segment tree is a binary tree used for storing intervals or segments. It allows querying which segments contain a certain point efficiently. It is particularly useful for answering range queries such as the sum, minimum, or maximum over a range in logarithmic time.

### Implementations
This project includes the implementation of segment trees in two forms:
1. **Tree Structure**: Using tree nodes to represent segments.
2. **Array Structure**: Using arrays to simulate the segment tree.

### Features
- **Range Sum Queries**: Quickly compute the sum of elements in a range.
- **Range Minimum Queries**: Quickly find the minimum element in a range.
- **Range Maximum Queries**: Quickly find the maximum element in a range.
- **Range Updates**: Efficiently update elements and propagate changes.

## Classes

### SegmentTree
- `SegmentTreeByTree`: Abstract class for segment tree using tree nodes.
- `SegmentTreeByArray`: Abstract class for segment tree using arrays.

### Nodes
- `TreeNode`: Abstract class representing a node in a binary tree.
- `SegmentTreeNode`: Extends `TreeNode` for segment tree nodes.

### Segment Tree Implementations
- `MaximumSegmentTreeByTree`: Implementation for maximum queries using tree nodes.
- `MinimumSegmentTreeByTree`: Implementation for minimum queries using tree nodes.
- `SummationSegmentTreeByTree`: Implementation for sum queries using tree nodes.
- `MaximumSegmentTreeByArray`: Implementation for maximum queries using arrays.
- `MinimumSegmentTreeByArray`: Implementation for minimum queries using arrays.
- `SummationSegmentTreeByArray`: Implementation for sum queries using arrays.

### Number Analyzer
- `NumberAnalyzer`: Abstract class for analyzing collections of integers.
- `NumberAnalyzerByArrays`: Implementation using arrays.
- `NumberAnalyzerByTrees`: Implementation using trees.

### Tester
- `Tester`: Class containing tests for all the public methods. Ensures that there are at least 50 test cases covering a wide range of scenarios.

## Usage

### Building the Segment Tree
To build a segment tree, you need an array of integers. Depending on the type of query (sum, minimum, maximum), use the appropriate class to construct the segment tree.

### Querying the Segment Tree
To query the segment tree for a range, use the `queryRange` method provided by the specific implementation class. This method will return the result of the query (sum, minimum, or maximum) for the given range.

### Updating the Segment Tree
To update an element in the segment tree, use the `update` method. This method will modify the element and update all relevant nodes in the tree to reflect the change.

## Example
Here's an example of how to use the segment tree for sum queries:
```java
int[] data = {1, 3, 5, 7, 9, 11};
SummationSegmentTreeByArray segmentTree = new SummationSegmentTreeByArray(data);

// Query the sum of elements from index 1 to 3
int sum = segmentTree.queryRange(1, 3);
System.out.println("Sum from index 1 to 3: " + sum); // Output: 15

// Update the element at index 2 to 10
segmentTree.update(2, 10);

// Query the sum again from index 1 to 3
sum = segmentTree.queryRange(1, 3);
System.out.println("Sum from index 1 to 3 after update: " + sum); // Output: 20
```

## Testing
Run the `Tester` class to execute all the tests. Ensure that all tests pass to verify the correctness and robustness of the implementations.
