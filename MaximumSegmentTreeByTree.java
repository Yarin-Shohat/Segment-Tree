/**
 * A segment tree data structure that supports efficient range queries and updates for integers.
 * Segment tree of maximum values
 * Implement using a tree structure
 */
public class MaximumSegmentTreeByTree extends SegmentTreeByTree{

	 /** default constructor
     */
	public MaximumSegmentTreeByTree() {
		super(new int[]{});
	}
	
	/**
	 * constructor that receives array
	 * @param arr - The array to create the tree
	 */
	public MaximumSegmentTreeByTree(int[] arr) {
		super(arr);
	}
	
    /**
     * Queries the Segment Tree for the maximum value in the given range. implemented by subclasses.
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return Maximum value in the given range
     */
	@Override
	public int queryRange(int left, int right) {
		SegmentTreeNode node = this.queryRangeHelper(this.root, left, right);
		return node.getMax();
	}
	
	/**
	 * Function that active when we print the object
	 */
	public String toString() {
		String s = toStringHelper(" [", this.root);
		return s + " ] ";
	}
	
	/**
	 * Function that help the toString function recursion method
	 * @param s - The string we will return
	 * @param node - The node we work on it
	 * @return String - The string to output
	 */
	private String toStringHelper(String s, SegmentTreeNode node) {
		if(node != null) {
			s = s + " " + node.getMax();
			s = s + toStringHelper("", (SegmentTreeNode)node.getLeft());
			s = s + toStringHelper("", (SegmentTreeNode)node.getRight());
		}
		return s;
	}
}
