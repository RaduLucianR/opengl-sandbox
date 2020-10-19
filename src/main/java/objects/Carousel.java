package objects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.Material;
import org.joml.Vector3f;
import shaders.ShaderPrograms;

import static sandbox.Sandbox.CheckBoxID.SHOWCAROUSEL;
import static sandbox.Sandbox.CheckBoxID.SHOWTERRAIN;

public class Carousel implements Renderable {
    private final float size;

    private final Vector3f position;

    private float rtri;  //for angle of rotation
    private float height;
    private boolean state;

    public Carousel(Vector3f position, float size) {
        this.position = position;
        this.size = size;
    }

    public void drawChair(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0,0,height);
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
        gl.glPopMatrix();
        if (state == true)
        {

            height += 0.001;
            if(height > 1.1f) { state = false; }

        }
        else if (state == false)
        {

            height -= 0.001;
            if(height < 0.15f) { state = true; }

        }

    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        //gl.glColorMaterial ( gl.GL_FRONT_AND_BACK, gl.GL_EMISSION);
        //gl.glColorMaterial ( gl.GL_FRONT_AND_BACK, gl.GL_EMISSION);
       // gl.glColorMaterial ( gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT_AND_DIFFUSE ) ;
       // gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
        //ambient, Vector3f diffuse, Vector3f specular, float shininess
        //Material carouselMaterial = new Material(new Vector3f(0,0,0),new Vector3f(1,1,1),new Vector3f(1,1,1),1.0f);
        //carouselMaterial.use(gl);
        //gl.glDisable(gl.GL_CULL_FACE);
        //ShaderPrograms.useTerrainShader(gl);
        if (SHOWCAROUSEL.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            gl.glDisable(GL.GL_CULL_FACE);
            gl.glEnable(gl.GL_DEPTH_TEST);
            gl.glDepthFunc(GL.GL_LEQUAL);
            Material carrouselMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.0f,0.0f,0.0f),new Vector3f(0.25f,0.25f,0.25f),80f);
            carrouselMaterial.use(gl);
            ShaderPrograms.useCarrouselShader(gl);

            gl.glPushMatrix();
            gl.glScalef(size,size,size);
            gl.glTranslatef(position.x,position.y,position.z);
            gl.glRotatef( rtri, 0.0f, 0.0f, 1.0f );
            gl.glPushMatrix();
            gl.glColor4f(0.8f, 0.0f, 0.8f,0.0f);
            gl.glTranslatef(0,0,0.0f);
            glut.glutSolidCylinder(5,0.30f,20,20);

            gl.glColor4f(1.0f, 0.0f, 1.0f,0.0f);
            glut.glutSolidCylinder(5.5,0.10f,20,20);
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
                gl.glVertex3f(0f, 0f, 3.01f); // Top Right Of The Quad (Top)
                gl.glVertex3f(4.8f, 0f, 3.01f);
                gl.glVertex3f(3.39f, 3.39f, 3.01f); // Top Right Of The Quad (Top)
                gl.glEnd();// Start Drawing The Cube
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
                    gl.glRotated(rotation, 0, 0, 1);
                    gl.glPushMatrix();
                    gl.glTranslatef(1.5f, 0.2f, 0.5f);
                    gl.glColor3f(0.9f, 0.5f, 0.3f);
                    drawChair(gl, glut);

                    gl.glTranslatef(1.0f, 0, 0.f);
                    gl.glColor3f(0.9f, 0.5f, 0.3f);
                    drawChair(gl, glut);

                    gl.glTranslatef(1.0f, 0, 0.0f);
                    gl.glColor3f(0.9f, 0.5f, 0.3f);
                    drawChair(gl, glut);

                    gl.glPopMatrix();

            }
          gl.glPopMatrix();
            rtri += 0.2f;  //assigning the angle

        }
    }
}
