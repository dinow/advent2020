package be.dno.advent2021;


import be.dno.Day;

public class Day02 extends Day {

	public Day02(){
		fileName = "2021/day02.txt";
	}


	@Override
	public String processPart1() {
		int horizontalPosition = 0;
		int depthPosition = 0;
		Command command;
		for (String line : lines){
			command = new Command(line);
			if (command.getAction().equals("forward")){
				horizontalPosition += command.getValue();
			} else if (command.getAction().equals("down")){
				depthPosition += command.getValue();
			} else if (command.getAction().equals("up")){
				depthPosition -= command.getValue();
			}
		}
		return (horizontalPosition*depthPosition)+""; //1484118
	}
	   
	@Override  
   	public String processPart2(){
		int horizontalPosition = 0;
		int depthPosition = 0;
		int aim = 0;
		Command command;
		for (String line : lines){
			command = new Command(line);
			if (command.getAction().equals("forward")){
				horizontalPosition += command.getValue();
				depthPosition += (aim * command.getValue());
			} else if (command.getAction().equals("down")){
				aim += command.getValue();
			} else if (command.getAction().equals("up")){
				aim -= command.getValue();
			}
		}
		return (horizontalPosition*depthPosition)+""; //1463827010
	}
}


class Command {
	private String action;
	private Integer value;

	public Command(String line){
		String[] couple = line.split(" ");
		action = couple[0];
		value = Integer.valueOf(couple[1]);
	}

	public String getAction(){ return this.action; };
	public Integer getValue(){ return this.value; };
}