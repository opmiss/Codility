package com.codility.lessons.numbers;

import java.util.Arrays;

/* Copyright 2009-2015 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * You are given a non-empty zero-indexed array A consisting of N integers.
 * For each number A[i] such that 0 <= i < N, we want to count the number of elements of the array that are not the divisors of A[i]. 
 * We say that these elements are non-divisors.
 * For example, consider integer N = 5 and array A such that:
    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6
   For the following elements:
   A[0] = 3, the non-divisors are: 2, 6,
   A[1] = 1, the non-divisors are: 3, 2, 3, 6,
   A[2] = 2, the non-divisors are: 3, 3, 6,
   A[3] = 3, the non-divisors are: 2, 6,
   A[6] = 6, there aren't any non-divisors.
   Write a function:
   class Solution { public int[] solution(int[] A); }
   that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
   The sequence should be returned as an array of integers.
   For example, given:
    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6
   the function should return [2, 4, 3, 2, 0], as explained above.
   Assume that: N is an integer within the range [1..50,000]; each element of array A is an integer within the range [1..2 * N].
   Complexity:
   expected worst-case time complexity is O(N*log(N)); expected worst-case space complexity is O(N), 
   beyond input storage (not counting the storage required for input arguments).
   Elements of input arrays can be modified.
   */ 

public class CountNonDivisors {
	public int[] solution(int[] A){
		int N = A.length; 
		int[] count = new int[N*2+1];
		int[] ret = new int[N]; 
		for (int i=0; i<N; i++){
			count[A[i]]++; 
		}
		int t; 
		for (int i=0; i<N; i++){
			for (int num=1; num*num<=A[i]; num++){
				if (A[i]%num==0){
					if ((t=A[i]/num)!=num){
						ret[i]+=count[t]; 
						ret[i]+=count[num]; 
					}
					else {
						ret[i]+=count[num]; 
					}
				}
			}
		}
		for (int i=0; i<N; i++) ret[i] = N-ret[i]; 
		return ret; 
	}
	public void test(){
		int[] A = new int[]{3, 1, 2, 3, 6};
		System.out.println(Arrays.toString(solution(A))); 
	}
	public static void main(String[] args){
		CountNonDivisors cnd = new CountNonDivisors(); 
		cnd.test(); 
	}
}
