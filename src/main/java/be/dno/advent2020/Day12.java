package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.awt.Point;
import java.util.List;

import org.apache.commons.io.IOUtils;
import be.dno.Day_old;
public class Day12 implements Day_old{

	private enum Direction {
		N(0), S(180), E(90), W(270);
		private int value;
		private Direction(int degree){
			this.value = degree;
		}
		public static Direction fromDegree(int id) {
			for (Direction type : values()) {
				if (type.value == id) {
					return type;
				}
			}
			return null;
		}
	}
	
	private Direction currentDirection = Direction.E;
	private Point startingPosition = new Point(0,0);
	private Point currentPosition = new Point(0,0);
	private Point currentWaypoint = new Point(10,-1);

	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		for  (String line : contents){
			move(line);
		}
		System.out.println("part 1 : " + (Math.abs(currentPosition.x-startingPosition.x) + Math.abs(currentPosition.y-startingPosition.y)));
		currentDirection = Direction.E;
		startingPosition = new Point(0,0);
		currentPosition = new Point(0,0);
		for  (String line : contents){
			moveWaypoint(line);
		}
		System.out.println("part 2 : " + (Math.abs(currentPosition.x-startingPosition.x) + Math.abs(currentPosition.y-startingPosition.y)));
	}

	private void moveWaypoint(String line){
		char action = line.charAt(0);
		int distance = Integer.parseInt(line.substring(1, line.length()));
		System.out.println(action + " - " + distance);
		//first turn the ship
		
		if (action == 'R' || action == 'L') {
			rotateWaypoint(action, distance);
		} else {
			if (action != 'F'){
				moveWaypoint(Direction.valueOf(""+action),distance);
			} else {
				moveBoatToWaypoint(distance);
			}
		}
		System.out.println("currentPosition : ["+currentPosition.x+", "+currentPosition.y+"]");
		System.out.println("currentWaypoint : ["+currentWaypoint.x+", "+currentWaypoint.y+"]");
	}

	private void moveBoatToWaypoint(int steps){
		//Get boat delta
		int deltaY = currentWaypoint.y - currentPosition.y;
		int deltaX = currentWaypoint.x - currentPosition.x;

		//Move boat
		currentPosition.setLocation(currentPosition.x + (deltaX * steps), currentPosition.y + (deltaY * steps));
		//Move waypoint
		currentWaypoint.setLocation(currentPosition.x + deltaX, currentPosition.y + deltaY);
	}

	private void rotateWaypoint(char direction, int degrees){

		if (direction == 'L' ) {
			degrees *= -1;
		} 

		double[] pt = {currentWaypoint.x, currentWaypoint.y};
		java.awt.geom.AffineTransform.getRotateInstance(Math.toRadians(degrees), currentPosition.x, currentPosition.y).transform(pt, 0, pt, 0, 1); // specifying to use this double[] to hold coords
		double xRot = pt[0];
		double yRot = pt[1];

		int newX = xRot > 0 ? (int)Math.ceil(xRot) : (int)Math.ceil(xRot*-1)*-1;
		int newY = yRot > 0 ? (int)Math.ceil(yRot) : (int)Math.ceil(yRot*-1)*-1;

		currentWaypoint.setLocation(newX, newY);
	}

	private void moveWaypoint(Direction direction, int distance){
		if (direction == Direction.W) currentWaypoint.setLocation(currentWaypoint.x - distance, currentWaypoint.y);
		if (direction == Direction.E) currentWaypoint.setLocation(currentWaypoint.x + distance, currentWaypoint.y);
		if (direction == Direction.S) currentWaypoint.setLocation(currentWaypoint.x           , currentWaypoint.y + distance);
		if (direction == Direction.N) currentWaypoint.setLocation(currentWaypoint.x           , currentWaypoint.y - distance);
	}

	private void move(String line){
		char action = line.charAt(0);
		int distance = Integer.parseInt(line.substring(1, line.length()));
		System.out.println(action + " - " + distance);
		//first turn the ship
		
		if (action == 'R' || action == 'L') {
			currentDirection = Direction.fromDegree(rotate(action,currentDirection.value, distance));
		} else {
			Direction direction = currentDirection;
			if (action != 'F') direction = Direction.valueOf(""+action);
			move(direction,distance);
		}
	}

	private int rotate(char direction, int origin, int degrees){

		if (degrees > 360) degrees /= 0;
		if (direction == 'R'){
			origin += degrees;
			origin %= 360;
		} else {
			origin -= degrees;
			if (origin < 0){
				origin += 360;
			}
		}
		return origin;
	}

	private void move(Direction direction, int distance){
		if (direction == Direction.W) currentPosition.setLocation(currentPosition.x - distance, currentPosition.y);
		if (direction == Direction.E) currentPosition.setLocation(currentPosition.x + distance, currentPosition.y);
		if (direction == Direction.S) currentPosition.setLocation(currentPosition.x           , currentPosition.y + distance);
		if (direction == Direction.N) currentPosition.setLocation(currentPosition.x           , currentPosition.y - distance);
	}
}
