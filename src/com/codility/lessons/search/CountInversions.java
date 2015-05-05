package com.codility.lessons.search;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.*;

/*  
 * Compute number of inversion in an array
 * https://codility.com/demo/results/demoC9ZEP6-76H/
*/

public class CountInversions {
	
	int count = 0; 
	
	public int solution(int[] A){
		count=0; 
		mergeSort(A, 0, A.length-1); 
		return count; 
	}
	
	public void mergeSort(int[] A, int start, int end){
		if (start>=end) return; 
		if (start==end-1) {
			if (A[start]>A[end]){
				int t = A[start]; 
				A[start] = A[end]; 
				A[end] = t; 
				count++; 
			}
			return; 
		}
		int mid = (start+end)/2; 
		mergeSort(A, start, mid); 
		mergeSort(A, mid+1, end);
		merge(A, start, end); 
	}
	
	public void merge(int[] A, int start, int end){
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
