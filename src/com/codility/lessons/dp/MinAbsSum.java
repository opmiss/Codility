package com.codility.lessons.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
Given array of integers, find the lowest absolute sum of elements.
test result
https://codility.com/demo/results/demoGE3VDQ-SC8/
*/
public class MinAbsSum {
	public int solution(int[] A){
		int S = 0; 
		int[] C = new int[101]; 
		int M = 0; 
		for (int i=0; i<A.length; i++){
			if (A[i]<0) A[i] = -A[i];
			S+=A[i]; 
			C[A[i]]++;
			M = (M<A[i])?A[i]:M; 
		}
		int[] R = new int[S+1]; 
		R[0] = 1; 
		for (int i=1; i<=M; i++){
			if (C[i]==0) continue; 
			for (int j=0; j<S+1; j++){
				if (R[j]>0) R[j]=C[i]+1; 
				else if (j-i>=0 && R[j-i]>0) R[j] = R[j-i]-1; 
			}
		}
		int ret = S; 
		for (int i=0; i<S/2+1; i++){
			if (R[i]>0) ret = Math.min(ret, S-i*2); 
		}
		return ret; 
	}
	@Test
	public void test(){
		int[] A = new int[]{1, 5, 2, -2}; 
		assertEquals(0, solution(A)); 
		A = new int[]{3, 3, 5, 6}; 
		assertEquals(1, solution(A)); 
		A = new int[]{1, 1};
		assertEquals(0, solution(A)); 
		A = new int[]{0, 0, 0, 0}; 
		assertEquals(0, solution(A));  
	}
}
