package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Camera;
import nl.tue.s2iv60.core.app.GS;
import org.joml.Matrix3d;
import org.joml.Vector3f;

import static java.lang.Math.*;
/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
class FlyingCamera extends Camera {
    /**
     * Set camera at given eye point, looking at given center, and taking the up
     * vector for the vertical direction. If one of these vectors is null, default
     * values are used.
     * @param e eye vector
     * @param c center vector
     * @param u up vector
     */
    public FlyingCamera(Vector3f e, Vector3f c, Vector3f u) {
        boolean useGiven = e!=null && c!=null && u!=null;
        this.eye    = useGiven? e: new Vector3f(10,-10,10);
        this.center = useGiven? c: new Vector3f(0,0,0);
        this.up     = useGiven? u: new Vector3f(0,0,1);

        Vector3f vDistance = eye.sub(center); //Compute the eye-vector
        GS.vDist = vDistance.length(); //vDist is the length of the eye-vector

        Vector3f x = new Vector3f(1,0,0); //Versor for direction x
        Vector3f y = new Vector3f(0,1,0); //Versor for direction y
        Vector3f z = new Vector3f(0,0,1); //Versor for direction z

        Vector3f normalXY = new Vector3f(0); //Normal of plan XY
        x.cross(y,normalXY); //Find the normal of plan XY and store it in normalXY
        float dotXY = eye.dot(normalXY); //Find the dot product of the normal of plane XY and the eye-vector
        normalXY.mul(dotXY); //The aforementioned dot product represents the length of the normal of plane XY,
                             //hence apply it to normalXY
        Vector3f projXY = new Vector3f(0); //The projection of the eye-vector on plane XY
        eye.sub(normalXY,projXY); //Compute the projection-vector of the eye-vector

        float quadrantAdjustment = 0; //Adjust angle theta based on its quadrant
        if (eye.x > 0 && eye.y < 0) { //Adjust based on quadrant IV
            quadrantAdjustment = (float) (- PI/2);
        }
        if (eye.x < 0 && eye.y < 0) { //Adjust based on quadrant III
            quadrantAdjustment = (float) (+ PI/2);
        }
        GS.theta = x.angle(projXY) + quadrantAdjustment; //Calculate theta, which is the angle between the
                                                         //projection-vector and the x versor

        //Calculate phi, which is the angle between the eye-vector and plan XY
        if (eye.z > 0) { //Adjust angle phi based on whether the eye-vector is above or below the XY plane
            GS.phi = eye.angle(projXY);
        } else {
            GS.phi = - eye.angle(projXY);
        }
    }

    @Override
    public void apply(GL2 gl, GLU glu) {
        // Compute eye vector based on center and GS.theta, GS.phi, and GS.vDist.
        // Keep up-vector the same.

        float cosinusP = (float) Math.cos(GS.phi); //Cosinus of angle phi
        float cosinusT = (float) Math.cos(GS.theta);//Cosinus of angle theta
        float sinusP = (float) Math.sin(GS.phi); //Sinus of angle phi
        float sinusT = (float) Math.sin(GS.theta); //Cosinus of angle theta
        float x = GS.vDist * cosinusP * cosinusT; //Compute x coordinate of the eye-vector
        float y = GS.vDist * cosinusP * sinusT; //Compute y coordinate of the eye-vector
        float z = GS.vDist * sinusP; //Compute z coordinate of the eye-vector
        eye = new Vector3f(x,y,z);
        super.apply(gl, glu);
    }
}
