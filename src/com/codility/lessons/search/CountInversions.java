package com.codility.lessons.search;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.*;

/*  
    A zero-indexed array A consisting of N integers is given. 
    An inversion is a pair of indexes (P, Q) such that P < Q and A[Q] < A[P].
Write a function: 
	int solution(int A[], int N);
	that computes the number of inversions in A, or returns −1 if it exceeds 1,000,000,000.
Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	For example, in the following array:
	A[0] = -1 A[1] = 6 A[2] = 3 A[3] =  4 A[4] = 7 A[5] = 4
	there are four inversions:
  	(1,2)  (1,3)  (1,5)  (4,5)
  	so the function should return 4.
Complexity:
	expected worst-case time complexity is O(N*log(N)); *! This is wrong, the worst case can output O(N*N) pairs*
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
*/

public class CountInversions {
	
	int count = 0; 
	
	public int solution(int[] A){
		count=0; 
		merge(A, 0, A.length-1); 
		return count; 
	}
	
	public void mergeSort(int[] A, int start, int end){
		if (start>=end) return; 
		if (start==end-1) {
			if (A[start]>A[end]){
				int t = A[start]; 
				A[start] = A[end]; 
				A[end] = t; 
			}
			return; 
		}
		int mid = (start+end)/2; 
		mergeSort(A, start, mid); 
		mergeSort(A, mid+1, end);
		merge(A, start, end); 
	}
	
	public void merge(int[] A, int start, int end){
		System.out.println(A.toString()); 
		int mid = (start+end)/2; 
		int n1 = mid-start+1; 
		int n2 = end-mid; 
		int[] A1 = new int[n1];
		int[] A2 = new int[n2];
		for (int i=start; i<=mid; i++) A1[i-start] = A[i]; 
		for (int i=mid+1; i<=end; i++) A2[i-mid-1] = A[i]; 
		int i1=0, i2=0, i=start;
		while (i1<n1&&i2<n2){
			if (A2[i2]<A1[i1]) {
				A[i++]=A2[i2++]; 
				count+=n1-i1; 
				System.out.println(count); 
				if (count>1000000000) count=-1; 
			}
			else {
				A[i++]=A1[i1++];
			}
		}
		while(i1<n1){
			A[i++]=A1[i1++];
		}
		while(i2<n2){
			A[i++]=A2[i2++]; 
		}
	}
	
	@Test
	public void test(){
		int[] A = new int[]{-1, 6, 3, 4, 7, 4};
		assertEquals(4, solution(A)); 
		A = new int[]{1, 2, 4, 5, 3};
		assertEquals(2, solution(A)); 
		A = new int[]{5, 4, 3, 2, 1}; 
		assertEquals(10, solution(A)); 
		A = new int[]{1, 2, 3, 4, 5}; 
		assertEquals(0, solution(A)); 
	}
}
