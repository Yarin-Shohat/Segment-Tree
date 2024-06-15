/**
 * A segment tree data structure that supports efficient range queries and updates for integers.
 * Segment tree of minimum values
 * Implement using using an array
 */
public class MinimumSegmentTreeByArray extends SegmentTreeByArray{

	 /** default constructor
	 */
	public MinimumSegmentTreeByArray() {
		super(new int[]{});
	}
	
	/**
	 * constructor that receives array
	 * @param arr - The array to create the tree
	 */
	public MinimumSegmentTreeByArray(int[] arr) {
		super(arr);
	}

    /**
     * Abstract method for query operation, to be implemented by subclasses.
     * Function that return the min from two sub-trees
     * @param node the current node
     * @param start the start index
     * @param end the end index
     * @param left the left index
     * @param right the right index
     * @return the result of the query operation
     */
	@Override
	protected int query(int node, int start, int end, int left, int right) {
		// Help get max from left and right
		int mid = (start+end)/2;
		int left_num = queryHelper(node*2, start, mid, left, right);
		int right_num = queryHelper(node*2+1, mid+1, end, left, right);
		return Math.min(left_num, right_num);
	}
	
	/**
	 * Internal function that return the max value of the array
	 * min - from MinimumSegmentTreeByArray
	 * @param arr - int array
	 * @param start - start index
     * @param end - end index
	 * @return - Max of the array
	 */
	@Override
	protected int get_info_from_arr(int[] arr, int start, int end) {
		end = end - start;
		start = 0;
		int min = arr[start];
		for(int i = 0; i < end; i++) {
			if(min > arr[i]) {
				min = arr[i];
			}
		}
		return min;
	}

    /**
     * Internal function that gets 2 numbers and return max/min/sum of them
     * Depends where from did you call it
     * Min - from MinimumSegmentTreeByArray
     * @param num1 - First number
     * @param num2 - Second number
     * @return - The min
     */
	@Override
	protected int get_info_from_childs(int num1, int num2) {
		return Math.min(num1, num2);
	}


}
