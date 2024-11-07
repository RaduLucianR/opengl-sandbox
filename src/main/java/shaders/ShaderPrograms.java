package shaders;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static nl.tue.s2iv60.core.app.GS.*;
import static sandbox.Sandbox.ComboBoxID.DAYNIGHT;
import static sandbox.Sandbox.CheckBoxID.HEADLIGHTON;
import nl.tue.s2iv60.core.util.ShaderProgram;
import nl.tue.s2iv60.core.util.TextureImg;

/**
 * 2IV60 - Computer Graphics
 * Date: 28/10/2020
 * @author Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
 */
/**
 * Here you can add Shaders to the project.
 *
 * To set up a shader and finally use it, four things have to be done:
 * <ol>
 * <li>Add a static field of type ShaderProgram, e.g.: <code>private static ShaderProgram myShader</code></li>
 * <li>Initialize this field in the method <code>setupShaders</code>; see example for <code>defaultShader</code>.</li>
 * <li>add a method <code>useMyShader</code>, see example code for <code>useDefaultShader</code>. 
 * Note that in the <code>useMyShader</code> method you can set uniforms that can be used in your shaders;
 * e.g. with the following statement you can pass on a boolean <code>isDay</code> to shader program:</li>
 * <ul><code>myShader.setUniform(gl, "isDay", DAYNIGHT.getValue()==0);</code></ul>
 * <li> Call the <code>useMyShader</code> method to start using the created shader.</li>
 * </ol>
 *For convenience SHADER_DIR and FS from the global state GS are used to specify 
 * the paths to the shaders. Note that FS gives the correct file separator for 
 * your operating system; not using it might get you into trouble.
 */
public class ShaderPrograms {
     /* To globally use every shader anywhere, add them like a static variable as done with defaultShader */
    private static ShaderProgram defaultShader;
    private static ShaderProgram phongShader;
    private static ShaderProgram terrainShader;
    private static ShaderProgram roadShader;

    public static void setupShaders(GL2 gl, GLU glu) {
        defaultShader = createShaderProgram(gl, glu, "default",
                "default_shader", "default_vertex.glsl", null, "default_fragment.glsl"
        );

        phongShader = createShaderProgram(gl, glu, "phong",
            "phongShader", "phongShader_vert.glsl", null, "phongShader_frag.glsl"
        );

        terrainShader = createShaderProgram(gl, glu, "terrain",
                "terrainShader", "terrainShader_vert.glsl", null, "terrainShader_frag.glsl"
        );

        roadShader = createShaderProgram(gl,glu,"road",
                "roadShader","roadShader_vert.glsl",null,"roadShader_frag.glsl"
        );
    }
    
    private static ShaderProgram createShaderProgram(
            GL2 gl, GLU glu,
            final String shaderName,
            final String shaderFolder, 
            final String vertexShader, 
            final String geometryShader,
            final String fragmentShader) {
        String folder = SHADER_DIR + shaderFolder + FS;
        return new ShaderProgram(shaderName, gl, glu,
                getFileStream(folder + vertexShader),
                getFileStream(folder + geometryShader), 
                getFileStream(folder + fragmentShader)
        );
    }

    private static String[] getFileStream(String shader) {
        try {
            return new String[] {new String(Files.readAllBytes(Paths.get(shader)))};
        } catch (IOException e) {
            return null;
        }
    }

    /** start using the default shader. Here you can also set your uniforms. */
    public static ShaderProgram useDefaultShader(GL2 gl) {
        defaultShader.useProgram(gl);
        return defaultShader;
    }

    public static ShaderProgram usePhongShader(GL2 gl) {
        ShaderProgram s=phongShader;
        s.useProgram(gl);
        s.setUniform(gl,"isDay",DAYNIGHT.getValue()==0);
        return phongShader;
    }

    public static ShaderProgram useTerrainShader(GL2 gl) {
        ShaderProgram t=terrainShader;
        t.useProgram(gl);
        t.setUniform(gl,"isDay",DAYNIGHT.getValue()==0);
        return terrainShader;
    }

    public static ShaderProgram useRoadShader(GL2 gl) {
        ShaderProgram t=roadShader;
        t.useProgram(gl);
        t.setUniform(gl,"isDay",DAYNIGHT.getValue()==0);
        //t.setUniform(gl,"desiredTexture",0);
        return roadShader;
    }

    public static ShaderProgram useCarrouselShader(GL2 gl) {
        ShaderProgram t=terrainShader;
        t.useProgram(gl);
        t.setUniform(gl,"isDay",DAYNIGHT.getValue()==0);
        return terrainShader;
    }
}