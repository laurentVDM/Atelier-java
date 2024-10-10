package commands.mock;

import java.util.List;

import server.Command;

/**
 * List the files
 */
public class MOCKLIST implements Command {

	@Override
	public void execute(List<String> args) {
		// Do not implement !
		System.out.println("MOCKLIST command executed");
	}

}
