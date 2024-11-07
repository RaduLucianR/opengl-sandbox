package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.cg.Renderable;
import static sandbox.Sandbox.ComboBoxID.VIEWMODE;
import shaders.ShaderPrograms;
/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
/**
 * Example object
 *
 * It uses 'dropdown1' to conditionally render the cube.
 * It uses 'slider1' and 'slider2' to set its position.
 *
 * In the render function, you can use all OpenGL methods.
 * Make sure you wrap the render function in these two methods:
 *
 *  gl.glPushMatrix();
 *      ...
 *  gl.glPopMatrix();
 *
 * This way none of the other main.java.objects are affected by the matrix operations
 * needed for this object.
 *
 */
public class Cube implements Renderable {
    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (VIEWMODE.getValue() == 0) {
            ShaderPrograms.useDefaultShader(gl);

            double anim = Math.sin(tAnim);


            double x = anim * 5;
            double y = anim * 5;

            gl.glPushMatrix();

            gl.glTranslated(5, 5, 0);
            glut.glutSolidCube(1);


            gl.glPopMatrix();
        }
    }
}
