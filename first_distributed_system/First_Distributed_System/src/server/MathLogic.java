package server;

import java.io.Serializable;

public class MathLogic implements Serializable {
	
	public MathLogic(){
		
	}
	public int add(int a, int b){
		System.out.println("SERVER: " + a + " + " + b);
		return a + b;
	}
	public int subtract(int a, int b){
		System.out.println("SERVER: " + a + " - " + b);
		return a - b;
	}
	

}
