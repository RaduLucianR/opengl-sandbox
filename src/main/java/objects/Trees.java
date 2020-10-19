package objects;

import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import objects.lights.LightPole;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTREES;
import shaders.ShaderPrograms;

public class Trees implements Renderable {
    private final Vector3f position;

    public Trees(Vector3f position) {
        this.position = position;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWTREES.getValue()) {
            gl.glPushMatrix();
            gl.glTranslatef(-10,-10,0);

            gl.glPushMatrix();
            gl.glColor3f(0,0,0);
            glut.glutSolidCylinder(0.75,5,20,20);
            gl.glTranslatef(0,0,5);
            glut.glutSolidCone(4,3f,20,20);
            gl.glTranslatef(0,0,1.5f);
            glut.glutSolidCone(3,3f,20,20);
            gl.glTranslatef(0,0,1.5f);
            glut.glutSolidCone(2,3f,20,20);
            gl.glTranslatef(0,0,1.5f);
            glut.glutSolidCone(1,3f,20,20);


            gl.glPopMatrix();

            gl.glPopMatrix();
        }
    }
}