package com.codility.lessons.dp;
/*
 For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:
 	val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
 For a given array A, we are looking for such a sequence S that minimizes val(A,S).
 Write a function:
 	int solution(int A[], int N);
 that, given an array A of N integers, 
 computes the minimum value of val(A,S) from all possible values of val(A,S) for all possible sequences S of N integers from the set {−1, 1}.
 For example, given array:
  A[0] =  1
  A[1] =  5
  A[2] =  2
  A[3] = -2
 your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.
Assume that:
N is an integer within the range [0..20,000];
each element of array A is an integer within the range [−100..100].
Complexity:
	expected worst-case time complexity is O(N*max(abs(A))2);
	expected worst-case space complexity is O(N+sum(abs(A))), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
*/
public class MinAbsSum {
	public int solution(int[] A){
		return 0; 
	}
}
