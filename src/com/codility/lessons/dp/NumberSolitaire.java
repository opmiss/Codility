package com.codility.lessons.dp;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NumberSolitaire {
	public int solution(int[] A){
		int[] dp = new int[A.length];
		dp[0] = A[0]; 
		int prev = dp[0]; 
		for (int i=1; i<A.length; i++){
			for (int k=1; k<=6; k++){ 
				int id = i-k;
				if (id>=0) prev = Math.max(prev, dp[id]); 
			}
			dp[i] = prev+A[i]; 
		}
		return dp[A.length-1]; 
	}
	
	@Test
	public void test(){
		int[] A = new int[]{1, -2, 0, 9, -1, -2}; 
		assertEquals(8, solution(A)); 
	}
	
}
