package objects;

import com.jogamp.opengl.GL;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import objects.lights.LightPole;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTRAIN;
import shaders.ShaderPrograms;


import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
public class TrainNoMovement implements Renderable {
    private final Vector3f position;
    private final float size;

    public TrainNoMovement(Vector3f position,float size) {
        this.size = size;
        this.position = position;
    }

    public void drawTrainNoMov(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(position.x,position.y,position.z);
        gl.glScalef(size,size,size);
        gl.glPushMatrix();
        gl.glColor3f(0,0,0);
        gl.glTranslatef(0,0,0.5f);
        gl.glScalef(1.5f,0.75f,0.25f);
        glut.glutSolidCube(3);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.5f,0.5f,0.5f);
        gl.glTranslatef(-1.5f,0,2.0f);
        gl.glScalef(0.6f,0.75f,1f);
        glut.glutSolidCube(3);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.5f,0.5f,0.5f);
        gl.glTranslatef(-0.60f,0,1.625f);
        gl.glRotatef(90,0,1,0);
        glut.glutSolidCylinder(1,3f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(-1.65f,-1.1f,0.8f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.8f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(-0.25f,-1.1f,0.6f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.6f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(0.95f,-1.1f,0.6f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.6f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(2.15f,-1.1f,0.6f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.6f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(-0.25f,1.3f,0.6f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.6f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(0.95f,1.3f,0.6f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.6f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(2.15f,1.3f,0.6f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.6f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(-1.65f,1.3f,0.8f);
        gl.glRotatef(90,1,0,0);
        glut.glutSolidCylinder(0.8f,0.2f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.5f,0.5f,0.5f);
        gl.glTranslatef(1.25f,0,2f);
        glut.glutSolidCylinder(0.35,1.25f,20,20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(1.25f,0,3.25f);
        glut.glutSolidCone(0.45,0.5,20,20);
        gl.glPopMatrix();

        gl.glPopMatrix();
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {

        if (SHOWTRAIN.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            gl.glDisable(GL.GL_CULL_FACE);
            gl.glEnable(gl.GL_DEPTH_TEST);
            gl.glDepthFunc(GL.GL_LEQUAL);
            Material trainMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.25f,0.25f,0.25f),80f);
            trainMaterial.use(gl);
            ShaderPrograms.usePhongShader(gl);


            drawTrainNoMov(gl,glut);


        }
    }
}