package objects;

import com.jogamp.opengl.GL;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.TextureImg;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import static java.util.Arrays.asList;

import java.nio.FloatBuffer;
import java.util.List;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWCONTROLPOLYGONS;
import static sandbox.Sandbox.CheckBoxID.SHOWPATH;
import shaders.ShaderPrograms;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.graph.curve.opengl.GLRegion;
public class Road implements Renderable {

    private final Vector3f position;

    double bezier(double A,  // Start value
                  double B,  // First control value
                  double C,  // Second control value
                  double D,  // Ending value
                  double t)  // Parameter 0 <= t <= 1
    {
        double s = 1 - t;
        double AB = A*s + B*t;
        double BC = B*s + C*t;
        double CD = C*s + D*t;
        double ABC = AB*s + BC*t;
        double BCD = BC*s + CD*t;
        return ABC*s + BCD*t;
    }

    public Road(Vector3f position) {
        this.position = position;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWPATH.getValue()) {
            gl.glColor3f(1,0,0);
            float t = 0.0f;
            Vector3f A = new Vector3f(20,20,0.0001f);
            Vector3f B = new Vector3f(-20,20,0.0001f);
            Vector3f C = new Vector3f(20,-20,0.0001f);
            Vector3f D = new Vector3f(-20,-20,0.0001f);

            float[] axisX = new float[1001];
            float[] axisY = new float[1001];

            int  i = 0;
            while(true)
            {
                if(t > 1.0f) break;

                axisX[i] = (float) bezier(A.x,B.x,C.x,D.x,t);
                axisY[i] = (float) bezier(A.y,B.y,C.y,D.y,t);

                i++;

                t += 0.01f;
            }

            float width = 3f;
            Vector3f du = new Vector3f(0);
            Vector3f dv = new Vector3f(0);
            Vector3f point = new Vector3f(0);
            Vector3f pointPrev = new Vector3f(0);

            gl.glBegin(GL2.GL_QUAD_STRIP);
            for (int j = 1; j < i; j++) {
                point.x = axisX[j];
                point.y = axisY[j];
                pointPrev.x = axisX[j-1];
                pointPrev.y = axisY[j-1];
                point.sub(pointPrev,du);
                dv.x = du.y;
                dv.y = -du.x;
                dv.mul(width/(2.0f*dv.length()));
                Vector3f p0 = new Vector3f(0);
                Vector3f p1 = new Vector3f(0);
                Vector3f p2 = new Vector3f(0);
                Vector3f p3 = new Vector3f(0);

                point.sub(dv,p0);
                point.add(dv,p1);
                pointPrev.add(dv,p2);
                pointPrev.sub(dv,p3);

                gl.glVertex3f(p0.x,p0.y,0.001f);
                gl.glVertex3f(p1.x,p1.y,0.001f);
            }
            gl.glEnd();
        }
    }

   /** computes point at parameter t on Bezier spline. */
    public Vector3f getCubicBezierSplinePnt(double t){
        return null;
    }

   /** computes tangent at parameter t on Bezier spline. */
    public Vector3f getCubicBezierSplineTng(double t){
        return null;
    }

   /** computes point at parameter t on cubic Bezier with given control points P. */
   private static Vector3f getCubicBezierPnt(final Vector3f[] P, double t) {
        return null;
    }

   /** computes tangent at parameter t on cubic Bezier with given control points P. */
   private static Vector3f getCubicBezierTng(final Vector3f[] P, double t) {
        return null;
    }
}
