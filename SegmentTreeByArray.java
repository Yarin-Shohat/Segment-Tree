/**
 * This abstract class represents a segment tree implementation using an array
 * and provides methods to build, update, and query the tree.
 */
public abstract class SegmentTreeByArray implements SegmentTree {
     
	/** The tree in array */
	 protected int[] tree;
	 /** The size of the original array that the tree build from */
	 protected int size;

    /**
     * Constructor for initializing the segment tree with the given input array.
     * @param arr the input array
     */
    public SegmentTreeByArray(int[] arr) {
    	if(arr == null) arr = new int[] {};
    	
    	this.size = arr.length;
    	int tree_size = 2 * (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
    	this.tree = new int[tree_size];
    	// Reset all values
    	INIT_TREE();
    	// Create the tree
        if(arr.length != 0) {
        	build(arr);
        }
    }

    /**
     * Builds the segment tree from the input array.
     * @param arr the input array
     */
    @Override
    public void build(int[] arr) {
    	buildHelper(arr, 0, arr.length-1, 1, 2);
    }
    
    /**
     * Function to help build the tree array recursive method
     * @param arr - The array we work on it
     * @param start - Array start index
     * @param end - Array end index
     * @param tree_start - Tree level(Start index in tree)
     * @param tree_end - Tree next level(End index in tree)
     */
    private void buildHelper(int[] arr, int start, int end, int tree_start, int tree_end) {
    	// Create leaf
    	if(arr.length == 1) {
    		this.tree[tree_start] = arr[0];
    		return;
    	}
    	// Create 2 leaves
    	if(arr.length == 2) {
    		end = end - start;
    		start = 0;
    		this.tree[tree_start] = get_info_from_arr(arr, start, end+1);
    		this.tree[tree_start*2] = arr[start];
    		this.tree[tree_start*2+1] = arr[end];
    		return;
    	}
    	// Get value to correct index
        this.tree[tree_start] = get_info_from_arr(arr, start, end+1);
        // Slice and continue recursive
    	int mid = (start+end)/2;
		int[] left_arr = slice_array(arr, start, mid+1);
		int[] right_arr = slice_array_right(arr,start, mid+1, end+1);

    	buildHelper(left_arr, start, mid, tree_start*2, tree_end*2);
    	buildHelper(right_arr, mid+1, end, tree_start*2+1, tree_end*2);
    }
        
     /**
     * Internal function that every sub-class Returns the corresponding value
     * Max from MaximumSegmentTreeByArray
     * Min from MinimumSegmentTreeByArray
     * Sum from SummationSegmentTreeByArray
     * @param arr - int array
     * @param start - start index
     * @param end - end index
     * @return - Max/Min/Sum of the array
     */
    protected abstract int get_info_from_arr(int[] arr, int start, int end);
    
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
	 * Internal function that go throw the array and reset all of the values
	 */
	private void INIT_TREE() {
		for(int i = 0 ; i < this.tree.length ; i++) {
			this.tree[i] = Integer.MIN_VALUE;
		}
	}
	
    /**
     * Updates the value at the specified index and updates the segment tree accordingly.
     * @param index the index of the element to update in the array
     * @param value the new value to replace the existing value
     */
    @Override
    public void update(int index, int value) {
    	if(this.size == 0) return;
    	update_the_item(index+1, 1, this.size, value, 1);
    }
    
    /**
     * Internal function that gets 2 numbers and return max/min/sum of them
     * Depends where from did you call it
     * Max - from MaximumSegmentTreeByArray
     * Min - from MinimumSegmentTreeByArray
     * Sum - from SummationSegmentTreeByArray
     * @param num1 - First number
     * @param num2 - Second number
     * @return - The max/min/sum
     */
    protected abstract int get_info_from_childs(int num1, int num2);
    
    private void update_the_item(int index, int start, int end, int value, int level) {
    	if(this.tree.length > level*2) {
    		// if we are leaf
    		if(this.tree[level*2] == Integer.MIN_VALUE && this.tree[level*2+1] == Integer.MIN_VALUE) {
    			this.tree[level] = value;
    		}
    		// We are not leaf
    		else {
    			int mid = (start+end)/2;
    			// Go left
    			if(index <= mid) {
    				update_the_item(index, start, mid, value, level*2);
    				this.tree[level] = get_info_from_childs(this.tree[level*2], this.tree[level*2+1]);
    			}
    			// Go right
    			else {
    				update_the_item(index, mid+1, end, value, level*2+1);
    				this.tree[level] = get_info_from_childs(this.tree[level*2], this.tree[level*2+1]);
    			}
    		}
    	}
    	// We are leaf
    	else {
    		this.tree[level] = value;
    	}
    }


    /**
     * Queries the segment tree for a range of elements.
     * @param left the left index of the range
     * @param right the right index of the range
     * @return the result of the query operation
     */
    @Override
    public int queryRange(int left, int right) {
    	// If empty, return 0
    	if(this.size == 0) return (Integer) null;

        // Fix left and right if they came opposite
    	if(left > right) {
        	int temp_left = right;
        	right = left;
        	left = temp_left;
        }
    	
    	return queryHelper(1, 1, this.size, left+1, right+1);
    }
    
    /**
     * Function that help get information from the tree recursive method
     * @param index - The index we work on it
     * @param start - The start index we work on it in the recursion
     * @param end - The end index we work on it in the recursion
     * @param left -  The left index to query - Giver from user
     * @param right - The right index to query - Given from user
     * @return
     */
    protected int queryHelper(int index, int start, int end, int left, int right) {
    	if(this.tree.length > index*2) {
    		// if we are leaf
    		if(this.tree[index*2] == Integer.MIN_VALUE && this.tree[index*2+1] == Integer.MIN_VALUE) {
    			return this.tree[index];
    		}
    		// We are not leaf
    		else {
    			int mid = (start+end)/2;
    			// GO left
    			if(right <= mid) {
    				return queryHelper(index*2, start, mid, left, right);
    			}
    			// GO right
    			else if(left > mid) {
    				return queryHelper(index*2+1, mid+1, end, left, right);
    			}
    			// Go left and right
    			else {
        			return query(index, start, end, left, right);
    			}
    		}
    	}
    	else {
    		// We are leaf
    		return this.tree[index];
    	}
    }

    /**
     * Abstract method for query operation, to be implemented by subclasses.
     * @param node the current node
     * @param start the start index
     * @param end the end index
     * @param left the left index
     * @param right the right index
     * @return the result of the query operation
     */
    protected abstract int query(int node, int start, int end, int left, int right);


    /**
     * The members inside the array representing the segment tree are printed according to their indexes in the array.
	 * When the members are surrounded by "[ ]" and exactly one space between each number and between the brackets. 
	 * For example, for the tree [10,4,6,1,3,2,4] " [ 10 4 6 1 3 2 4 ] " will be returned 
     */
    @Override
    public String toString() {
      String arr_text = " [";
      for(int i = 1 ; i < this.tree.length ; i++) {
    	  if(this.tree[i] != Integer.MIN_VALUE) {
    		  arr_text = arr_text + " " + this.tree[i];
    	  }
    	  else {
    		  arr_text = arr_text + " -";
    	  }
      }
      return arr_text + " ] ";
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
}