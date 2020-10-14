package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Matrix3d;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWAXIS;
import static sandbox.Sandbox.SliderID.SLIDER1;
import static sandbox.Sandbox.SliderID.SLIDER2;

public class Axis implements Renderable {
    private Vector3f pos;
    private float sf;

    public Axis(Vector3f pos, float sf) {
        this.pos = pos;
        this.sf = sf;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWAXIS.getValue()) {
            float slider1 = (float) SLIDER1.getValue() - 50;
            float slider2 = (float) SLIDER2.getValue() - 50;

            float xLen = 3+slider1;
            float yLen = 3+slider2;
            float zLen = 4;

            /** Draw yellow sphere*/
            gl.glPushMatrix();
            gl.glColor3f(1,1,0);
            glut.glutSolidSphere(0.2,100,100);
            gl.glPopMatrix();

            /** Draw red cone*/
            gl.glPushMatrix();
            gl.glColor3f(1,0,0);
            gl.glRotatef(90,0,1,0);
            gl.glTranslated(0,0,1);
            glut.glutSolidCone(0.1,0.2,100,100);
            gl.glPopMatrix();

            /** Draw red cylinder*/
            gl.glPushMatrix();
            gl.glColor3f(1,0,0);
            gl.glRotatef(90,0,1,0);
            glut.glutSolidCylinder(0.05,0.8,100,100);
            gl.glPopMatrix();

            /** Draw green cone*/
            gl.glPushMatrix();
            gl.glColor3f(0,1,0);
            gl.glRotatef(270,1,0,0);
            gl.glTranslated(0,0,0.8);
            glut.glutSolidCone(0.1,0.2,100,100);
            gl.glPopMatrix();

            /** Draw green cylinder*/
            gl.glPushMatrix();
            gl.glColor3f(0,1,0);
            gl.glRotatef(0,1,0,0);
            glut.glutSolidCylinder(0.05,5,100,100);
            gl.glPopMatrix();

            /** Draw blue cone*/
            gl.glPushMatrix();
            gl.glColor3f(0,0,1);
            gl.glTranslated(0,0,0.8);
            glut.glutSolidCone(0.1,0.2,100,100);
            gl.glPopMatrix();

            /** Draw blue cylinder*/
            gl.glPushMatrix();
            gl.glColor3f(0,0,1);
            glut.glutSolidCylinder(0.05,0.8,100,100);
            gl.glPopMatrix();

        }
    }
}
