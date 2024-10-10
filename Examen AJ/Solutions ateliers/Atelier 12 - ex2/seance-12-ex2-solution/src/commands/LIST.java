package commands;

import java.util.List;

import server.Command;

/**
 * List the files
 */
public class LIST implements Command {

	@Override
	public void execute(List<String> args) {
		// Do not implement !
		System.out.println("LIST command executed");
	}

}
