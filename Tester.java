import java.util.Comparator;
import java.util.Iterator;

/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {
    	
        			//######################## SegmentTrees ########################
    								///////// SegmentTreeByTree /////////
        testMaximumSegmentTreeByTree();
        testMinimumSegmentTreeByTree();
        testSummationSegmentTreeByTree();
        							///////// SegmentTreeByArray /////////
        testMaximumSegmentTreeByArray();
        testMinimumSegmentTreeByArray();
        testSummationSegmentTreeByArray();

					//######################## NumberAnalyzers ########################
        //SegmentTreeByTree
        testNumberAnalyzerByTrees();
        //SegmentTreeByArray
        testNumberAnalyzerByArray();
        
        // Comparator
        testComparator();
        // Iterator
        testIterator();

        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }

    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }

    /**
     * Checks the MaximumSegmentTreeByTree class.
     */
    private static void testMaximumSegmentTreeByTree() {
        MaximumSegmentTreeByTree mstbt = new MaximumSegmentTreeByTree();
        
        // Test 1
		mstbt = new MaximumSegmentTreeByTree(new int[]{0});
        test(mstbt.toString().equals(" [ 0 ] "),"The toString of {0} should be ' [ 0 ] ' got: '" + mstbt.toString()+ " '");
        // Test 2, 3
		mstbt = new MaximumSegmentTreeByTree(new int[]{1,2,3,4});
		test(mstbt.queryRange(1,2) == 3, "The max of {1,2,3,4} between indexes [1:2] should be 3");
        test(mstbt.size() == 4, "The size of {1,2,3,4} should be 4");
        // Test 4
		mstbt = new MaximumSegmentTreeByTree(new int[]{8,2,8,6,0});
		test(mstbt.queryRange(0,2) == 8, "The max of {8,2} between indexes [0:2] should be 8");
        // Test 5, 6, 7
		mstbt = new MaximumSegmentTreeByTree(new int[]{60,10,5,15,6});
        test(mstbt.queryRange(0,4) == 60, "The max of {60,10,5,15,6} between indexes [0:4] should be 60");
        test(mstbt.queryRange(4,4) == 6, "The max of {60,10,5,15,6} between indexes [4:4] should be 6");
        test(mstbt.size() == 5, "The size of {60,10,5,15,6} should be 5");
        // Test 8
        mstbt.update(1,80);
        test(mstbt.queryRange(0,4) == 80, "After update index 1 from {60,10,5,15,6} to 80, the max between indexes [0:4] should be 80");
        // Test 9
        test(mstbt.toString().equals(" [ 80 80 80 60 80 5 15 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 80 80 80 60 80 5 15 15 6 ] ' got: '" + mstbt.toString()+ " '");		
        // Test 10
        mstbt = new MaximumSegmentTreeByTree((new int[]{-9, -8, -7, -6, 1, -10, 100}));
        test(mstbt.toString().equals(" [ 100 -6 -8 -9 -8 -6 -7 -6 100 1 1 -10 100 ] "),"The toString of {-9, -8, -7, -6, 1, -10, 100} should be ' [ 100 -6 -8 -9 -8 -6 -7 -6 100 1 1 -10 100 ] ' got: '" + mstbt.toString()+ " '");		
    }
    
    /**
     * Checks the MinimumSegmentTreeByTree class.
     */
    private static void testMinimumSegmentTreeByTree() {

    	MinimumSegmentTreeByTree mstbt;
        // Test 11, 12
		mstbt = new MinimumSegmentTreeByTree(new int[]{0});
		test(mstbt.queryRange(0,0) == 0, "The min of {0} between indexes [0:0] should be 0");
        test(mstbt.size() == 1, "The size of {0} should be 1");
        // Test 13, 14
        mstbt = new MinimumSegmentTreeByTree(new int[]{60,10,5,15,6});
        test(mstbt.queryRange(0,4) == 5, "The min of {60,10,5,15,6} between indexes [0:4] should be 5");
		test(mstbt.queryRange(1,3) == 5, "The min of {60,10,5,15,6} between indexes [1:3] should be 5");
        // Test 15
        mstbt.update(1,0);
        test(mstbt.queryRange(0,4) == 0, "After update index 1 from {60,10,5,15,6} to 0, the min between indexes [0:4] should be 0");
        // Test 16
        test(mstbt.toString().equals(" [ 0 0 0 60 0 5 6 15 6 ] "),"The toString of {60,0,5,15} should be ' [ 196 175 170 60 110 5 21 15 6 ] ' got: '" + mstbt.toString()+ " '");
        // Test 17, 18
		mstbt = new MinimumSegmentTreeByTree(new int[]{1,3,5,7,9,11,13,100,0});
		test(mstbt.queryRange(2,6) == 5, "The min of {1,3,5,7,9,11,13,100,0} between indexes [2:6] should be 5");
		test(mstbt.queryRange(3,8) == 0, "The min of {1,3,5,7,9,11,13,100,0} between indexes [3:8] should be 0");
        // Test 19
		mstbt = new MinimumSegmentTreeByTree(new int[]{-9, -8, -7, -6, 1, -10, 100});
		test(mstbt.queryRange(2,6) == -10, "The min of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be -10");

    }

    /**
     * Checks the SummationSegmentTreeByTree class.
     */
    private static void testSummationSegmentTreeByTree() {
    	SummationSegmentTreeByTree mstbt;
        // Test 20
		mstbt = new SummationSegmentTreeByTree(new int[]{0});
		test(mstbt.queryRange(0,0) == 0, "The sum of {0} between indexes [0:0] should be 0");
        // Test 21, 22
		mstbt = new SummationSegmentTreeByTree(new int[]{2});
        test(mstbt.queryRange(0,0) == 2, "The sum of {2} between indexes [0:0] should be 2");    	
        test(mstbt.size() == 1, "The size of {2} should be 1");
        // Test 23, 24
    	mstbt = new SummationSegmentTreeByTree(new int[]{60,10,5,15,6});
        test(mstbt.queryRange(0,4) == 96, "The sum of {60,10,5,15,6} between indexes [0:4] should be 96");
        test(mstbt.toString().equals(" [ 96 75 70 60 10 5 21 15 6 ] "),"The toString of {60,10,5,15,6} should be ' [ 196 175 170 60 110 5 21 15 6 ] ' got: '" + mstbt.toString()+ " '");
        // Test 25, 26
        mstbt.update(1,110);
        test(mstbt.queryRange(0,4) == 196, "After update index 1 from 10 to 110, the sum between indexes [0:4] should be 196");
        test(mstbt.toString().equals(" [ 196 175 170 60 110 5 21 15 6 ] "),"The toString of {60,110,5,15,6} should be ' [ 96 75 70 60 10 5 21 15 6 ] ' got: '" + mstbt.toString()+ " '");
        // Test 27
		mstbt = new SummationSegmentTreeByTree(new int[]{-9, -8, -7, -6, 1, -10, 100});
        test(mstbt.queryRange(0,mstbt.size()) == 61, "The sum of {-9, -8, -7, -6, 1, -10, 100} between indexes [0:6] should be 61");    	
        // Test 28
		mstbt = new SummationSegmentTreeByTree(new int[]{1,3,5,7,9,11,13,100,0});
		test(mstbt.queryRange(3,7) == 140, "The sum of {1,3,5,7,9,11,13,100,0} between indexes [3:7] should be 140");
        // Test 29
		mstbt.update(7, 200);
		test(mstbt.queryRange(0,8) == 249, "The sum of {1,3,5,7,9,11,13,100,0} between indexes [0:8] should be 249");
    }
    /**
     * Checks the MaximumSegmentTreeByArray class.
     */
    private static void testMaximumSegmentTreeByArray() {
        MaximumSegmentTreeByArray mstbt;
        // Test 30
        mstbt = new MaximumSegmentTreeByArray(new int[]{1000,200,300,4,5,6,7,8});
        test(mstbt.queryRange(0,5) == 1000, "The max of {1000,200,300,4,5,6,7,8} between indexes [0:5] should be 1000");
        // Test 31, 32, 33
        mstbt.update(5, 1500);
        test(mstbt.queryRange(0,5) == 1500, "The max of {1000,200,300,4,5,1500,7,8} between indexes [0:5] should be 1500");
        test(mstbt.queryRange(2,5) == 1500, "The max of {1000,200,300,4,5,1500,7,8} between indexes [0:5] should be 1500");
        test(mstbt.toString().equals(" [ 1500 1000 1500 1000 300 1500 8 1000 200 300 4 5 1500 7 8 ] "),"The toString of {1000,200,300,4,5,1500,7,8} should be ' [ 1500 1000 1500 1000 300 1500 8 1000 200 300 4 5 1500 7 8 ] ' got: '" + mstbt.toString()+ " '");
        // Test 34, 35
        mstbt = new MaximumSegmentTreeByArray(new int[]{10,15,55,15,9,12});
        test(mstbt.toString().equals(" [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");
        test(mstbt.queryRange(0,1) == 15, "The max of {10,15,55,15,9,12} between indexes [0:1] should be 15");
        // Test 36
        mstbt.update(0,80);
        test(mstbt.queryRange(0,0) == 80, "After update index 0 from {10,15,55,15,9,12} to 80, the max between indexes [0:0] should be 80");
        // Test 37
        mstbt = new MaximumSegmentTreeByArray(new int[]{1,2,3,4,5,6,7,8,9});
        test(mstbt.toString().equals(" [ 9 5 9 3 5 7 9 2 3 4 5 6 7 8 9 1 2 - - - - - - - - - - - - - - ] "),"The toString of {1,2,3,4,5,6,7,8,9} should be ' [ 9 5 9 3 5 7 9 2 3 4 5 6 7 8 9 1 2 - - - - - - - - - - - - - - ] ' got: '" + mstbt.toString()+ " '");    
    }
    
    /**
     * Checks the MaximumSegmentTreeByArray class.
     */
    private static void testMinimumSegmentTreeByArray() {
        // Test 38
    	MinimumSegmentTreeByArray mstbt = new MinimumSegmentTreeByArray(new int[]{9,4,5,1,7,100,6,4,-9});
    	test(mstbt.queryRange(0,mstbt.size()) == -9, "The min of {9,4,5,1,7,100,6,4,-9} between indexes [0:8] should be -9");
        // Test 39
    	mstbt.update(4, -25);
    	test(mstbt.queryRange(0,mstbt.size()) == -25, "The min of {9,4,5,1,-25,100,6,4,-9} between indexes [0:8] should be -25");
        // Test 40
    	mstbt = new MinimumSegmentTreeByArray(new int[]{1000,200,300,4,5,6,7,8});
    	test(mstbt.queryRange(0,mstbt.size()) == 4, "The min of {1000,200,300,4,5,6,7,8} between indexes [0:7] should be 4");
        mstbt.update(4, -25);
        // Test 41, 42, 43
    	test(mstbt.queryRange(0,mstbt.size()) == -25, "The min of {1000,200,300,4,-25,6,7,8} between indexes [0:7] should be -25");
    	test(mstbt.queryRange(0,2) == 200, "The min of {1000,200,300,4,-25,6,7,8} between indexes [0:2] should be 4");
    	test(mstbt.toString().equals(" [ -25 4 -25 200 4 -25 7 1000 200 300 4 -25 6 7 8 ] "),"The toString of {1000,200,300,4,-25,6,7,8} should be ' [ -25 4 -25 200 4 -25 7 1000 200 300 4 -25 6 7 8 ] ' got: '" + mstbt.toString()+ " '");   
    	// Test 44
        mstbt.update(0, -25);
    	test(mstbt.queryRange(0,2) == -25, "The min of {-25,200,300,4,-25,6,7,8} between indexes [0:2] should be 4");
    	// Test 45, 46
        mstbt = new MinimumSegmentTreeByArray(new int[]{10,15,55,15,9,12});
        test(mstbt.toString().equals(" [ 9 10 9 10 55 9 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 9 10 9 10 55 9 12 10 15 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");
        test(mstbt.queryRange(0,1) == 10, "The min of {10,15,55,15,9,12} between indexes [0:1] should be 10");
        // Test 47, 48, 49
        mstbt.update(0, -9);
        test(mstbt.queryRange(0,1) == -9, "The max of {-9,15,55,15,9,12} between indexes [0:1] should be -9");
        test(mstbt.queryRange(0,0) == -9, "The max of {-9,15,55,15,9,12} between indexes [0:0] should be 15");
        test(mstbt.queryRange(1,1) == 15, "The max of {-9,15,55,15,9,12} between indexes [1:1] should be -9");
    }
    
    /**
     * Checks the MaximumSegmentTreeByArray class.
     */
    private static void testSummationSegmentTreeByArray() {
    	SummationSegmentTreeByArray mstbt;
    	// Test 50
    	mstbt = new SummationSegmentTreeByArray(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1});
    	test(mstbt.queryRange(0, mstbt.size()) == 14, "The sum of {1,1,1,1,1,1,1,1,1,1,1,1,1,1} between indexes [0:13] should be 14");
    	// Test 51, 52, 53
    	mstbt.update(5, 99);
    	test(mstbt.queryRange(5, 5) == 99, "After update index 5 from 1 to 99, The sum of {1,1,1,1,1,99,1,1,1,1,1,1,1,1} between indexes [5:5] should be 99");
    	test(mstbt.queryRange(0, mstbt.size()) == 112, "The sum of {1,1,1,1,1,99,1,1,1,1,1,1,1,1} between indexes [0:13] should be 112");
    	test(mstbt.queryRange(4, 6) == 101, "The sum of {1,1,1,1,1,99,1,1,1,1,1,1,1,1} between indexes [4:6] should be 101");
    	// Test 54
    	mstbt = new SummationSegmentTreeByArray(new int[]{10,15,55,15,9,12});
    	test(mstbt.queryRange(0, 5) == 116,  "The sum of {10,15,55,15,9,12} between indexes [0:5] should be 116");
    	// Test 55, 56, 57, 58, 59
        mstbt.update(0, -9);
    	test(mstbt.queryRange(0, 5) == 97,  "The sum of {-9,15,55,15,9,12} between indexes [0:5] should be 97");
    	test(mstbt.toString().equals(" [ 97 61 36 6 55 24 12 -9 15 - - 15 9 - - ] "), "The toString of {-9,15,55,15,9,12} should be ' [ 97 61 36 6 55 24 12 -9 15 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");
    	test(mstbt.queryRange(0, 0) == -9,  "The sum of {-9,15,55,15,9,12} between indexes [0:0] should be -9");
    	test(mstbt.queryRange(0, 1) == 6,  "The sum of {-9,15,55,15,9,12} between indexes [0:1] should be 6");
    	test(mstbt.queryRange(2, 4) == 79,  "The sum of {-9,15,55,15,9,12} between indexes [2:4] should be 79");
    }

    
    /**
     * Checks the NumberAnalyzerByTrees class.
     */
    private static void testNumberAnalyzerByTrees() {

        Integer[] arr = {10,30,50};
        NumberAnalyzerByTrees nabt = new NumberAnalyzerByTrees(arr);
        // Test 60, 61, 62
        test(nabt.getMax(0,1) == 30, "The max of {10,30,50} between indexes [0:1] should be 30");
        test(nabt.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
        test(nabt.getSum(0,1) == 40, "The sum of {10,30,50} between indexes [0:1] should be 40");
        // Test 63, 64, 65
        nabt = new NumberAnalyzerByTrees(new Integer[]{1,3,5,7,9,11,13,100,0});
        test(nabt.getMax(2, 8) == 100, "The max of {1,3,5,7,9,11,13,100,0} between indexes [2:8] should be 100");
        test(nabt.getMin(2, 8) == 0, "The min of {1,3,5,7,9,11,13,100,0} between indexes [2:8] should be 0");
        test(nabt.getSum(2, 8) == 145, "The sum of {1,3,5,7,9,11,13,100,0} between indexes [2:8] should be 145");
        // Test 66, 67, 68
        nabt.update(8, 9);
        nabt.update(7, 50);
        test(nabt.getMax(2, 8) == 50, "The max of {1,3,5,7,9,11,13,50,9} between indexes [2:8] should be 50");
        test(nabt.getMin(2, 8) == 5, "The min of {1,3,5,7,9,11,13,50,9} between indexes [2:8] should be 5");
        test(nabt.getSum(2, 8) == 104, "The sum of {1,3,5,7,9,11,13,50,9} between indexes [2:8] should be 104");
        // Test 69, 70, 71
        nabt = new NumberAnalyzerByTrees(new Integer[]{-9, -8, -7, -6, 1, -10, 100});
        test(nabt.getMax(2, 6) == 100, "The max of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be 100");
        test(nabt.getMin(2, 6) == -10, "The min of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be -10");
        test(nabt.getSum(2, 6) == 78, "The sum of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be 78");
        // Test 72, 73, 74
        nabt = new NumberAnalyzerByTrees(new Integer[] {10,15,55,15,9,12});
        test(nabt.getMax(0, 4) == 55, "The max of {10,15,55,15,9,12} between indexes [0:4] should be 55");
        test(nabt.getMin(0, 4) == 9, "The min of {10,15,55,15,9,12} between indexes [0:4] should be 9");
        test(nabt.getSum(0, 4) == 104, "The sum of {10,15,55,15,9,12} between indexes [0:4] should be 104");
        // Test 75, 76, 77
        nabt.update(0, 80);
        test(nabt.getMax(0, 4) == 80, "The max of {10,15,55,15,9,12} between indexes [0:4] should be 55");
        test(nabt.getMin(0, 4) == 9, "The min of {10,15,55,15,9,12} between indexes [0:4] should be 9");
        test(nabt.getSum(0, 4) == 174, "The sum of {10,15,55,15,9,12} between indexes [0:4] should be 104");
    }
    
    /**
     * Checks the NumberAnalyzerByArray class.
     */
    private static void testNumberAnalyzerByArray() {
        // Test 78, 79, 80
        Integer[] arr = {10,30,50};
        NumberAnalyzerByArrays naba = new NumberAnalyzerByArrays(arr);
        test(naba.getMax(0,1) == 30, "The max of {10,30,50} between indexes [0:1] should be 30");
        test(naba.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
        test(naba.getSum(0,1) == 40, "The sum of {10,30,50} between indexes [0:1] should be 40");
        // Test 81, 82, 83
        naba = new NumberAnalyzerByArrays(new Integer[]{1,3,5,7,9,11,13,100,0});
        test(naba.getMax(2, 8) == 100, "The max of {1,3,5,7,9,11,13,100,0} between indexes [2:8] should be 100");
        test(naba.getMin(2, 8) == 0, "The min of {1,3,5,7,9,11,13,100,0} between indexes [2:8] should be 0");
        test(naba.getSum(2, 8) == 145, "The sum of {1,3,5,7,9,11,13,100,0} between indexes [2:8] should be 145");
        // Test 84, 85, 86
        naba.update(8, 9);
        naba.update(7, 50);
        test(naba.getMax(2, 8) == 50, "The max of {1,3,5,7,9,11,13,50,9} between indexes [2:8] should be 50");
        test(naba.getMin(2, 8) == 5, "The min of {1,3,5,7,9,11,13,50,9} between indexes [2:8] should be 5");
        test(naba.getSum(2, 8) == 104, "The sum of {1,3,5,7,9,11,13,50,9} between indexes [2:8] should be 104");
        // Test 87, 88, 89
        naba = new NumberAnalyzerByArrays(new Integer[]{-9, -8, -7, -6, 1, -10, 100});
        test(naba.getMax(2, 6) == 100, "The max of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be 100");
        test(naba.getMin(2, 6) == -10, "The min of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be -10");
        test(naba.getSum(2, 6) == 78, "The sum of {-9, -8, -7, -6, 1, -10, 100} between indexes [2:6] should be 78");
        // Test 90, 91, 92
        naba = new NumberAnalyzerByArrays(new Integer[] {10,15,55,15,9,12});
        test(naba.getMax(0, 4) == 55, "The max of {10,15,55,15,9,12} between indexes [0:4] should be 55");
        test(naba.getMin(0, 4) == 9, "The min of {10,15,55,15,9,12} between indexes [0:4] should be 9");
        test(naba.getSum(0, 4) == 104, "The sum of {10,15,55,15,9,12} between indexes [0:4] should be 104");
        // Test 93, 94, 95
        naba.update(0, 80);
        test(naba.getMax(0, 4) == 80, "The max of {10,15,55,15,9,12} between indexes [0:4] should be 55");
        test(naba.getMin(0, 4) == 9, "The min of {10,15,55,15,9,12} between indexes [0:4] should be 9");
        test(naba.getSum(0, 4) == 174, "The sum of {10,15,55,15,9,12} between indexes [0:4] should be 104");
    }
    
    /**
     * Checks the Comparator function.
     */
    public static void testComparator() {
    	// Test 96
    	int[] arr = new int[]{10,15,55,15,9,12};
    	insertionSort(arr, new NumberAnalyzerByTrees());
    	test(compareArray(arr, new int[] {9, 15, 15, 55, 10, 12}), "After sorting with comprator and Insertion Sort, {10,15,55,15,9,12} became {9, 15, 15, 55, 10, 12}");
    	// Test 97
    	arr = new int[]{1,9,100,3,11,13,5,7,0};
    	insertionSort(arr, new NumberAnalyzerByArrays());
    	test(compareArray(arr, new int[] {1,3,5,7,9,11,13,0,100}), "After sorting with comprator and Insertion Sort, {10,15,55,15,9,12} became {9, 15, 15, 55, 10, 12}");
    }
    
    /**
     * Checks the Iterator function.
     */
    public static void testIterator() {
    	Integer[] arr = new Integer[]{10,15,55,15,9,12};
    	Iterator<Integer> iter = new NumberAnalyzerByTrees(arr).iterator();
    	int i = 0;
    	// Test 98-103
    	while(iter.hasNext()) {
    		test(arr[i].equals(iter.next()), "Index " + i + " incorrect");
    		i++;
    	}
    }

    /**
     * Function that preform insertionSort on the array given
     * @param arr - Array to sort
     * @param comp - Comparator
     */
	public static void insertionSort(int[] arr, NumberAnalyzer comp) {
		for(int i = 0; i < arr.length; i++) {
			int x = arr[i];
			int j = i;
			while(j > 0 && comp.compare(arr[j-1], x) > 0) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = x;
		}
	}
	
	/**
	 * Function that compare 2 array and return boolean if equals
	 * @param arr1 - First array
	 * @param arr2 - Second array
	 * @return - True if equals, else false
	 */
	public static boolean compareArray(int[] arr1, int[] arr2) {
		if(arr1.length != arr2.length) {
			return false;
		}
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
}
