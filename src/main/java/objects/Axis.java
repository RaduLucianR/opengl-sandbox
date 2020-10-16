package objects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Matrix3d;
import org.joml.Vector3f;
import shaders.ShaderPrograms;

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

    void drawFilledCircle(GL2 gl, float x, float y, float radius){
        int i;
        int triangleAmount = 20; //# of triangles used to draw circle

        //GLfloat radius = 0.8f; //radius
        float twicePi = (float) (2.0f * Math.PI);

        gl.glBegin(gl.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y); // center of circle
        for(i = 0; i <= triangleAmount;i++) {
            gl.glVertex2f(
                    x + (float)(radius * Math.cos(i *  twicePi / triangleAmount)),
                    (float) (y + (radius * Math.sin(i * twicePi / triangleAmount)))
            );
        }
        gl.glEnd();
    }

    public void drawArrow(GL2 gl, GLUT glut) {
        /** Draw cone*/
        gl.glPushMatrix();
        gl.glRotatef(90,0,1,0);
        gl.glTranslated(0,0,0.8);
        glut.glutSolidCone(0.1,0.2,20,20);
        gl.glPopMatrix();

        /** Draw disc*/
        gl.glPushMatrix();
        gl.glRotatef(90,0,1,0);
        gl.glTranslatef(0,0,0.8f);
        drawFilledCircle(gl,0,0,0.1f);
        gl.glPopMatrix();

        /** Draw cylinder*/
        gl.glPushMatrix();
        gl.glRotatef(90,0,1,0);
        glut.glutSolidCylinder(0.05,0.8,20,20);
        gl.glPopMatrix();
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        ShaderPrograms.useDefaultShader(gl);
        if (SHOWAXIS.getValue()) {
            gl.glDisable(gl.GL_CULL_FACE);

            gl.glDisable(GL.GL_CULL_FACE);

            gl.glPushMatrix();
            gl.glColor3f(1,1,0);
            glut.glutSolidSphere(0.2,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor3f(1,0,0);
            drawArrow(gl,glut);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor3f(0,1,0);
            gl.glRotatef(90,0,0,1);
            drawArrow(gl,glut);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor3f(0,0,1);
            gl.glRotatef(-90,0,1,0);
            drawArrow(gl,glut);
            gl.glPopMatrix();
            /**
            gl.glPushMatrix();
            gl.glColor3f(1,0,0);
            gl.glRotatef(90,0,1,0);
            gl.glTranslated(0,0,0.8);
            glut.glutSolidCone(0.1,0.2,20,20);
            gl.glPopMatrix();


            gl.glPushMatrix();
            gl.glColor3f(1,0,0);
            gl.glRotatef(90,0,1,0);
            glut.glutSolidCylinder(0.05,0.8,20,20);
            gl.glPopMatrix();


            gl.glPushMatrix();
            gl.glColor3f(0,1,0);
            gl.glRotatef(270,1,0,0);
            gl.glTranslated(0,0,0.8);
            glut.glutSolidCone(0.1,0.2,20,200);
            gl.glPopMatrix();


            gl.glPushMatrix();
            gl.glColor3f(0,1,0);
            gl.glRotatef(270,1,0,0);
            glut.glutSolidCylinder(0.05,0.8,20,20);
            gl.glPopMatrix();


            gl.glPushMatrix();
            gl.glColor3f(0,0,1);
            gl.glTranslated(0,0,0.8);
            glut.glutSolidCone(0.1,0.2,20,20);
            gl.glPopMatrix();


            gl.glPushMatrix();
            gl.glColor3f(0,0,1);
            glut.glutSolidCylinder(0.05,0.8,20,20);
            gl.glPopMatrix();
             */
        }
    }
}
