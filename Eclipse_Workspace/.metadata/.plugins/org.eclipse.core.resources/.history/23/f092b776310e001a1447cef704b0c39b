package skj;

import java.awt.Color;

import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

public class MyRobot extends TeamRobot {
	
	int dir = 1;
	boolean f = true;
	double moveAmount;
	
	public void run() {
		// Set colors
		setBodyColor(Color.BLACK);
		setGunColor(Color.BLACK);
		setRadarColor(Color.BLACK);
		setScanColor(Color.BLACK);
		setBulletColor(Color.BLACK);

		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		setAdjustGunForRobotTurn(true);
		
		turnLeft(getHeading());
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		turnRight(90);
		
		while (true) {
			turnRadarLeft(360);
			ahead(moveAmount * dir);
			turnRight(90);
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		shoot(e);	
	}
	
	public void shoot(ScannedRobotEvent e) {
		setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
		setFire(Math.min(400 / e.getDistance(), 3));
	}
}
