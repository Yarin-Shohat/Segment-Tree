/**
 * An abstract base class for a segment tree data structure implemented using a tree structure.
 * Subclasses must implement the {@code queryRange} method to provide specific range query functionality.
 */
public abstract class SegmentTreeByTree implements SegmentTree {

	/** The root of the tree  */
    protected SegmentTreeNode root;
	 /** The size of the original array that the tree build from */
    protected int size;

    /**
     * Constructor for creating a Segment Tree from an input array
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public SegmentTreeByTree(int[] arr) {
    	if(arr == null) arr = new int[] {};
        build(arr);
    }
    
    /**
     * Builds the segment tree from the given array of integers.
     *
     * @param arr the array of integers to build the segment tree from
     */
    @Override
    public void build(int[] arr) {
    	this.size = arr.length;
    	if(arr.length == 0) {
    		this.root = new SegmentTreeNode(0, 0, 0, 0, 0, null, null);
    	}
    	else {
        	this.root = create_the_tree(arr, 0, arr.length-1);
    	}
    }

    /**
     * Function to create the tree recursively 
     * @param arr - The array we work on it
     * @param start - The start index for the array(from the original array)
     * @param end - The end index of the array(from the original array)
     * @return SegmentTreeNode - Tree node
     */
    private SegmentTreeNode create_the_tree(int[] arr, int start, int end) {
    	// Get min, max and sum values
    	int min = get_min_fron_arr(arr);
    	int max = get_max_from_arr(arr);
    	int sum = get_sum_of_arr(arr);
    	// This is leaf
    	if(start == end || arr.length == 1) {
    		return new SegmentTreeNode(start, end, min, max, sum, null, null);
    	}
    	// Create two arrays for creating new sub-trees
		int mid = (start+end)/2;
		int[] left_arr = slice_array(arr, start, mid+1);
		int[] right_arr = slice_array_right(arr,start, mid+1, end+1);
		// Create two sub-trees
		SegmentTreeNode left_tree;
		SegmentTreeNode right_tree;
		if(left_arr.length == 1) {
			// we need to create a leaf
			left_tree = new SegmentTreeNode(start, mid, left_arr[0], left_arr[0], left_arr[0], null, null);
		}
		else {
			// Continue sub-trees
			left_tree = create_the_tree(left_arr, start, mid);
		}
		if(right_arr.length == 1) {
			// we need to create a leaf
			right_tree = new SegmentTreeNode(mid+1, end, right_arr[0], right_arr[0], right_arr[0], null, null);
		}
		else {
			// Continue sub-trees
			right_tree = create_the_tree(right_arr, mid+1, end);
		}
		
		return new SegmentTreeNode(start, end, min, max, sum, left_tree, right_tree);
    }
    
    /**
     * Internal function to find minimum value in array of int
     * @param arr Array of int
     * @return the min value
     */
	private int get_min_fron_arr(int[] arr) {
		if(arr.length == 0) return 0;
		int min = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
		}
	
	/**
     * Internal function to find maximun value in array of int
     * @param arr Array of int
     * @return the max value
     */
	private int get_max_from_arr(int[] arr) {
		if(arr.length == 0) return 0;
		int max = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		return max;		
	}
	
