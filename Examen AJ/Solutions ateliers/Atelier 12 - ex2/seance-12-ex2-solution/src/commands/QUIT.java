package commands;

import java.util.List;

import server.Command;

public class QUIT implements Command {

	@Override
	public void execute(List<String> args) {
		System.out.println("Bye.");
		System.exit(0);
	}

}
