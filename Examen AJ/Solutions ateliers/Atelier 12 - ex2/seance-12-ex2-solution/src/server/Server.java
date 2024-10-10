package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Server {
	
	private static Properties props = new Properties();
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		try {
			props.load(new FileInputStream("./commands.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listenKeyboard() {
		System.out.println("Type commands here :");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		while (true) {
			// Read and split line
			String command = input.nextLine();
			String[] split = command.split(" ");
			// Get arguments
			List<String> args = new ArrayList<>();
			for (int i = 1; i < split.length; i++) {
				args.add(split[i]);
			}
			// Execute command
			executeCommand(split[0], args);
		}
	}
	
	public void listenNetwork(int port) {
		// Do not implement !
	}
	
	private void executeCommand(String name, List<String> args) {
		try {
			name = name.toUpperCase();
			// Get class name
			String className = name;
			if (props.get(name) != null) {
				className = props.getProperty(name);
			} else {
				className = "commands." + className;
			}
			// Get command object
			Command command;
			if (commands.containsKey(className)) {
				command = commands.get(className);
			} else {
				Class<?> cl = Class.forName(className);
				// Checks
				Method m = cl.getMethod("execute", List.class);
				if (!Modifier.isPublic(m.getModifiers()) || m.getReturnType() != Void.TYPE) {
					throw new InternalError("Command not well implemented");
				}
				// New command
				command = (Command) cl.newInstance();
				commands.put(className, command);
			}
			// Execute command
			command.execute(args);
		} catch (ClassNotFoundException e) {
			System.out.println("Unsupported command: " + name);
		} catch (InstantiationException e) {
			throw new InternalError("Command not well implemented", e);
		} catch (IllegalAccessException e) {
			throw new InternalError("Command not well implemented", e);
		} catch (NoSuchMethodException e) {
			throw new InternalError("Command not well implemented", e);
		} catch (SecurityException e) {
			throw new InternalError("Command not well implemented", e);
		}
	}

}
