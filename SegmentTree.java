/**
 * A segment tree data structure that supports efficient range queries and updates for integers.
 */
public interface SegmentTree {

    /**
     * Builds the segment tree from the given array of integers.
     * @param arr the array of integers to build the segment tree from
     */
    void build(int[] arr);

    /**
     * Queries the segment tree for an aggregate value over the specified range.
     * @param left the left endpoint of the range (inclusive)
     * @param right the right endpoint of the range (inclusive)
     * @return the result of the range query
     */
    int queryRange(int left, int right);

    /**
     * Updates the element at the specified index in the original array and updates the segment tree accordingly.
     * @param index the index of the element to update
     * @param value the new value of the element at the specified index
     */
    void update(int index, int value);

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     * @return the size of the original array
     */
    int size();

    /**
     * Returns a string representation of the segment tree.
     * @return a string representation of the segment tree
     */
    String toString();
}