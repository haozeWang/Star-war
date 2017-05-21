package _08final.mvc.model;

import _08final.mvc.controller.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NewShipFloater extends Sprite {

   public static enum Type{addlife,shield}
	private int nSpin;
    private Random random;
	private Type type;
	public NewShipFloater() {

		super();
		setTeam(Team.FLOATER);
		ArrayList<Point> pntCs = new ArrayList<Point>();
		// top of ship
		pntCs.add(new Point(5, 5));
		pntCs.add(new Point(4,0));
		pntCs.add(new Point(5, -5));
		pntCs.add(new Point(0,-4));
		pntCs.add(new Point(-5, -5));
		pntCs.add(new Point(-4,0));
		pntCs.add(new Point(-5, 5));
		pntCs.add(new Point(0,4));

		assignPolarPoints(pntCs);

		setExpire(250);
		setRadius(50);
		setColor(Color.BLUE);


		int nX = Game.R.nextInt(10);
		int nY = Game.R.nextInt(10);
		int nS = Game.R.nextInt(5);
		
		//set random DeltaX
		if (nX % 2 == 0)
			setDeltaX(nX);
		else
			setDeltaX(-nX);

		//set rnadom DeltaY
		if (nY % 2 == 0)
			setDeltaX(nY);
		else
			setDeltaX(-nY);
		
		//set random spin
		if (nS % 2 == 0)
			setSpin(nS);
		else
			setSpin(-nS);

		//random point on the screen
		setCenter(new Point(Game.R.nextInt(Game.DIM.width),
				Game.R.nextInt(Game.DIM.height)));

		//random orientation 
		 setOrientation(Game.R.nextInt(360));
		 settype(getRandType());
		 switch (type){
			 case addlife:setColor(Color.RED);break;
			 case shield:setColor(Color.green);break;
		 }


	}

	@Override
	public void move() {
		super.move();
		setOrientation(getOrientation() + getSpin());

		//adding expire functionality
		if (getExpire() == 0)
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		else
			setExpire(getExpire() - 1);


	}

	public int getSpin() {
		return this.nSpin;
	}

	public void setSpin(int nSpin) {
		this.nSpin = nSpin;
	}

	private Type getRandType(){
		//int pick = random.nextInt(Type.values().length);
		return Type.values()[Math.random()>0.5?0:1];
	}
	public void settype(Type type) {
		this.type=type;
	}

	public Type getType() {
		return type;
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
