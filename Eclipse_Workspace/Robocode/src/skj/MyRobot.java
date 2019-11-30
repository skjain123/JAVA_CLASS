package skj;

import java.awt.Color;

import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

public class MyRobot extends TeamRobot {
	
	int dir = 1;
	double moveAmount;
	
	public void run() {
		// Set colors
		setBodyColor(Color.BLACK);
		setGunColor(Color.BLACK);
		setRadarColor(Color.BLACK);
		setScanColor(Color.BLACK);
		setBulletColor(Color.WHITE);

		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		setAdjustGunForRobotTurn(true);
		
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		turnRight(90);
		ahead(moveAmount);
		
		while (true) {
			turnRadarRight(360);
			ahead(moveAmount * dir);
			turnRight(90);
		}
	}
	
	double bulletPwr = 0;
	double bulletSpeed = 0;
	long bulletTime = 0;
	double distTraveling = 0;
	//double angle = 0;
	
	public void shoot(ScannedRobotEvent e) {
		double bulletPwr = Math.min(400 / e.getDistance(), 3);
		double angle = getHeading() - getGunHeading() + e.getBearing();		

		/*
		bulletPwr = Math.min(400 / e.getDistance(), 3);
		bulletSpeed = 20 - bulletPwr * 3;
		bulletTime = (long)(e.getDistance() / bulletSpeed);
		distTraveling = e.getVelocity() * bulletTime;
		angle = Math.atan((distTraveling) / (e.getDistance()));
		*/
		
		//setTurnGunRightRadians(0);
		//setTurnGunRightRadians(angle);
		
		setTurnGunRight(angle);
		if (Math.abs(getGunTurnRemainingRadians()) < 10) {
			setFire(bulletPwr);
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		shoot(e);
	}
}
