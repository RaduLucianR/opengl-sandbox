package objects.lights;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWLIGHTPOLES;
import static sandbox.Sandbox.ComboBoxID.DAYNIGHT;
import shaders.ShaderPrograms;

/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */

public class LightPole implements Renderable {
    private final Vector3f POS, FACING;
    private final float SF;
    private final Integer LIGHT;
    private final float ANGLE;// GL_LIGHTi, null ==> no light source

    public LightPole(Vector3f pos, Vector3f tng, float sf, Integer LIGHT, float angle) {
        this.POS = pos;
        this.SF = sf;
        this.FACING = tng;
        this.LIGHT = LIGHT;
        this.ANGLE = angle;
    }
    
    public LightPole(Vector3f pos, float sf, Integer LIGHT,float angle) {
        this(pos, new Vector3f(1,0,0), sf, LIGHT,angle);
    }

    public void drawLightPole(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(POS.x,POS.y,0);
        gl.glRotatef(ANGLE,0,0,1);
        gl.glTranslatef(-POS.x,-POS.y,0);
        gl.glTranslatef(POS.x,POS.y,POS.z);
        gl.glScalef(SF,SF,SF);
        gl.glTranslatef(2.5f,2.5f,0);

        gl.glPushMatrix();
        gl.glColor3f(0.1f,0.1f,0.1f);
        gl.glTranslatef(0,0,0.1f);
        gl.glScalef(5.0f,5.0f,1.0f);
        glut.glutSolidCube(0.2f);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.2f,0.2f,0.2f);
        gl.glTranslatef(0,0,2.25f);
        gl.glScalef(0.1f,0.1f,1.5f);
        glut.glutSolidCube(3);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.2f,0.2f,0.2f);
        gl.glTranslatef(-0.975f,0,4.65f);
        gl.glScalef(0.75f,0.1f,0.1f);
        glut.glutSolidCube(3);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.2f,0.2f,0.2f);
        gl.glTranslatef(-1.2f,0,4.50f);
        glut.glutSolidCube(0.3f);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.8f,0.8f,0);
        gl.glTranslatef(-1.2f,0,3.85f);
        glut.glutSolidCube(1f);
        gl.glPopMatrix();

        gl.glPopMatrix();
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWLIGHTPOLES.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            gl.glEnable(GL.GL_CULL_FACE);
            Material lightPoleMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.25f,0.25f,0.25f),80f);
            lightPoleMaterial.use(gl);
            ShaderPrograms.usePhongShader(gl);
            drawLightPole(gl,glut);
        }
    }
}
