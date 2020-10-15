package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Camera;
import nl.tue.s2iv60.core.app.GS;
import org.joml.Matrix3d;
import org.joml.Vector3f;

import static java.lang.Math.*;

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
        this.eye    = useGiven? e: new Vector3f(10,10,10);
        this.center = useGiven? c: new Vector3f(0,0,0);
        this.up     = useGiven? u: new Vector3f(0,0,1);

        Vector3f vDistance = eye.sub(center);
        GS.vDist = vDistance.length();

        Vector3f x = new Vector3f(1,0,0);
        Vector3f y = new Vector3f(0,1,0);
        Vector3f z = new Vector3f(0,0,1);

        Vector3f normalXY = new Vector3f(0);
        Vector3f normalXZ = new Vector3f(0);

        x.cross(y,normalXY);
        x.cross(z,normalXZ);

        float dotXY = eye.dot(normalXY);
        float dotXZ = eye.dot(normalXZ);

        normalXY.mul(dotXY);
        normalXZ.mul(dotXZ);

        Vector3f projXY = new Vector3f(0);
        Vector3f projXZ= new Vector3f(0);

        eye.sub(normalXY,projXY);
        eye.sub(normalXZ,projXZ);

        GS.theta = x.angle(projXY);
        GS.phi = x.angle(projXZ);


        //  compute theta, phi, and vDist to start with same eye point and direction
    }

    @Override
    public void apply(GL2 gl, GLU glu) {
        // Compute eye vector based on center and GS.theta, GS.phi, and GS.vDist.
        // Keep up-vector the same.

        Vector3f z = new Vector3f(0,0,1);
        Vector3f y = new Vector3f(0,1,0);
        Vector3f eyePosition = new Vector3f(1,0,0);
        eyePosition.rotateZ(GS.theta);
        eyePosition.rotateY(-GS.phi);
        eyePosition.mul(GS.vDist);
        eye = eyePosition;
        super.apply(gl, glu);
    }
}
