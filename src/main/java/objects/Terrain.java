package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.ShaderProgram;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTERRAIN;
import static sandbox.Sandbox.ComboBoxID.VIEWMODE;


import shaders.ShaderPrograms;
/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
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
            Material terrainMaterial = new Material(new Vector3f(0.5f,0.5f,0.5f),new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.25f,0.25f,0.25f),80f);
            terrainMaterial.use(gl);
            ShaderPrograms.useTerrainShader(gl);
            //gl.glClearDepth(1.0f);
            //gl.glEnable(gl.GL_DEPTH_TEST); // Enable Depth Test to make shape solid
            //gl.glDepthFunc(gl.GL_LEQUAL);
            //gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, gl.GL_NICEST);  // Clear The Screen And The Depth Buffer
            //gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

            gl.glPushMatrix();
            gl.glColor3f(0.015f,0.7f,0.25f);
            gl.glScalef(50,50,1);
            gl.glTranslatef(0,0,-0.5f);
            glut.glutSolidCube(1);
            gl.glPopMatrix();
        }
    }
}
