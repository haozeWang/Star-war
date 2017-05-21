package _08final.mvc.model;
import _08final.mvc.controller.Game;

import java.awt.*;
import java.util.*;
/**
 * Created by haozewang on 16/11/28.
 */
public class Debris implements Movable {

    private int mExpiry;
    private Point mPoint;
    public Debris(int mEmpiry, Point mPoint) {
        this.mExpiry = mEmpiry;
        this.mPoint = mPoint;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(
                Game.R.nextInt(256),
                Game.R.nextInt(256),
                Game.R.nextInt(256)
        ));
        g.fillOval(mPoint.x, mPoint.y, mExpiry, mExpiry);

    }

    @Override
    public void move() {
        if (mExpiry == 0){
            CommandCenter.getInstance().getOpsList()
                    .enqueue(this, CollisionOp.Operation.REMOVE);
        } else {
            mExpiry--;
        }
    }

    //collission detection.
    @Override
    public Point getCenter() {
        return null;
    }
    @Override
    public int getRadius() {
        return 0;
    }
    @Override
    public Team getTeam() {
        return Team.DEBRIS;
    }
    public int getScore(){
        return 0;
    }
}
