package com.codility.lessons.search;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/*
 * Let A be a non-empty zero-indexed array consisting of N integers.
 * The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.

For example, the following array A:
  A[0] =  1
  A[1] =  4
  A[2] = -3
has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2). 
The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2. 
The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5. 
The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2. 
The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8. 
The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1. 
The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6. 
Write a function:

int solution(int A[], int N);

that, given a non-empty zero-indexed array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.

For example, given the following array A:

  A[0] =  1
  A[1] =  4
  A[2] = -3
the function should return 1, as explained above.

Given array A:

  A[0] = -8
  A[1] =  4
  A[2] =  5
  A[3] =-10
  A[4] =  3
the function should return |(−8) + 5| = 3.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/

public class MinSumTwo {
	public int solution(int[] A){
		Arrays.sort(A);
		int i = 0, j = A.length-1, s, r=Integer.MAX_VALUE;
		while (i<=j){
			if ((s=A[i]+A[j]) < 0)
				i++;
			else if (s>0) 
				j--; 
			else return 0;
			r = Math.min(Math.abs(s), r); 
		}
		return r; 
	}
	
	@Test
	public void test(){
		int[] A = {1, 4, -3}; 
		assertEquals(1, solution(A)); 
		A = new int[]{-8, 4, 5, -10, 3}; 
		assertEquals(3, solution(A)); 
		A = new int[]{1, 2, 3, 4}; 
		assertEquals(2, solution(A)); 
		A = new int[]{-1, -2, -3, -4}; 
		assertEquals(2, solution(A)); 
	}

}