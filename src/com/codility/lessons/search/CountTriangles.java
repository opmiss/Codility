package com.codility.lessons.search;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import util.Log;

/*
 * A zero-indexed array A consisting of N integers is given. 
 * A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. 
 * In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
	A[P] + A[Q] > A[R],
	A[Q] + A[R] > A[P],
	A[R] + A[P] > A[Q].
	
	For example, consider array A such that:
  	A[0] = 10    A[1] = 2    A[2] = 5	A[3] = 1	A[4] = 8    A[5] = 12
  	
  	There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).
  	Write a function:
	int solution(int A[], int N);
	that, given a zero-indexed array A consisting of N integers, returns the number of triangular triplets in this array.
	
	For example, given array A such that:
    A[0] = 10    A[1] = 2    A[2] = 5		A[3] = 1     A[4] = 8    A[5] = 12
    the function should return 4, as explained above.
    
Assume that:
	N is an integer within the range [0..1,000]; each element of array A is an integer within the range [1..1,000,000,000].
Complexity:
	Expected worst-case time complexity is O(N2);
	Expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

public class CountTriangles {
	
	public int solution(int[] A){
		if (A.length<3) return 0; 
		Arrays.sort(A); 
		int ret=0; 
		for (int i=0; i<A.length-2; i++){
			for (int j=(i+1), k=(j+1); j<A.length-1; j++){
				while (k<A.length && A[i]+A[j]>A[k]) k++; 
				ret+=(k-j-1); 
			}
		}
		return ret; 
	}
	
	@Test
	public void test(){
		int[] A = new int[]{10, 2, 5, 1, 8, 12}; 
		assertEquals(4, solution(A)); 
		A = new int[]{3, 3, 3, 3, 3}; 
		assertEquals(10, solution(A));
	}
	
}
