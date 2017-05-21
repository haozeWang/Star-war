package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;


public class Ufobullet extends Sprite {

	  private final double FIRE_POWER = 35.0;



public Ufobullet(Ufo ufo){
		
		super();
	    setTeam(Team.FOE);
		
		//defined the points on a cartesean grid
		ArrayList<Point> pntCs = new ArrayList<Point>();
		
		pntCs.add(new Point(1,0)); //top point
		
		pntCs.add(new Point(1,1));
		pntCs.add(new Point(1,2));
		pntCs.add(new Point(1,3));
	    pntCs.add(new Point(1,4));
	    pntCs.add(new Point(1,5));
	    pntCs.add(new Point(1,6));


		assignPolarPoints(pntCs);

		//a bullet expires after 20 frames
	    setExpire( 20 );
	    setRadius(6);
	    setColor(Color.YELLOW);
	    

	    //everything is relative to the falcon ship that fired the bullet
	    setDeltaX( ufo.getDeltaX() +
	               Math.cos( Math.toRadians( ufo.getOrientation() ) ) * FIRE_POWER );
	    setDeltaY( ufo.getDeltaY() +
	               Math.sin( Math.toRadians( ufo.getOrientation() ) ) * FIRE_POWER );
	    setCenter( ufo.getCenter() );

	    //set the bullet orientation to the falcon (ship) orientation
	    setOrientation(ufo.getOrientation());


	}

	@Override
	public void move(){

		super.move();

		if (getExpire() == 0)
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		else
			setExpire(getExpire() - 1);

	}
	public int getScore(){return 0;}

}
