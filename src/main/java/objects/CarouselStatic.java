package objects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.Material;
import org.joml.Vector3f;
import shaders.ShaderPrograms;

import static sandbox.Sandbox.SliderID.CAROUSEL_SIZE;
import static sandbox.Sandbox.SliderID.CAROUSEL_SPEED;
import static sandbox.Sandbox.CheckBoxID.SHOWCAROUSEL;
import static sandbox.Sandbox.CheckBoxID.SHOWTERRAIN;
/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
public class CarouselStatic implements Renderable {
    private float size;

    private final Vector3f position;

    private float rotation;  //for angle of rotation
    private float value;
    private float heightDown;
    private float heightUp;
    private boolean stateDown;
    private boolean stateUp;
    private double animation;

    public CarouselStatic(Vector3f position, float size) {
        this.position = position;
        this.size = size;
    }

    public void drawChairDown(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0,0,heightDown);
        gl.glPushMatrix();
        gl.glTranslatef(0,0,0.3f);
        gl.glScalef(0.3f,0.2f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0,0,0.6f);
        gl.glScalef(0.25f,0.1f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0.0f,-0.15f,0.7f);
        gl.glScalef(0.3f,0.05f,0.4f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(0.25f,0f,0.8f);
        gl.glScalef(0.05f,0.15f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(-0.25f,0f,0.8f);
        gl.glScalef(0.05f,0.15f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();
        if (stateDown)
        {

            heightDown += 0.001;
            if(heightDown > 1.1f) { stateDown = false; }

        }
        else if (!stateDown)
        {

            heightDown -= 0.001;
            if(heightDown < 0.15f) { stateDown = true; }

        }
        gl.glPopMatrix();

    }

    public void drawChairUp(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0,0,1.1f);
        gl.glTranslatef(0,0,heightUp);
        gl.glPushMatrix();
        gl.glTranslatef(0,0,0.3f);
        gl.glScalef(0.3f,0.2f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0,0,0.6f);
        gl.glScalef(0.25f,0.1f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0.0f,-0.15f,0.7f);
        gl.glScalef(0.3f,0.05f,0.4f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(0.25f,0f,0.8f);
        gl.glScalef(0.05f,0.15f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glTranslatef(-0.25f,0f,0.8f);
        gl.glScalef(0.05f,0.15f,0.05f);
        glut.glutSolidCube(2);
        gl.glPopMatrix();
        if (stateUp)
        {

            heightUp += 0.001;
            if(heightUp > 0.0f) { stateUp = false; }

        }
        else if (!stateUp)
        {

            heightUp -= 0.001;
            if(heightUp < -0.95f) { stateUp = true; }

        }
        gl.glPopMatrix();

    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWCAROUSEL.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            gl.glDisable(GL.GL_CULL_FACE);
            gl.glEnable(gl.GL_DEPTH_TEST);
            gl.glDepthFunc(GL.GL_LEQUAL);
            Material carrouselMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.25f,0.25f,0.25f),80f);
            carrouselMaterial.use(gl);
            ShaderPrograms.useCarrouselShader(gl);

            gl.glPushMatrix();
            gl.glScalef(size,size,size);
            gl.glTranslatef(position.x,position.y,position.z);
            gl.glRotatef( rotation, 0.0f, 0.0f, 1.0f );
            gl.glPushMatrix();
            gl.glColor4f(0.8f, 0.0f, 0.8f,0.0f);
            gl.glTranslatef(0,0,0.0f);
            glut.glutSolidCylinder(5,0.30f,50,50);

            gl.glColor4f(1.0f, 0.0f, 1.0f,0.0f);
            glut.glutSolidCylinder(5.5,0.10f,50,50);
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            glut.glutSolidCylinder(0.5f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(1.0f, 1.0f, 0.1f,0.0f);

            gl.glTranslatef(1.5f,0.0f,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.3f,0.0f,0.0f);
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(45,0,0,1);
            gl.glTranslatef(4.8f,0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(90,0,0,1);
            gl.glColor4f(1.0f, 1.0f, 0.1f,0.0f);

            gl.glTranslatef(1.5f,0.0f,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.3f,0.0f,0.0f);
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(135,0,0,1);
            gl.glTranslatef(4.8f,0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(180,0,0,1);
            gl.glColor4f(1.0f, 1.0f, 0.1f,0.0f);

            gl.glTranslatef(1.5f,0.0f,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.3f,0.0f,0.0f);
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(225,0,0,1);
            gl.glTranslatef(4.8f,0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(270,0,0,1);
            gl.glColor4f(1.0f, 1.0f, 0.1f,0.0f);

            gl.glTranslatef(1.5f,0.0f,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.0f,0,0.0f);
            glut.glutSolidCylinder(0.075f,3.0f,20,20);

            gl.glTranslatef(1.3f,0.0f,0.0f);
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor4f(0.7f, 0.4f, 0.0f,0.0f);
            gl.glRotated(315,0,0,1);
            gl.glTranslatef(4.8f,0.0f,0.0f);
            glut.glutSolidCylinder(0.15f,3.0f,20,20);
            gl.glPopMatrix();

            gl.glPushMatrix();
            for (int i = 0;i<=7;i++) {
                float rotation = (float) (45.0);
                if (i%2==0)
                    gl.glColor3f(1,1,1);
                else
                    gl.glColor3f(1,0,0);
                gl.glRotated(rotation,0,0,1);
                gl.glBegin(gl.GL_TRIANGLES);
                gl.glNormal3f(0,0,-1.0f);
                gl.glVertex3f(0f, 0f, 3.01f); // Top Right Of The Quad (Top)
                gl.glVertex3f(4.8f, 0f, 3.01f);
                gl.glVertex3f(3.39f, 3.39f, 3.01f); // Top Right Of The Quad (Top)
                gl.glEnd();// Start Drawing The Cube
                gl.glNormal3f(0.2960f,0.1231f,0.9472f);
                gl.glBegin(gl.GL_TRIANGLES);
                gl.glVertex3f(0f, 0f, 4.51f); // Top Right Of The Quad (Top)
                gl.glVertex3f(4.8f, 0f, 3.01f);
                gl.glVertex3f(3.39f, 3.39f, 3.01f); // Top Right Of The Quad (Top)
                gl.glEnd();// Start Drawing The Cube
            }
            gl.glFlush();
            gl.glPopMatrix();

            for (int i = 0;i<=3;i++) {
                float rotation = (float) (90.0);
                //if (i%2==0){
                //    height=0.15f;
                //}
                //else{
                //    height=1.1f;
                //}
                gl.glRotated(rotation, 0, 0, 1);
                gl.glPushMatrix();
                gl.glTranslatef(1.5f, 0.2f, 0.5f);
                gl.glColor3f(0.9f, 0.5f, 0.3f);
                if (i%2==0){
                    drawChairDown(gl,glut);
                }
                else{
                    drawChairUp(gl,glut);
                }

                gl.glTranslatef(1.0f, 0, 0.f);
                gl.glColor3f(0.9f, 0.5f, 0.3f);
                if (i%2==0){
                    drawChairDown(gl,glut);
                }
                else{
                    drawChairUp(gl,glut);
                }

                gl.glTranslatef(1.0f, 0, 0.0f);
                gl.glColor3f(0.9f, 0.5f, 0.3f);
                if (i%2==0){
                    drawChairDown(gl,glut);
                }
                else{
                    drawChairUp(gl,glut);
                }

                gl.glPopMatrix();

            }
            gl.glPopMatrix();
            value = CAROUSEL_SPEED.getValue();
            value = (float) (value / 100.0);
            rotation += value;  //assigning the angle

        }
    }
}