	/**
     * Internal function to find sum of array of int
     * @param arr Array of int
     * @return the sum of the array
     */
	private int get_sum_of_arr(int[] arr) {
		int sum = 0;
		for(int i = 0; i< arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	/**
	 * Interal function to slice array - the left arrays
	 * @param arr the array we want to slice
	 * @param start the start index to slice
	 * @param end the end index to slice
	 * @return new smaller array
	 */
	private int[] slice_array(int[] arr, int start, int end) {
		// Reset vars to match the array
		end = end - start;
		start = start - start;
		int[] new_arr = new int[end - start];
		// Slicing
		for(int i = start; i < end; i++) {
			new_arr[i - start] = arr[i];
		}
		return new_arr;
	}
	
	/**
	 * Interal function to slice array - the right arrays
	 * @param arr - the array we want to slice
	 * @param start_index - The real start index of the original array
	 * @param start - the start index to slice
	 * @param end - the end index to slice
	 * @return
	 */
	private int[] slice_array_right(int[] arr,int start_index, int start, int end) {
		// Reset vars to match the array
		start = start - start_index;
		end = end - start_index;
		int[] new_arr = new int[end - start];
		// Slicing
		for(int i = start; i < end; i++) {
			new_arr[i - start] = arr[i];
		}
		return new_arr;
	}


    /**
     * Updates the element at the specified index in the original array and updates the segment tree accordingly.
     *
     * @param index the index of the element to update
     * @param value the new value of the element at the specified index
     */
    @Override
	public void update(int index, int value) {
		update_leaf_and_sum(this.root, index, value);
		int[] arr = get_arr(this.root);
		update_min_and_max(arr, index, this.root);
    }
    
	/**
	 * Function that update the leaf value and the sum in the nodes in the way to the leaf
	 * @param node - The node we work on it
	 * @param index - The index of the node we need to update
	 * @param value - The new value of the node
	 * @return SegmentTreeNode object for the recursion
	 */
    private SegmentTreeNode update_leaf_and_sum(SegmentTreeNode node, int index, int value) {
    	// Initial vars
    	int start = node.getStart();
    	int end = node.getEnd();
    	int mid = (start+end)/2;
    	if(index > end || index < start) return null;
    	// Save old values
    	int old_value = queryRangeHelper(node, index, index).getSum();
    	int old_sum = node.getSum();
    	// Update the values
    	node.setSum(old_sum-old_value+value);
    	// If we leaf, we need to exit
    	if(node.isLeaf()) return null;
    	// Continue recursive 
    	if(index <= mid) {
    		return update_leaf_and_sum((SegmentTreeNode)node.getLeft(), index, value);
    	}
    	else if(index > mid) {
    		return update_leaf_and_sum((SegmentTreeNode)node.getRight(), index, value);
    	}
    	return null;
    }

    /**
     * Function that create array from the tree
     * @return int[] array from the leafs of the tree
     */
    private int[] get_arr(SegmentTreeNode node) {
    	int[] arr = new int[this.size];
    	for(int i = 0; i < this.size ; i++) {
    		arr[i] = queryRangeHelper(node, i, i).getSum();
    	}
    	return arr;
    }
    
    /**
     * Function that update the min and max value in the node we updated
     * @param arr - The array we work on it
     * @param index - The index of the node we updated
     * @param node - The node we work on it
     * @return SegmentTreeNode for the recursion
     */
    private SegmentTreeNode update_min_and_max(int[] arr, int index, SegmentTreeNode node) {
    	int start = node.getStart();
    	int end = node.getEnd();
    	// Update the min and max
		int new_min = get_min_fron_arr(arr);
		int new_max = get_max_from_arr(arr);
    	node.setMax(new_max);
		node.setMin(new_min);
		// End recursion
    	if((node.getStart() == index && node.getEnd() == index) || node.isLeaf()) {
    		node.setMax(node.getSum());
    		node.setMin(node.getSum());
    		return null;
    	}
    	// Create two arrays for creating new sub-trees
    	int mid = (node.getStart()+node.getEnd())/2;
		int[] left_arr = slice_array(arr, start, mid+1);
		int[] right_arr = slice_array_right(arr,start, mid+1, end+1);
		// Create two sub-trees
    	if(index <= mid) {
    		new_min = get_min_fron_arr(left_arr);
    		new_max = get_max_from_arr(left_arr);
    		return update_min_and_max(left_arr, index, (SegmentTreeNode)node.getLeft());
    	}
    	else {
    		new_min = get_min_fron_arr(right_arr);
    		new_max = get_max_from_arr(right_arr);
    		return update_min_and_max(right_arr, index, (SegmentTreeNode)node.getRight());
    	}
    }

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     *
     * @return the size of the original array
     */
    @Override
    public int size() {
      return this.size;
    }

    /**
     * Queries the Segment Tree for the minimum value in the given range. to be implemented by subclasses.
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return Minimum value in the given range
     */
    @Override
    public abstract int queryRange(int left, int right);

    /**
     * Helper method for querying the Segment Tree 
     * @param node Current node of the Segment Tree
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return A SegmentTreeNode that contains the minimum, maximum and sum values for the given range
     */
    protected SegmentTreeNode queryRangeHelper(SegmentTreeNode node, int left, int right) {
    	int start = node.getStart();
        int end = node.getEnd();
        // Fix left and right if they came opposite
    	if(left > right) {
        	int temp_left = right;
        	right = left;
        	left = temp_left;
        }
    	if(start >= left && end <= right) {
    		return node;
    	}
    	// If we are leaf, return the node
    	if(node.isLeaf()) {
    		return node;
    	}
    	// If left and right equals to the node start and end, return the node
    	if(start == left && end == right) {
    		return node;
    	}
    	// Get half of the node index
    	int mid = (start+end)/2;
    	if(right <= mid) {
    		// Left sub tree
    		return queryRangeHelper((SegmentTreeNode)node.getLeft(), left, right);
    	}
    	else if(left > mid) {
    		// Right sub tree
    		return queryRangeHelper((SegmentTreeNode)node.getRight(), left, right);
    	}
    	// Part of left tree and part of right tree
    	SegmentTreeNode node_from_left_tree = queryRangeHelper((SegmentTreeNode)node.getLeft(), left, mid);
    	SegmentTreeNode node_from_right_tree = queryRangeHelper((SegmentTreeNode)node.getRight(), mid+1, right);
    	// Get min, max and sum value of the new node
    	int sum = node_from_left_tree.getSum() + node_from_right_tree.getSum();
    	int min = get_min_from(node_from_left_tree, node_from_right_tree);
    	int max = get_max_from(node_from_left_tree, node_from_right_tree);
    	
    	return new SegmentTreeNode(start, end, min, max, sum, null, null);
		 // use this function for implementing the queryRange in the subclasses.
    }
    
    /**
     * Function that recive 2 nodes and return the min value from the nodes
     * @param node1 - First node
     * @param node2 - Second node
     * @return Min value from 2 nodes
     */
    private int get_min_from(SegmentTreeNode node1, SegmentTreeNode node2) {
    	if(node1.getMin() < node2.getMin()) {
    		return node1.getMin();
    	}
    	return node2.getMin();
    }
    
    /**
     * Function that recive 2 nodes and return the max value from the nodes
     * @param node1 - First node
     * @param node2 - Second node
     * @return The max value from the nodes
     */
    private int get_max_from(SegmentTreeNode node1, SegmentTreeNode node2) {
    	if(node1.getMax() > node2.getMax()) {
    		return node1.getMax();
    	}
    	return node2.getMax();
    }
}
