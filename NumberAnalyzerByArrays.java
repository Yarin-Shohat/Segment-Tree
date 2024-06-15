/**
 The NumberAnalyzer class provides an abstract implementation for analyzing a collection of integers.
 Using SegmentTreeByArray classes that implement using using an array
 */
public class NumberAnalyzerByArrays extends NumberAnalyzer{

	/** The maximum tree */
	MaximumSegmentTreeByArray tree_max;
	/** The minimum tree */
	MinimumSegmentTreeByArray tree_min;
	/** The sum tree */
	SummationSegmentTreeByArray tree_sum;
	
	/** default constructor
	 */
	public NumberAnalyzerByArrays() {
		super(new Integer[]{});
		this.tree_max = new MaximumSegmentTreeByArray();
		this.tree_min = new MinimumSegmentTreeByArray();
		this.tree_sum = new SummationSegmentTreeByArray();
	}
	
	/**
	 * Constactor that receives Integer array
	 * @param numbers - Integer Array
	 */
	public NumberAnalyzerByArrays(Integer[] numbers) {
		super(numbers);
		int[] new_numbers = de_Wrapper(numbers);
		this.tree_max = new MaximumSegmentTreeByArray(new_numbers);
		this.tree_min = new MinimumSegmentTreeByArray(new_numbers);
		this.tree_sum = new SummationSegmentTreeByArray(new_numbers);
	}

    /**
     * Returns the maximum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The maximum value in the range.
     */
	@Override
	public Integer getMax(int left, int right) {
		return this.tree_max.queryRange(left, right);
	}

    /**
     * Returns the minimum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The minimum value in the range.
     */
	@Override
	public Integer getMin(int left, int right) {
		return this.tree_min.queryRange(left, right);
	}

    /**
     * Returns the sum of the values in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The sum of the values in the range.
     */
	@Override
	public Integer getSum(int left, int right) {
		return this.tree_sum.queryRange(left, right);
	}

    /**
     * Updates the value at the given index. This is an abstract function to be implemented by the subclasses
     * @param index The index of the value to be updated.
     * @param value The new value to be set at the given index.
     */
	@Override
	public void update(int index, int value) {
		this.tree_max.update(index, value);
		this.tree_min.update(index, value);
		this.tree_sum.update(index, value);
		this.numbers[index] = (Integer)value;
	}

}
