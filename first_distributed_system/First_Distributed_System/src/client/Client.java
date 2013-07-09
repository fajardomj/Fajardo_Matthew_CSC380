package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client c = new Client();
		c.connectClient();
		
	}
	public void connectClient()
	{
		while(true){
		Socket socket = null;
		PrintWriter output = null;
		BufferedReader input = null;
		
		try {
			socket = new Socket("localhost",4359);
			output = new PrintWriter(socket.getOutputStream(),true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.write(getUserInput() + "\n");
			output.flush();
		
			System.out.println("CLIENT: " + input.readLine());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}	
	public String getUserInput(){
	
		String request = "";
		System.out.println(" 1- Add \n 2- Subtract \n 3- Quit ");
		Scanner scan = new Scanner(System.in);
		try{
		int userInput = scan.nextInt();
		System.out.println("Please enter two numbers you want to " + request + " Example 2,2");
		String numbers = scan.next();
		String input[] = numbers.split(",");		
		int a = Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		
		if(userInput == 1){
			
			request = "a," + a +","+ b;
		}
		else if(userInput == 2){
			
			request = "s," + a +","+ b;
		}
		else{
			System.exit(0);
		}
		}catch(Exception e){
			System.out.println("Invalid Input");
		}	
		scan.close();
		return request;
	}
	
	
	

}