/**
 * A segment tree data structure that supports efficient range queries and updates for integers.
 * Segment tree of sum values
 * Implement using using an array
 */
public class SummationSegmentTreeByArray extends SegmentTreeByArray{

	 /** default constructor
	 */
	public SummationSegmentTreeByArray() {
		super(new int[]{});
	}
	
	/**
	 * constructor that receives array
	 * @param arr - The array to create the tree
	 */
	public SummationSegmentTreeByArray(int[] arr) {
		super(arr);
	}
	
    /**
     * Abstract method for query operation, to be implemented by subclasses.
     * Function that return the sum from two sub-trees
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
		return left_num + right_num;
	}

	/**
	 * Internal function that return the max value of the array
	 * Sum - from SummationSegmentTreeByArray
	 * @param arr - int array
	 * @param start - start index
     * @param end - end index
	 * @return - Sum of the array
	 */
	@Override
	protected int get_info_from_arr(int[] arr, int start, int end) {
		end = end - start;
		start = 0;
		int sum = 0;
		for(int i = 0; i < end; i++) {
			sum += arr[i];
		}
		return sum;
	}

    /**
     * Internal function that gets 2 numbers and return max/min/sum of them
     * Depends where from did you call it
     * Sum - from SummationSegmentTreeByArray
     * @param num1 - First number
     * @param num2 - Second number
     * @return - The sum
     */
	@Override
	protected int get_info_from_childs(int num1, int num2) {
		return num1 + num2;

	}


}
