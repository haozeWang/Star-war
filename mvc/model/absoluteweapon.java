package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by haozewang on 16/11/28.
 */
public class absoluteweapon extends Sprite  {


        // ==============================================================
        // FIELDS
        // ==============================================================

        private final int MAX_EXPIRE = 15;					// how long the explosion lasts

        private final double FIRE_POWER = 35.0;
        private static Random R;


        public absoluteweapon(Falcon fal) {
            super();
            setTeam(Team.FRIEND);
            //defined the points on a cartesean grid
            ArrayList<Point> pntCs = new ArrayList<Point>();


            pntCs.add(new Point(0, 0));
            pntCs.add(new Point(0, 1));
            pntCs.add(new Point(0, 2));
            pntCs.add(new Point(0, 3));
            pntCs.add(new Point(0, 5));
            pntCs.add(new Point(0, 6));
            pntCs.add(new Point(0, 7));
            pntCs.add(new Point(0, 8));
            pntCs.add(new Point(0, 9));
            pntCs.add(new Point(0, 10));
            pntCs.add(new Point(0, 11));
            pntCs.add(new Point(2, 0));
            pntCs.add(new Point(2, 1));
            pntCs.add(new Point(2, 2));
            pntCs.add(new Point(2, 3));
            pntCs.add(new Point(2, 5));
            pntCs.add(new Point(2, 6));
            pntCs.add(new Point(2, 7));
            pntCs.add(new Point(2, 8));
            pntCs.add(new Point(2, 9));
            pntCs.add(new Point(2, 10));
            pntCs.add(new Point(2, 11));

            assignPolarPoints(pntCs);



            //a cruis missile expires after 25 frames
            setExpire(100);
            setRadius(100);

            //everything is relative to the falcon ship that fired the bullet
            setDeltaX( fal.getDeltaX() +
                    Math.cos( Math.toRadians( fal.getOrientation() ) ) * FIRE_POWER );
            setDeltaY( fal.getDeltaY() +
                    Math.sin( Math.toRadians( fal.getOrientation() ) ) * FIRE_POWER );
            setCenter( fal.getCenter() );

            //set the bullet orientation to the falcon (ship) orientation
            setOrientation(fal.getOrientation());
            setColor(Color.RED);


        }
        public void move(){

        super.move();

        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else
            setExpire(getExpire() - 1);

      }
    public int getScore(){return 0;}



    }

