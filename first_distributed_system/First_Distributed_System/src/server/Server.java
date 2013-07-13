package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;

public class Server implements Runnable{
	private Socket client;
	private MathLogic logic = new MathLogic();
	private WordsLogic wLogic = new WordsLogic();
	private Class[] _classes = {logic.getClass(),wLogic.getClass()};
	private HashMap<String,Class> classes = new HashMap<String,Class>();
	
	private String[] classChoices = {logic.getClass().getName(),wLogic.getClass().getName()};
	public Server(Socket client){
		this.client = client;
		for(int i = 0; i < _classes.length; i ++){
			classes.put(classChoices[i], _classes[i]);
		}
			
	}
	
	public Class getMethods(String className){
		String response = "";
		Method[] methods = null;
		Class c = null;
		try {
			// Methodname, string-int ; Methodname, string-int;
			c = Class.forName(className);
			methods = c.getDeclaredMethods();		
//			for(Method m : methods){
//				response += m.getName() + ",";
//				for(int i = 0; i < m.getParameterTypes().length; i++){
//					if(i == m.getParameterTypes().length-1){
//						response += m.getParameterTypes()[i] + ";";
//					}
//					else
//					response += m.getParameterTypes()[i] + "-";
//					
//				}
//					
//			}			
			
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return c;
	}
	
	
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(4359);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		while(true){
			Server server;
			try{
				Socket socket = serverSocket.accept();
				server = new Server(socket);
				Thread request = new Thread(server);
				request.start();				
				
			}catch(IOException e){
				System.out.println("Something went wrong");
			}
		}

	}
	
	@Override
	public void run() {
		
		System.out.println("SERVER: listening to new Client");


		BufferedReader input = null;
		PrintWriter output = null;
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream(),true);
			ObjectOutputStream obs = new ObjectOutputStream(client.getOutputStream());
			//classes that the user can choose
			obs.writeObject(classChoices);
			obs.flush();
			String className = input.readLine();
			
			//Method that the user can choose
			obs.writeObject(getMethods(className));
			//output.write(getMethods() + "\n");			
			obs.flush();
			
			String received = input.readLine();
			if(received != null){
				// method name, int-java.lang.integer,int,java.lang.string ;
				String[] metadata= received.split(",|;");
				String methodName = metadata[0];
				String[] params = new String[metadata.length -1 ];
				for(int i = 1; i < metadata.length; i++){
					params[i-1] = metadata[i];
					System.out.println(" param " + params[i-1]);
				}
				
				System.out.println("Method name " + methodName);
				Object[] o = new Object[params.length];
				System.out.println(o.length);
				int count = 0;
			
				for(String s : params){
					if(s != null){
					String[] st = s.split("-");					
					Class c = Class.forName(st[1]);
					if(!st[1].equals("java.lang.String")){
					Method method = c.getMethod("valueOf",String.class);
					o[count] = method.invoke(null, st[0]);
					count++;
					}
					else{
						o[count] = st[0];
						count++;
					}

					}
				}
						Class the_class = classes.get(className);
						Object instance = the_class.newInstance();
				for(Method m : the_class.getMethods()){
					if(m.getName().equals(methodName)){						
						Object obj = m.invoke(instance, o);				
						output.println(obj);
						output.flush();
					}
				}
				
				
				
				
				client.close();
			}
		}
	catch (Exception e) {			
			e.printStackTrace();
		}
		
		
		
	}
	public void placeholder(String received, PrintWriter output, MathLogic logic)
	{
		
		
		String receivedInput[] = received.split(",");
		int a = Integer.parseInt(receivedInput[1]);
		int b = Integer.parseInt(receivedInput[2]);
		String operation = receivedInput[0];
		if(operation.equals("add")){
			output.write(logic.add(a, b));
			}
		else if(operation.equals("subtract")){
			output.write(logic.subtract(a, b));
			}
		output.flush();
	}

}
