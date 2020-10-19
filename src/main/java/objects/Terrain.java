package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.ShaderProgram;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTERRAIN;
import static sandbox.Sandbox.ComboBoxID.VIEWMODE;
import static sandbox.Sandbox.SliderID.SLIDER1;
import static sandbox.Sandbox.SliderID.SLIDER2;

import shaders.ShaderPrograms;

public class Terrain implements Renderable {
    private final double sizeX=50;  // meter
    private final double sizeY=50;    // meter
    private final double sizeZ=1;  // meter

    public Terrain() {
    }
    private float rtri =0.0f;
    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWTERRAIN.getValue()) {
            gl.glEnable ( gl.GL_COLOR_MATERIAL ) ;
            Material terrainMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0,0,0),32f);
            terrainMaterial.use(gl);
            ShaderPrograms.useTerrainShader(gl);
            //gl.glClearDepth(1.0f);
            //gl.glEnable(gl.GL_DEPTH_TEST); // Enable Depth Test to make shape solid
            //gl.glDepthFunc(gl.GL_LEQUAL);
            //gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, gl.GL_NICEST);  // Clear The Screen And The Depth Buffer
            //gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

            gl.glPushMatrix();
            /**
            gl.glBegin(gl.GL_QUADS); // Start Drawing The Cube
            gl.glColor3f(0, 1, 0);   //green color
            gl.glVertex3f(25.0f, 25.0f, -1.0f); // Top Right Of The Quad (Top)
            gl.glVertex3f(-25.0f, 25.0f, -1.0f); // Top Left Of The Quad (Top)
            gl.glVertex3f(-25.0f, 25.0f, 1.0f); // Bottom Left Of The Quad (Top)
            gl.glVertex3f(25.0f, 25.0f, 1.0f); // Bottom Right Of The Quad (Top)
            gl.glColor3f(0f, 1f, 0f); //green color
            gl.glVertex3f(25.0f, -25.0f, 1.0f); // Top Right Of The Quad
            gl.glVertex3f(-25.0f, -25.0f, 1.0f); // Top Left Of The Quad
            gl.glVertex3f(-25.0f, -25.0f, -1.0f); // Bottom Left Of The Quad
            gl.glVertex3f(25.0f, -25.0f, -1.0f); // Bottom Right Of The Quad
            gl.glColor3f(0f, 0f, 1f); //blue color
            gl.glVertex3f(25.0f, 25.0f, 1.0f); // Top Right Of The Quad (Front)
            gl.glVertex3f(-25.0f, 25.0f, 1.0f); // Top Left Of The Quad (Front)
            gl.glVertex3f(-25.0f, -25.0f, 1.0f); // Bottom Left Of The Quad
            gl.glVertex3f(25.0f, -25.0f, 1.0f); // Bottom Right Of The Quad
            gl.glColor3f(0f, 0f, 1f); //blue color
            gl.glVertex3f(25.0f, -25.0f, -1.0f); // Bottom Left Of The Quad
            gl.glVertex3f(-25.0f, -25.0f, -1.0f); // Bottom Right Of The Quad
            gl.glVertex3f(-25.0f, 25.0f, -1.0f); // Top Right Of The Quad (Back)
            gl.glVertex3f(25.0f, 25.0f, -1.0f); // Top Left Of The Quad (Back)
            gl.glColor3f(1f, 0f, 0f); //red color
            gl.glVertex3f(-25.0f, 25.0f, 1.0f); // Top Right Of The Quad (Left)
            gl.glVertex3f(-25.0f, 25.0f, -1.0f); // Top Left Of The Quad (Left)
            gl.glVertex3f(-25.0f, -25.0f, -1.0f); // Bottom Left Of The Quad
            gl.glVertex3f(-25.0f, -25.0f, 1.0f); // Bottom Right Of The Quad
            gl.glColor3f(1f, 0f, 0f); //red color
            gl.glVertex3f(25.0f, 25.0f, -1.0f); // Top Right Of The Quad (Right)
            gl.glVertex3f(25.0f, 25.0f, 1.0f); // Top Left Of The Quad
            gl.glVertex3f(25.0f, -25.0f, 1.0f); // Bottom Left Of The Quad
            gl.glVertex3f(25.0f, -25.0f, -1.0f); // Bottom Right Of The Quad
            gl.glEnd(); // Done Drawing The Quad
            gl.glFlush();
             */
            gl.glColor3f(0.015f,0.7f,0.25f);
            gl.glScalef(50,50,1);
            gl.glTranslatef(0,0,-0.5f);
            glut.glutSolidCube(1);
            gl.glPopMatrix();
        }
    }
}
