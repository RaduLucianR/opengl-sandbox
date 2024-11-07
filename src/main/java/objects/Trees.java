package objects;

import com.jogamp.opengl.GL;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import objects.lights.LightPole;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTREES;
import shaders.ShaderPrograms;
/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
public class Trees implements Renderable {
    private final Vector3f position;
    private final float size;

    public Trees(Vector3f position,float size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWTREES.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            gl.glDisable(GL.GL_CULL_FACE);
            gl.glEnable(gl.GL_DEPTH_TEST);
            gl.glDepthFunc(GL.GL_LEQUAL);
            Material treesMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.25f,0.25f,0.25f),80f);
            treesMaterial.use(gl);
            ShaderPrograms.usePhongShader(gl);

            gl.glPushMatrix();
            gl.glTranslatef(position.x,position.y,position.z);
            gl.glScalef(size,size,size);
            gl.glPushMatrix();
            gl.glColor3f(0.3f,0,0);
            glut.glutSolidCylinder(0.75,5,20,20);
            gl.glTranslatef(0,0,5);
            gl.glColor3f(0,0.3f,0);
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