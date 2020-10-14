package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTERRAIN;
import shaders.ShaderPrograms;

public class Terrain implements Renderable {
    private final double sizeX=50;  // meter
    private final double sizeY=50;    // meter
    private final double sizeZ=1;  // meter

    public Terrain() {
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (!SHOWTERRAIN.getValue()) return;
    }
}
