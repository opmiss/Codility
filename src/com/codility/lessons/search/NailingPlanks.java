package com.codility.lessons.search;

import java.util.Arrays;

/*Copyright 2009-2015 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
  You are given two non-empty zero-indexed arrays A and B consisting of N integers. These arrays represent N planks. 
  More precisely, A[K] is the start and B[K] the end of the K-th plank.
  Next, you are given a non-empty zero-indexed array C consisting of M integers. 
  This array represents M nails. More precisely, C[I] is the position where you can hammer in the I-th nail.
  We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] <= C[I] <= B[K].
  The goal is to find the minimum number of nails that must be used until all the planks are nailed. 
  In other words, you should find a value J such that all planks will be nailed after using only the first J nails. 
  More precisely, for every plank (A[K], B[K]) such that 0 <= K < N, there should exist a nail C[I] such that I < J and A[K] <= C[I] <= B[K].
  For example, given arrays A, B such that:
    A[0] = 1    B[0] = 4
    A[1] = 4    B[1] = 5
    A[2] = 5    B[2] = 9
    A[3] = 8    B[3] = 10
  four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
  Given array C such that:
    C[0] = 4
    C[1] = 6
    C[2] = 7
    C[3] = 10
    C[4] = 2
  if we use the following nails:
  0, then planks [1, 4] and [4, 5] will both be nailed.
  0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
  0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
  0, 1, 2, 3, then all the planks will be nailed.
  Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
Write a function:
  int solution(int A[], int B[], int N, int C[], int M);
that, given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed array C consisting of M integers, 
returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
If it is not possible to nail all the planks, the function should return -1.
For example, given arrays A, B, C such that:
    A[0] = 1    B[0] = 4
    A[1] = 4    B[1] = 5
    A[2] = 5    B[2] = 9
    A[3] = 8    B[3] = 10
    
    C[0] = 4
    C[1] = 6
    C[2] = 7
    C[3] = 10
    C[4] = 2
the function should return 4, as explained above.
Assume that:
  N and M are integers within the range [1..30,000];
  each element of arrays A, B, C is an integer within the range [1..2*M];
  A[K] <= B[K].
Complexity:
  expected worst-case time complexity is O((N+M)*log(M));
  expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
  Elements of input arrays can be modified.
  */

public class NailingPlanks {
	int solution(int[] A, int[] B, int[] C){
		int N = A.length; 
		int M = C.length; 
		int[][] S = new int[M][2]; 
		for (int i=0; i<M; i++){
			S[i][0] = C[i];
			S[i][1] = i; 
		}
		Arrays.sort(S, (int[] a, int[] b)->a[0]-b[0]);
		int ret = N; 
		for (int i=0; i<N; i++){
			ret = find(A[i], B[i], S, ret);  
			if (ret<0) return -1; 
		}
		return ret+1; 
	}
	int find(int plankstart, int plankend, int[][] S, int p){
		int start = 0, end = S.length-1, mid; 
		int candidate = -1; 
		if (S[end][0]<plankstart || S[start][0]>plankend) return -1; 
		while (start<end-1){
			mid = (start+end)/2; 
			if (S[mid][0]>plankstart){
				end = mid; 
			}
			else if (S[mid][0]<plankstart){
				start = mid; 
			}
			else {
				end = mid; 
				break; 
			}
		}
		if (plankstart<=S[end][0]&&plankend>=S[end][0]){
			candidate = S[end][1]; 
		}
		else return -1; 
		for (int i=candidate; i<S.length && S[i][0]<=plankend; i++){
			if (S[i][1] < candidate) candidate = S[i][1];  
		}
		return candidate; 
	}
	public void test(){
		int[] A = new int[]{1, 4, 5, 8};
		int[] B = new int[]{4, 5, 9, 10}; 
		int[] C = new int[]{4, 6, 7, 10, 2}; 
		System.out.println(solution(A, B, C)); 
		C = new int[]{4, 6, 7, 2}; 
		System.out.println(solution(A, B, C)); 
	}
	public static void main(String[] args){
		NailingPlanks np = new NailingPlanks(); 
		np.test(); 
	}
}