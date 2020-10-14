package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import nl.tue.s2iv60.core.cg.Camera;
import nl.tue.s2iv60.core.app.GS;
import org.joml.Vector3f;

class FlyingCamera extends Camera {
    @Override
    public void apply(GL2 gl, GLU glu) {
        // Compute eye vector based on center and GS.theta, GS.phi, and GS.vDist.
        // Keep up-vector the same.
        Vector3f eyeVector = new Vector3f(1,0,0);
        super.apply(gl, glu);
    }
    
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
        this.eye    = useGiven? e : new Vector3f(-10,10,10);
        this.center = useGiven? c : new Vector3f(0,0,0); 
        this.up     = useGiven? u : new Vector3f(0,0,1);
        
        //  compute theta, phi, and vDist to start with same eye point and direction
    }
}