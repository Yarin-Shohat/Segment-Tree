public class SegmentTreeNode extends TreeNode {
    private int start;
    private int end;
    private int min;
    private int max;
    private int sum;

    /**
     * Constructs a new SegmentTreeNode with the given start and end indices.
     *
     * @param start The start index of the segment.
     * @param end The end index of the segment.
     */
    public SegmentTreeNode(int start, int end) {
        super();
        this.start = start;
        this.end = end;
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
        this.sum = 0;
    }

    /**
     * Constructs a new SegmentTreeNode with the given start and end indices.
     *
     * @param start The start index of the segment.
     * @param end The end index of the segment.
     */
    public SegmentTreeNode(int start, int end, int min, int max, int sum,SegmentTreeNode left,SegmentTreeNode right) {
        super(left,right);
        this.start = start;
        this.end = end;
        this.min = min;
        this.max = max;
        this.sum = sum;
    }

    /**
     * Returns the start index of the segment represented by this node.
     *
     * @return The start index of the segment.
     */
    public int getStart() {
        return start;
    }

    /**
     * Returns the end index of the segment represented by this node.
     *
     * @return The end index of the segment.
     */
    public int getEnd() {
        return end;
    }

    /**
     * Returns the minimum value of the segment represented by this node.
     *
     * @return The minimum value of the segment.
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the minimum value of the segment represented by this node.
     *
     * @param min The minimum value of the segment.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Returns the maximum value of the segment represented by this node.
     *
     * @return The maximum value of the segment.
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the maximum value of the segment represented by this node.
     *
     * @param max The maximum value of the segment.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns the sum of the segment represented by this node.
     *
     * @return The sum of the segment.
     */
    public int getSum() {
        return sum;
    }

    /**
     * Sets the sum of the segment represented by this node.
     *
     * @param sum The sum of the segment.
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "] " + "min=" + min + " max=" + max + " sum=" + sum;
    }
}