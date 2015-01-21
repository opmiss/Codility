package com.codility.lessons.sorting;

/* Copyright 2009-2015 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * 
 * Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. 
 * We say that the J-th disc and K-th disc intersect if J != K and J-th and K-th discs have at least one common point.
 * Write a function:
 * public int solution(int[] A); 
 * that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. 
 * For example, given N=6 and:
 * A[0] = 1  A[1] = 5  A[2] = 2
 * A[3] = 1  A[4] = 4  A[5] = 0
 * intersecting discs appear in eleven pairs of elements:
	0 and 1,
	0 and 2,
	0 and 4,
	1 and 2,
	1 and 3,
	1 and 4,
	1 and 5,
	2 and 3,
	2 and 4,
	3 and 4,
	4 and 5.
	so the function should return 11.
 * The function should return -1 if the number of intersecting pairs exceeds 10,000,000.
 * Assume that:
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..2147483647].
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.*/ 

public class DiscIntersections {
	 public int solution(int[] A) {
		 int[] ds = new int[A.length];
		 int[] de = new int[A.length];
		 int s, e; 
		 for (int i=0; i<A.length; i++){
			 s=i-A[i]; e=i+A[i]; 
			 ds[(s<0)?0:s]++;
			 de[(e>A.length-1)?(A.length-1):e]++; 
		 }
		 int ret=0, cur=0; 
		 for (int i=0; i<A.length; i++){
			 if (ds[i]>0){
				 ret+=ds[i]*(ds[i]-1)/2; 
				 ret+=cur*ds[i]; 
				 cur+=ds[i]; 
			 }
			 cur-=de[i]; 
		 }
		 return ret; 
	 }
	 public void test(){
		 int[] A = new int[]{1, 5, 2, 1, 4, 0}; 
		 System.out.println(solution(A)); 
	 }
	 public static void main(String[] args){
		 DiscIntersections di = new DiscIntersections(); 
		 di.test(); 
	 }
}
