package server;
public class MathLogic {
	
	public MathLogic(){
		
	}
	public static int add(int a, int b){
		System.out.println("SERVER: " + a + " + " + b);
		return a + b;
	}
	public static int subtract(int a, int b){
		System.out.println("SERVER: " + a + " - " + b);
		return a - b;
	}

}
