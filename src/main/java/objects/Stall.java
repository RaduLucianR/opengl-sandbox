package objects;

import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import objects.lights.LightPole;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWSTALL;
import shaders.ShaderPrograms;

public class Stall implements Renderable {
    private final Vector3f position;

    public Stall(Vector3f position) {
        this.position = position;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        gl.glDisable(gl.GL_LIGHT0);
        gl.glColorMaterial ( gl.GL_FRONT_AND_BACK, gl.GL_EMISSION);
        gl.glColorMaterial ( gl.GL_FRONT_AND_BACK, gl.GL_EMISSION);
        gl.glColorMaterial ( gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT_AND_DIFFUSE ) ;
        gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
        //ambient, Vector3f diffuse, Vector3f specular, float shininess
        Material stallMaterial = new Material(new Vector3f(0,0,0),new Vector3f(1,1,1),new Vector3f(1,1,1),1.0f);
        stallMaterial.use(gl);
        //gl.glDisable(gl.GL_CULL_FACE);
        ShaderPrograms.useTerrainShader(gl);
        if (SHOWSTALL.getValue()) {
            //gl.glTranslatef(position.x,position.y,position.z);
            //gl.glScalef(10,10,10);

            gl.glPushMatrix();
            gl.glTranslatef(10,10,0.5f);
            gl.glColor3f(0.5f,0.5f,0.5f);
            gl.glScalef(2.5f,1.5f,1);
            glut.glutSolidCube(1f);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(11.175f,10.675f,1.75f);
            gl.glColor3f(0.0f,0.0f,0.0f);
            gl.glScalef(0.1f,0.1f,1.25f);
            glut.glutSolidCube(1.5f);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(11.175f,9.325f,1.75f);
            gl.glColor3f(0.0f,0.0f,0.0f);
            gl.glScalef(0.1f,0.1f,1);
            glut.glutSolidCube(1.5f);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(8.825f,10.675f,1.75f);
            gl.glColor3f(0.0f,0.0f,0.0f);
            gl.glScalef(0.1f,0.1f,1.25f);
            glut.glutSolidCube(1.5f);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(8.825f,9.325f,1.75f);
            gl.glColor3f(0.0f,0.0f,0.0f);
            gl.glScalef(0.1f,0.1f,1);
            glut.glutSolidCube(1.5f);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(10,10,2.65f);
            gl.glRotatef(15,1,0,0);
            gl.glColor3f(0.5f,0.5f,0.0f);
            gl.glScalef(2.7f,1.7f,0.3f);
            glut.glutSolidCube(1f);
            gl.glPopMatrix();

            gl.glPopMatrix();
        }
    }
}