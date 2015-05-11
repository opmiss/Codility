package com.codility.lessons.numbers;

import org.junit.Test;

/* Copyright 2009-2015 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * You are given two positive integers N and M. 
 * The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.
 * For example, given:
 * N = 15 and M = 75, the prime divisors are the same: {3, 5};
 * N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
 * N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
 * Write a function:
 * class Solution { public int solution(int[] A, int[] B); } 
 * that, given two non-empty zero-indexed arrays A and B of Z integers, 
 * returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.
 * For example, given:
    A[0] = 15   B[0] = 75
    A[1] = 10   B[1] = 30
    A[2] = 3    B[2] = 5
    the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
    Assume that:
    Z is an integer within the range [1..6,000];
    each element of arrays A, B is an integer within the range [1..2,147,483,647].
    Complexity:
    expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
    expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
*/ 
public class CommonPrimeDivisors {
	
	//https://codility.com/demo/results/demoB2UF8F-96C/
	
	public int solution(int[] A, int[] B){
		int l = A.length; 
		int ret = 0; 
		for (int i=0; i<l; i++){
			int g = gcd(A[i], B[i]);
			int a = A[i]/g;
			int d = gcd(a, g); 
			while (a!=1 && d!=1){
				a/=gcd(a, g);
				d=gcd(a, g); 
			}
			if (a!=1) continue;
			a = B[i]/g; 
			d = gcd(a, g); 
			while (a!=1 && d!=1){
				a/=gcd(a, g);
				d=gcd(a, g); 
			}
			if (a==1) ret++; 
		}
		return ret; 
	}
	int gcd(int a, int b){
		if (b==0) return a; 
		return gcd(b, a%b); 
	}
	
	@Test
	public void test(){
		int[] A = new int[]{15, 10, 750}; 
		int[] B = new int[]{75, 30, 180}; 
		System.out.println(solution(A, B)); 
	}
	
	public static void main(String[] args){
		CommonPrimeDivisors c = new CommonPrimeDivisors(); 
		c.test(); 
	}
}
