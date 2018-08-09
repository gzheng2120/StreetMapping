
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Inp {
	
	public String inputFile;
	public boolean show, directions, exit;
	public String startAt, destination;
	
	public Inp() {
		//initiate values
		inputFile = "";
		show = false;
		directions = false;
		startAt = "";
		destination = "";
		exit = false;
		
		//read in the command line
		Scanner in = new Scanner(System.in);
		String commandLine = in.nextLine();
		process(commandLine);
	}
	
	public void process(String command) {
		if (command.equals("exit")) {
			exit = true;
		}
		else  {//divide the comamnd line into different parts
			List<String> commandParts = Arrays.asList(command.split("\\s+"));
			//take out the input file name, which is the 3rd words of the command line string
			inputFile = commandParts.get(2);
		
			if (commandParts.contains("--show")) show = true;
			if (commandParts.contains("--directions")) {
				directions = true;
			
				//the two strings after directions is the start and the destination
				int index = commandParts.indexOf("--directions");
				startAt = commandParts.get(index+1);
				destination = commandParts.get(index+2);
			}
		}
	}
}
