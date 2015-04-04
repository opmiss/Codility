package com.codility.lessons.search;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.logging.Logger;

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
	Logger log = Logger.getLogger(NailingPlanks.class.getName()); 
	public int solution(int[] A){
		int ret = 0; 
		LinkedList<Integer> list = new LinkedList<Integer>(); 
		for (int i = 0; i< A.length; i++){
			ret+=add(A[i], list); 
		}
		return ret; 
	}
	int find(int a, LinkedList<Integer> list, int start, int end){ 
		if (a<list.get(start)) return start;
		if (a>=list.get(end)) return end+1; 
		if (start==end || start==end-1) return start+1;
		int mid = (start+end)/2; 
		int m = list.get(mid); 
		if (a>m) return find(a, list, mid, end); 
		if (a<m) return find(a, list, start, mid); 
		return mid+1; 
	}
	
	int add(int a, LinkedList<Integer> list){// add element a, and return the number of elements right to a in the list
		int n = list.size(); 
		if (n==0) {list.add(a); return 0;}
		int id = find(a, list, 0, n-1);
		list.add(id, a); 
		return n-id;   
	}
	
	@Test
	public void test(){
		int[] A = new int[]{-1, 6, 3, 4, 7, 4};
		assertEquals(4, solution(A)); 
		A = new int[]{1, 2, 4, 5, 3};
		assertEquals(2, solution(A)); 
		A = new int[]{5, 4, 3, 2, 1}; 
		assertEquals(10, solution(A)); 
	}
}
