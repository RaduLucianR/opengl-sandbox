package objects;

import com.jogamp.opengl.GL;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import objects.lights.LightPole;
import org.joml.Vector2f;
import org.joml.Vector3f;

import shaders.ShaderPrograms;


import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import static sandbox.Sandbox.CheckBoxID.*;

public class Train implements Renderable {
    private final Vector3f position;

    public Train(Vector3f position) {
        this.position = position;
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
        if (SHOWTRAIN.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            gl.glDisable(GL.GL_CULL_FACE);
            gl.glEnable(gl.GL_DEPTH_TEST);
            gl.glDepthFunc(GL.GL_LEQUAL);
            Material trainMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.25f,0.25f,0.25f),80f);
            trainMaterial.use(gl);
            ShaderPrograms.usePhongShader(gl);

            List<Vector3f[]> curves = new ArrayList<>();
            curves.add(curve1);
            curves.add(curve2);
            curves.add(curve3);
            float sinus = (float) tAnim;
            float angle = 180;
            for (int i = 1; i <= tAnim/2; i++) {
                if (sinus >= Math.PI/2 + 2*Math.PI*i && sinus <= 3*Math.PI/2 + 2*Math.PI*i) {
                    sinus += Math.PI;
                }
            }
            sinus = (float) (Math.sin(sinus)+1)/2;
            Road obj = new Road(new Vector3f(0));
            Vector3f point = new Vector3f(-5,-15,0.0001f);
            if (SHOWTRAINMOVING.getValue()) {
                point = obj.getCubicBezierSplinePnt(curves, sinus);
                point.z = 0.0001f;
                if (sinus >= 0.0f && sinus <= 0.05) {
                    angle = 180;
                }
                if (sinus >= 0.05f && sinus <= 0.1) {
                    angle = 163;
                }
                if (sinus >= 0.1f && sinus <= 0.3) {
                    angle = 145;
                }
                if (sinus >= 0.3f && sinus <= 0.7) {
                    angle = 135;
                }
                if (sinus >= 0.7f && sinus <= 0.15) {
                    angle = 110;
                }
                if (sinus >= 0.15f && sinus <= 0.245) {
                    angle = 70;
                }
                if (sinus >= 0.245f && sinus <= 0.40) {
                    angle = 40;
                }
                if (sinus >= 0.40 && sinus <= 0.45) {
                    angle = 15;
                }
                if (sinus >= 0.45f && sinus <= 0.50) {
                    angle = 12;
                }
                if (sinus >= 0.50f && sinus <= 0.55) {
                    angle = 5;
                }
                if (sinus >= 0.55f && sinus <= 0.60) {
                    angle = -30;
                }
                if (sinus >= 0.60f && sinus <= 0.63) {
                    angle = -60;
                }
                if (sinus >= 0.63f && sinus <= 0.67) {
                    angle = -90;
                }
                if (sinus >= 0.67f && sinus <= 0.75) {
                    angle = -60;
                }
                if (sinus >= 0.67f && sinus <= 0.75) {
                    angle = -30;
                }
                if (sinus >= 0.75f && sinus <= 0.77) {
                    angle = -5;
                }
                if (sinus >= 0.77f && sinus <= 0.78) {
                    angle = -10;
                }
                if (sinus >= 0.78f && sinus <= 0.80) {
                    angle = -30;
                }
                if (sinus >= 0.80f && sinus <= 0.83) {
                    angle = -40;
                }
                if (sinus >= 0.83f && sinus <= 0.85) {
                    angle = -70;
                }
                if (sinus >= 0.85f && sinus <= 0.87) {
                    angle = -90;
                }
                if (sinus >= 0.87f && sinus <= 0.90) {
                    angle = -110;
                }
                if (sinus >= 0.90f && sinus <= 0.95) {
                    angle = -160;
                }
                if (sinus >= 0.95f && sinus <= 1.0) {
                    angle = 180;
                }
            }

            gl.glPushMatrix();
            gl.glTranslatef(point.x,point.y,0);
            gl.glRotatef(angle,0,0,1);
            gl.glTranslatef(-point.x,-point.y,0);

            gl.glPushMatrix();
            //gl.glRotatef(20,point.x,point.y, 0);
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(0,0,0);
            gl.glTranslatef(0,0,0.5f);
            gl.glScalef(1.5f,0.75f,0.25f);
            glut.glutSolidCube(3);
            gl.glPopMatrix();

            gl.glPushMatrix();
            //gl.glRotatef(10,point.x,point.y, point.z);
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(0.5f,0.5f,0.5f);
            gl.glTranslatef(-1.5f,0,2.0f);
            gl.glScalef(0.6f,0.75f,1f);
            glut.glutSolidCube(3);
            gl.glPopMatrix();

            gl.glPushMatrix();
            //gl.glRotatef(20,0,0, 0);
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(0.5f,0.5f,0.5f);
            gl.glTranslatef(-0.60f,0,1.625f);
            gl.glRotatef(90,0,1,0);
            glut.glutSolidCylinder(1,3f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(-1.65f,-1.1f,0.8f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.8f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(-0.25f,-1.1f,0.6f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.6f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(0.95f,-1.1f,0.6f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.6f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(2.15f,-1.1f,0.6f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.6f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(-0.25f,1.3f,0.6f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.6f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(0.95f,1.3f,0.6f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.6f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(2.15f,1.3f,0.6f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.6f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,1,1);
            gl.glTranslatef(-1.65f,1.3f,0.8f);
            gl.glRotatef(90,1,0,0);
            glut.glutSolidCylinder(0.8f,0.2f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(1,0,0);
            gl.glTranslatef(1.25f,0,2f);
            glut.glutSolidCylinder(0.35,1.25f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(point.x, point.y, point.z);
            gl.glColor3f(0,1,0);
            gl.glTranslatef(1.25f,0,3.25f);
            glut.glutSolidCone(0.45,0.5,20,20);
            gl.glPopMatrix();

            gl.glPopMatrix();

        }
    }
}