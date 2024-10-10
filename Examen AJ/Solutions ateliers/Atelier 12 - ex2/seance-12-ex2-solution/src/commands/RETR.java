package commands;

import java.util.List;

import server.Command;

/**
 * Retrieve a file
 */
public class RETR implements Command {

	@Override
	public void execute(List<String> args) {
		// Do not implement !
		System.out.println("RETR command executed on the file" + args.get(0));
	}

}
