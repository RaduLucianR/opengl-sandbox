package objects.lights;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.app.Base;
import nl.tue.s2iv60.core.cg.Renderable;
import objects.Road;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
public class StreetLights implements Renderable {
    private final int numLights = 5;
    private final Road road;
    private final LightPole[] poles = new LightPole[numLights];

    public StreetLights(Road road) {
        this.road = road;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        Vector3f[] curve1 = {
                new Vector3f(-5,-15,0.0001f),
                new Vector3f(-25,-15,0.0001f),
                new Vector3f(-25,18,0.0001f),
                new Vector3f(-5,18,0.0001f)
        };

        Vector3f[] curve2 = {
                new Vector3f(-5,18,0.0001f),
                new Vector3f(15,18,0.0001f),
                new Vector3f(-5,6,0.0001f),
                new Vector3f(15,6,0.0001f)
        };

        Vector3f[] curve3 = {
                new Vector3f(15,6,0.0001f),
                new Vector3f(25,6,0.0001f),
                new Vector3f(25,-15,0.0001f),
                new Vector3f(-5,-15,0.0001f)
        };

        GLU glu = new GLU();
        
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, new float[]{1, 1, 1, 1}, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, new float[]{1, 1, 1, 1}, 0);

        float[] angle = new float[numLights];
        angle[0]=-90;
        angle[1]=0;
        angle[2]=-90;
        angle[3]=0;
        angle[4]=50;
        for (int j = 5; j< numLights; j++){
            angle[j]=-45;
        }
        for (int i = 0; i < numLights; i++) {
            if (poles[i]==null) {
                // position street lights regularly
                float t =  i/(float) numLights;
                List<Vector3f[]> curves = new ArrayList<>();
                curves.add(curve1);
                curves.add(curve2);
                curves.add(curve3);
                Vector3f pos = road.getCubicBezierSplinePnt(curves,t);
                float zCoord = pos.z;
                Vector3f[] tng = road.getCubicBezierSplineTng(curves,t);
                Vector3f tng2 = tng[1].sub(tng[0]);
                //pos.add(cross.mul(2));
                poles[i] = new LightPole(pos, tng2, 1, gl.GL_LIGHT0+(i+1),angle[i]);
                System.out.println(pos);
            }
        
            poles[i].render(gl, glut, tAnim, dt);
            Base.reportError("Opengl error in java class " + poles[i].getClass().getName(), gl, glu);
        }
    }
}
