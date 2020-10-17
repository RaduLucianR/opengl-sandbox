package objects;

import com.jogamp.opengl.GL;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.TextureImg;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import static java.util.Arrays.asList;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWCONTROLPOLYGONS;
import static sandbox.Sandbox.CheckBoxID.SHOWPATH;
import shaders.ShaderPrograms;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.graph.curve.opengl.GLRegion;
public class Road implements Renderable {

    private final Vector3f position;
    private final TextureImg texture;

    private static List<Vector3f> p0List = new ArrayList<>();
    private static List<Vector3f> p1List = new ArrayList<>();

    public Road(Vector3f position) {
        this.position = position;
        texture = new TextureImg("road.jpg");
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        texture.bind(gl);
        gl.glEnable(gl.GL_TEXTURE_2D);
        /** Define the control points and the number of Bezier curves of the path*/
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

        if (SHOWPATH.getValue()) {
            gl.glColor3f(0.5f,0.3f,0.1f);
            drawBezierCurve(gl,curve1,curve3);
            drawBezierCurve(gl,curve2,curve1);
            drawBezierCurve(gl,curve3,curve2);
        }

        if (SHOWCONTROLPOLYGONS.getValue()) {
            drawControlGraph(gl,glut,curve1,true);
            drawControlGraph(gl,glut,curve2,false);
            drawControlGraph(gl,glut,curve3,false);
        }
    }

   /** computes point at parameter t on Bezier spline. */
    public Vector3f getCubicBezierSplinePnt(double t) {
        return null;
    }

   /** computes tangent at parameter t on Bezier spline. */
    public Vector3f getCubicBezierSplineTng(double t) {
        return null;
    }

   /** computes point at parameter t on cubic Bezier with given control points P. */
   private static Vector3f getCubicBezierPnt(final Vector3f[] P, double t) {
       Vector3f A = P[0];
       Vector3f B = P[1];
       Vector3f C = P[2];
       Vector3f D = P[3];

       float x = bezierCalculations(A.x,B.x,C.x,D.x,t);
       float y = bezierCalculations(A.y,B.y,C.y,D.y,t);

       Vector3f result = new Vector3f(x,y,0.0001f);

       return result;
    }

   /** computes tangent at parameter t on cubic Bezier with given control points P. */
   private static Vector3f getCubicBezierTng(final Vector3f[] P, double t) {
        return null;
   }

   /**Computes one coordinate of a point on a Bezier curve with given coordinate of control points*/
   private static float bezierCalculations(float A, float B, float C, float D, double t) {
       double s = 1 - t;
       double AB = A*s + B*t;
       double BC = B*s + C*t;
       double CD = C*s + D*t;
       double ABC = AB*s + BC*t;
       double BCD = BC*s + CD*t;
       return (float) (ABC*s + BCD*t);
   }

   /**Draws a Bezier curve */
   private static void drawBezierCurve(GL2 gl, Vector3f[] controlPoints, Vector3f[] prevCurve) {
       Vector3f[] bezierPoints = new Vector3f[1001]; //The points of the Bezier curve
       float t = 0.0f; //Parameter that goes from 0 to 1 in order to calculate the Bezier points
       int i = 0; //Index for bezierPoints[]

       //For each t calculate a point of the Bezier curve based on t
       while(true)
       {
           if(t > 1.0f) break; //If t has reached 1 then

           bezierPoints[i] = getCubicBezierPnt(controlPoints,t);

           i++;

           t += 0.01f; //The smaller increment of t, the smoother the Bezier curve is goin to be
       }

       float width = 3f;
       Vector3f du = new Vector3f(0);
       Vector3f dv = new Vector3f(0);
       Vector3f point;
       Vector3f pointPrev;

       gl.glPushMatrix();
       gl.glBegin(GL2.GL_QUAD_STRIP);

       Vector3f p2 = new Vector3f(0);
       Vector3f p3 = new Vector3f(0);

       point = getCubicBezierPnt(prevCurve,0.99);
       pointPrev = bezierPoints[0];

       point.sub(pointPrev,du);
       dv.x = du.y;
       dv.y = -du.x;
       dv.mul(width/(2.0f*dv.length()));

       pointPrev.add(dv,p2);
       pointPrev.sub(dv,p3);

       gl.glVertex3f(p2.x,p2.y,0.001f);
       gl.glVertex3f(p3.x,p3.y,0.001f);

       for (int j = 1; j < i; j++) {
           point = bezierPoints[j];
           pointPrev = bezierPoints[j - 1];

           point.sub(pointPrev,du);
           dv.x = du.y;
           dv.y = -du.x;
           dv.mul(width/(2.0f*dv.length()));

           Vector3f p0 = new Vector3f(0);
           Vector3f p1 = new Vector3f(0);

           point.sub(dv,p0);
           point.add(dv,p1);

           p0List.add(p0);
           p1List.add(p1);

           double tex = (j % 2)*0.5;
           gl.glTexCoord2d(tex, (j+t)*20);
           gl.glVertex3f(p0.x,p0.y,0.001f);

           gl.glTexCoord2d(tex, j*20);
           gl.glVertex3f(p1.x,p1.y,0.001f);
       }
       gl.glEnd();
       gl.glPopMatrix();
   }

   /** Draws a control point*/
   private static void drawControlPoint(GL2 gl, GLUT glut, Vector3f coord) {
        gl.glPushMatrix();
        gl.glTranslatef(coord.x,coord.y,0);
        glut.glutSolidSphere(1,20,20);
        gl.glPopMatrix();
   }

   /** Draws the control graph of a Bezier curve*/
   private static void drawControlGraph(GL2 gl, GLUT glut, Vector3f[] curve, boolean first) {
       for (int i = 0; i < curve.length; i++) {
           gl.glColor3f(1,1,1);
           if (i != curve.length - 1 && i != 0) {
               drawControlPoint(gl,glut,curve[i]);
           } else {
               if (!first) {
                   gl.glColor3f(1,0,0);
                   drawControlPoint(gl,glut,curve[i]);
               }
           }
           if (i > 0) {
               gl.glColor3f(0,0,0);
               gl.glBegin(gl.GL_LINES);
               gl.glVertex3f(curve[i].x, curve[i].y, 0.001f);
               gl.glVertex3f(curve[i - 1].x, curve[i - 1].y,0.001f);
               gl.glEnd();
           }
       }
   }

}
