package com.codility.lessons.numbers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
/*
 * The Fibonacci sequence is defined using the following recursive formula:

    F(0) = 0
    F(1) = 1
    F(M) = F(M - 1) + F(M - 2) if M >= 2
    A small frog wants to get to the other side of a river. 
    The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). 
    The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. 
    Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
    The leaves on the river are represented in a zero-indexed array A consisting of N integers. 
    Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
    
    0 represents a position without a leaf; 1 represents a position containing a leaf.
    
The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). 
The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.

For example, consider array A such that:

    A[0] = 0
    A[1] = 0
    A[2] = 0
    A[3] = 1
    A[4] = 1
    A[5] = 0
    A[6] = 1
    A[7] = 0
    A[8] = 0
    A[9] = 0
    A[10] = 0
The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.

Write a function:

int solution(int A[], int N);

that, given a zero-indexed array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.

For example, given:

    A[0] = 0
    A[1] = 0
    A[2] = 0
    A[3] = 1
    A[4] = 1
    A[5] = 0
    A[6] = 1
    A[7] = 0
    A[8] = 0
    A[9] = 0
    A[10] = 0
the function should return 3, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer that can have one of the following values: 0, 1.
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.*/
import java.util.Queue;
import java.util.logging.Logger;

import org.junit.Test;

import com.codility.lessons.search.NailingPlanks;

public class FibFrog {
	Logger log = Logger.getLogger(NailingPlanks.class.getName()); 
	//an array that holds a list of Fibonacci numbers
	ArrayList<Integer> F = new ArrayList<Integer>(); 
	int prevF = 1, curF = 1; 
	int solution(int[] A){
		int N = A.length; 
		if (N==0) return 1; 
		int temp; 
		//fill in the Fib array with the largest element less than N
		while (curF<=N+2){
			F.add(curF);
			temp = curF; 
			curF +=prevF;
			prevF = temp; 
		}
		//log.info("fib list: "+F); 
		//breadth first search
		ArrayList<Queue<Integer>> Qs = new ArrayList<Queue<Integer>>();
		Queue<Integer> Q1 = new LinkedList<Integer>(); 
		Queue<Integer> Q2 = new LinkedList<Integer>();
		int reach = -1; 
		Q2.add(reach); 
		Qs.add(Q1); Qs.add(Q2); 
		int cur_pos = -1, step = 1;
		do {
			while (!Qs.get(step % 2).isEmpty()) {
				cur_pos = Qs.get(step%2).poll(); 
				for (int f = F.size() - 1; f >= 0; f--) {
					reach = cur_pos + F.get(f);
					if (reach == N)
						return step;
					if (reach < N && A[reach] == 1)
						Qs.get((step+1)%2).add(reach);
				}
			}
			if (Qs.get((++step)%2).isEmpty()) return -1; 
		} while (true);
	}
	
	@Test
	public void test(){
		int[] A = new int[]{0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}; 
		//System.out.println(solution(A)); 
		assertEquals(3, solution(A)); 
		A = new int[]{0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0}; 
		assertEquals(1, solution(A)); 
		A = new int[]{0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0}; 
		assertEquals(-1, solution(A));
	}
}
