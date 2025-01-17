package br.com.tapflappy.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.graphics.Canvas;
import br.com.tapflappy.graphic.Screen;

public class Obstacles {
	
	private static final int NUM_OF_OBST = 5;
	private static final int OBST_DIST = 700;
	
	private final List<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	private Screen screen;

	public Obstacles(Screen screen) {
		this.screen = screen;
		int position = 275;
		
		for (int i = 0; i < NUM_OF_OBST; i++) {
			position += OBST_DIST;
			Obstacle obstacle = new Obstacle(screen, position);
			obstacles.add(obstacle);
		}
		
	}

	public void drawOnThe(Canvas canvas) {
		for (Obstacle obstacle : obstacles) {
			obstacle.drawOnThe(canvas);
		}
		
	}

	public void move() {
		ListIterator<Obstacle> iterator = obstacles.listIterator();
		while(iterator.hasNext()){
			Obstacle obstacle = iterator.next();
			obstacle.move();
			if(obstacle.outOfBounds()){
				// gerar novo obstaculo e apagar antigo
				iterator.remove();
				Obstacle auxObstacle = new Obstacle(screen, getMaxPos() + OBST_DIST);
				iterator.add(auxObstacle);
			}
		}
	}

	private int getMaxPos() {
		int max = 0;
		for (Obstacle obstacle : obstacles) {
			max = Math.max(obstacle.getPosition(), max);
		}
		return max;
	}
	
}
