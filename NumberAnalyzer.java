import java.util.Comparator;
import java.util.Iterator;

/**
 The NumberAnalyzer class provides an abstract implementation for analyzing a collection of integers.
 It implements the Iterable interface to allow for iteration over the collection, and the Comparator interface
 to provide a default comparison method for integers.
 */
public abstract class NumberAnalyzer implements Iterable<Integer>, Comparator<Integer> {

	//** Array of numbers that will build from if the trees */
    protected Integer[] numbers;
    /** The size of the array */
    protected int size;

    /**
     * Constructs a new NumberAnalyzer object with the given array of integers.
     * @param numbers The array of integers to be analyzed.
     */
    public NumberAnalyzer(Integer[] numbers) {
        this.numbers = numbers;
        this.size = numbers.length;
    }
    
    /**
     * Function that create Iterator object to go throw the numbers Array
     * @return Iterator Object
     */
	@Override
	public Iterator<Integer> iterator() {
		return new NumberAnalyzerIterator(this.numbers, this.size);
	}
	
	/**
	 * Inner Class for the Iterator
	 * Create the Iterator
	 */
	public class NumberAnalyzerIterator implements Iterator<Integer> {
		
		/** Array numbers */
	    protected Integer[] numbers;
	    /** The size of the array */
	    protected int size;
	    /** The current index that the Iterator is on it */
	    protected int currentIndex;
		
	    /**
	     * Constractor for the Iterator
	     * @param numbers
	     * @param size
	     */
		public NumberAnalyzerIterator(Integer[] numbers, int size) {
			this.size = size;
			this.numbers = numbers;
			this.currentIndex = 0;
		}
		
		/**
		 * Function that return true if the Iterator has next element
		 * return false if the Iterator don't have next element
		 */
		@Override
		public boolean hasNext() {
			if(this.currentIndex < this.size) return true;
			else return false;
		}

		/**
		 * Function that return the next element if he exist
		 * If don't exist, throw ERROR
		 */
		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new RuntimeException("No more elements");
			}
			else {
				Integer num = this.numbers[currentIndex];
				currentIndex++;
				return num;
			}
		}
	}
	
	/**
	 * Function that compare between 2 Integers
	 * Even number greater then odd number
	 * @Return 1 if o1 > o2
	 * @Return -1 if o1 < o2
	 * @Return 0 if o1 = o2
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		// o1 even, o2 odd
		if(o1%2 == 0 && o2%2 == 1) return 1;
		// o1 odd, o2 even
		else if(o1%2 == 1 && o2%2 == 0) return -1;
		// Both even or odd, compare regular
		else return o1.compareTo(o2);
	}
	
	/**
	 * Function that convert Integer array to int array
	 * @param numbers - Integer array
	 * @return int[] array
	 */
	protected int[] de_Wrapper(Integer[] numbers) {
		int[] new_array = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++) {
			new_array[i] = numbers[i];
		}
		return new_array;
	}
    
    /**
     * Returns the maximum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The maximum value in the range.
     */
    public abstract Integer getMax(int left, int right);

    /**
     * Returns the minimum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The minimum value in the range.
     */
    public abstract Integer getMin(int left, int right);

    /**
     * Returns the sum of the values in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The sum of the values in the range.
     */
    public abstract Integer getSum(int left, int right);

    /**
     * Updates the value at the given index. This is an abstract function to be implemented by the subclasses
     * @param index The index of the value to be updated.
     * @param value The new value to be set at the given index.
     */
    public abstract void update(int index, int value);



}