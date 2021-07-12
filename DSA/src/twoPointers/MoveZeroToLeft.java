package twoPointers;

import org.junit.Assert;
import org.junit.Test;

public class MoveZeroToLeft

{
	/*
	 * In a given array, move the all the zeroes to the right end
	 * 
	 */
	
	/*
	 * 1. Did I understand the problem? Yes


        What is the input(s)? int[]
        What is the expected output? int[] 
        Do I have any constraints to solve the problem? Solve with two ptrs 
        Do I have all the information to go to the next steps 
        How big is your test data set will be? 
		

	2. Test data set
	        Minimum  3 data set including positive, negative and edge
	        Validate with the interviewer if the data set is fine by his/ her assumptions
	
	3. Do I know to solve it?
	        Yes- great is there an alternate ? Yes
	        No - can I break the problem into sub problems?
	
	4. Ask for hint (if you dont know how to solve this
	5.  Do I know alternate solutions as well
	        Yes- what are those? Yes
	        No- that is still fine, proceed to solve by what you know
	
	6. If you know the alternate solution find out the O-notations (performance)
	 *	Approach 1 : Bruteforce - Time :O(n) Space : O(n)
	 *  Approach 2 : Two Pointer Approach - Time : O(n)  Space : O(1) 
	 */
	
	@Test
	public void testData01()
	{
		int[] input = {0,2,1,0,0,3,4};
		Assert.assertArrayEquals(moveZeroToEnd(input), new int[] {4,2,1,3,0,0,0});
	}
	
	@Test
	public void testData02()
	{
		int[] input = {0,1};
		Assert.assertArrayEquals(moveZeroToEnd(input), new int[] {1,0});
	}

	@Test
	public void testData03()
	{
		int[] input = {0,9,0,2,-1,2,2,0,9,0};
		Assert.assertArrayEquals(moveZeroToEnd(input), new int[] {9,9,2,2,-1,2,0,0,0,0});
	}
	
	/* Pseudocode - Two Pointer Approach
	 * 1. If the input is empty or length of 1, return the input
	 * 2. Initialize two pointer left = 0, right = input.length - 1;
	 * 3. Iterate the input left < right
  	 *
	 * 	 	a) If left value = 0 , swap with right value , increment left ++, decrement right --
	 *   	b) If right value  = 0, decrement right --;
	 *   	c) Else left++
	 * 4. return the input data
	 * Time : O(n)
	 * Space : O(1)
	 * 
	 */
	
	private int[] moveZeroToEnd(int[] input) {
		if (input.length < 2)
			return input;
		int left = 0, right = input.length - 1;
		while (left < right) {
			if (input[right] == 0)
				right--;
			else if (input[left] == 0) {
				int temp = input[left];
				input[left++] = input[right];
				input[right--] = temp;
			} else
				left++;
		}
		return input;
	}
	
	

}
