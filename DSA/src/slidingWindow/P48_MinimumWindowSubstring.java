package slidingWindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class P48_MinimumWindowSubstring {
	
	/* Given two strings s and t of lengths m and n respectively,
	 *  return the minimum window substring of s such that every 
	 *  character in t (including duplicates) is included in the window. 
	 *  If there is no such substring, return the empty string "".

		The testcases will be generated such that the answer is unique.
		
		A substring is a contiguous sequence of characters within the string.
		
		 
		
		Example 1:
		
		Input: s = "ADOBECODEBANC", t = "ABC"
		Output: "BANC"
		Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
		Example 2:
		
		Input: s = "a", t = "a"
		Output: "a"
		Explanation: The entire string s is the minimum window.
		Example 3:
		
		Input: s = "a", t = "aa"
		Output: ""
		Explanation: Both 'a's from t must be included in the window.
		Since the largest window of s only has one 'a', return empty string.
	 * 
	 */
	
	 /* 
	  * 1. 
    Input(s)? String, String
    Output ? String
    Constraints ?  

	2. Test data set
	
	        Positive :s = "ADOBECODEBANC", t = "ABC"
	        Negative :"a", t = "aa"
	        Edge     :s = "a", t = "a"
	
	3. Known Approaches
	        Approach 1 :Sliding Window 
	
	4. O-Notations
	
	5. Pseudocode
	  * 
	  */
	
	@Test
	public void testData01(){            // Positive
		String s = "ADOBECODEBANC";
		String t = "ABC";
		Assert.assertTrue(findMinimumSubtring(s,t).equals("BANC"));
	}
	@Test
	public void testData04(){            // Positive
		String s = "ACOOBDEBANC";
		String t = "ABC";
		Assert.assertTrue(findMinimumSubtring(s,t).equals("BANC"));
	}


	@Test
	public void testData02(){			 // Negative
		String s = "a";
		String t = "aa";
		Assert.assertTrue(findMinimumSubtring(s,t).equals(""));
	}

	@Test
	public void testData03(){			 // Edge
		String s = "a";
		String t = "a";
		Assert.assertTrue(findMinimumSubtring(s,t).equals("a"));
	}
	
	/* 1.Initialize left as 0, right as 1 and minCount = Integer.MAX_VALUE
	 * 2.Initialize empty string as returnStr
	 * 3.Initialize a map and add characters of t length
	 * 	  a) If all chars in t is present in inputHash , Update minCount and returnStr
	 * 4. Iterate the input till right reaches length-t.length
	 * 	  a) Add the next char which is right+t.length-1 to inputMap
	 *    b) If all chars in t present in inputHash
	 *    	  a)Update minCount and returnStr
	 *    	  b)Increment left
	 *        c)Repeat till inputHash does not contains all chars in char
	 *    c) increment right
	 * 5.return returnStr
	 * Time : O(n)
	 */

	private String findMinimumSubtring(String s, String t) {
		if (s.length() < t.length())
			return "";
		int left = 0, right = 1, minCount = Integer.MAX_VALUE;
		String returnStr = "";
		Map<Character, Integer> inputMap = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			inputMap.put(s.charAt(i), inputMap.getOrDefault(s.charAt(i), 0) + 1);
		}
		if (isAllCharPresent(inputMap, t)) {
			minCount = t.length();
			returnStr = s.substring(0, t.length());
		}
		while (right <= s.length() - t.length()) {
			char addChar = s.charAt(right + t.length() - 1);
			inputMap.put(addChar, inputMap.getOrDefault(addChar, 0) + 1);
			while (isAllCharPresent(inputMap, t)) {
				if (right - left + 1 < minCount) {
					minCount = right - left + 1;
					returnStr = s.substring(left, right + t.length());
				}
				char removeChar = s.charAt(left);
				if (inputMap.get(removeChar) > 1)
					inputMap.put(removeChar, inputMap.getOrDefault(removeChar, 0) - 1);
				else
					inputMap.remove(s.charAt(left));
				left++;
			}
			right++;
		}
		return returnStr;
	}

	private boolean isAllCharPresent(Map<Character, Integer> inputMap, String target) {
		int left = 0, right = target.length() - 1;
		while (left <= right) {
			if (left == right) {
				if (!inputMap.containsKey(target.charAt(left++)))
					return false;
			} else {
				if (!inputMap.containsKey(target.charAt(left++)))
					return false;
				if (!inputMap.containsKey(target.charAt(right--)))
					return false;
			}
		}
		return true;

	}
}
