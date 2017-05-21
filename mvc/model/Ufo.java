package _08final.mvc.model;

import _08final.mvc.controller.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Sprite {

	private int nSpin;
    private Random random;
	public Ufo() {

		super();
		setTeam(Team.FOE);
		ArrayList<Point> pntCs = new ArrayList<Point>();
		// top of ship
		pntCs.add(new Point(0,45));
		pntCs.add(new Point(45,45));
		pntCs.add(new Point(45,0));
		pntCs.add(new Point(0,45));

		assignPolarPoints(pntCs);

		setExpire(250);
		setRadius(50);
		setColor(Color.pink);


		setRandomdirection();
		
		//set random spin

		//random point on the screen
		setCenter(new Point(Game.R.nextInt(Game.DIM.width),
				Game.R.nextInt(Game.DIM.height)));

		//random orientation 
		 setOrientation(Game.R.nextInt(360));

		 }




	private void setRandomdirection() {
		int nX = Game.R.nextInt(10);
		int nY = Game.R.nextInt(10);


		//set random DeltaX
		if (nX % 2 == 0)
			setDeltaX(nX);
		else
			setDeltaX(-nX);

		//set rnadom DeltaY
		if (nY % 2 == 0)
			setDeltaY(nY);
		else
			setDeltaY(-nY);
	}

	@Override
	public void move() {
		super.move();
		setOrientation(getOrientation() + getSpin());

		//adding expire functionality
		if (getExpire() == 0)
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		else{
			if(getExpire()%20==0){
				setRandomdirection();
			   CommandCenter.getInstance().getOpsList().enqueue(new Ufobullet(this),CollisionOp.Operation.ADD);
			}

		}
			setExpire(getExpire() - 1);
	}

	public int getSpin() {
		return this.nSpin;
	}

	public void setSpin(int nSpin) {
		this.nSpin = nSpin;
	}





	@Override
	public void draw(Graphics g) {
		super.draw(g);
		//fill this polygon (with whatever color it has)
		g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
		//now draw a white border
		g.setColor(Color.WHITE);
		g.drawPolygon(getXcoords(), getYcoords(), dDegrees.length);
	}
	public int getScore(){return 0;}

	public void expire() {
		if (getExpire() == 0)
			CommandCenter.getInstance().movFloaters.remove(this);
		else
			setExpire(getExpire() - 1);
	}

}
