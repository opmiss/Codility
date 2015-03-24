package com.codility.lessons.search;

/*
 * Copyright 2009-2015 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
  You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. 
  Every element of the array is not greater than M.
  You should divide this array into K blocks of consecutive elements. 
  The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
  The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
  The large sum is the maximal sum of any block.
  For example, you are given integers K = 3, M = 5 and array A such that:
  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
  The array can be divided, for example, into the following blocks:
  [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
  [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
  [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
  [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
  The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
Write a function:
  int solution(int K, int M, int A[], int N);
  that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.
  For example, given K = 3, M = 5 and array A such that:
  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
  the function should return 6, as explained above.
Assume that:
  N and K are integers within the range [1..100,000];
  M is an integer within the range [0..10,000];
  each element of array A is an integer within the range [0..M].
Complexity:
  expected worst-case time complexity is O(N*log(N+M));
  expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.
  */

public class MinMaxDivision {
	int solution(int K, int[] A){
		float sum = 0; 
		for (int a:A) sum+=a; 
		if (K==1) return (int)sum; 
		int ret = (int)sum/K; 
		float _cur=0, cur; 
		float fut=sum/(K-1); 
		float _max = Math.max(_cur, fut), max, max_; 
		for (int i=0; i<A.length; i++){
			cur = _cur+A[i]; 
			fut = (sum-A[i])/(K-1); 
			max = Math.max(cur, fut); 
			if (max>_max){
				K--; 
				ret = Math.max(ret, (int)_max); 
				if (K==1) return ret;
				_cur=0; 
				i--; 
			}
			else {
				_cur = cur; 
				_max = max; 
				sum -=A[i]; 
			}
		}
		return ret; 
	}
	
	public void test(){
		//int[] A = new int[]{2, 1, 5, 1, 2, 2, 2};
		//int[] A = new int[]{1, 1, 8, 7, 7};
		int[] A = new int[]{1, 1, 8, -10};
		System.out.println(solution(3, A)); 
	}
	
	public static void main(String[] args){
		MinMaxDivision mmd = new MinMaxDivision(); 
		mmd.test(); 
	}

}
