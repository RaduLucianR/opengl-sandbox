package sandbox;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import nl.tue.s2iv60.core.cg.Renderer;
import objects.*;
import objects.lights.LightPole;
import objects.lights.StreetLights;
import org.joml.Vector3f;
import static sandbox.Sandbox.ComboBoxID.VIEWMODE;
import static sandbox.Sandbox.CheckBoxID.SHOWSTALL;
import shaders.ShaderPrograms;

/**
 * The main.java.SandboxRenderer extends the Renderer base class.
 * The Renderer superclass takes care of most OpenGL calls, but you can always edit behaviour using your own calls.
 *              (e.g. enabling/disabling back face culling)
 *
 * The Renderer class holds the camera which is initialized before any objects are created.
 *
 */
public class SandboxRenderer extends Renderer {
    private int currentViewMode = -1; // no known view mode
    
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        super.init(glAutoDrawable);

        /* Initialize the camera's position */
        camera = null;

        /* Load all the shaders */
        ShaderPrograms.setupShaders(gl, glu);
    }

    /**
     * Here you add all the objects to the scene that will be rendered.
     * All objects need to implement the interface Renderable.
     */
    @Override
    public void initObjects() {
        gl.glDisable(GL.GL_CULL_FACE);
        //objects.add(new Cube());


        Vector3f pos = new Vector3f();
        pos.x = 0;
        pos.y = 0;
        pos.z = 0;
        float size = 4.0f;
        objects.add(new Axis(pos,size/4));
        objects.add(new Terrain());
        objects.add(new Stall(pos));
        //objects.add(new LightPole(new Vector3f(8,8,0),1,100,1));
        objects.add(new Train(pos));

        objects.add(new Road(pos));
        size = 1;
        objects.add(new Carousel(pos,size));
        objects.add(new StreetLights(new Road(pos)));

        Vector3f tree1 = new Vector3f();
        tree1.x = -10;
        tree1.y = -10;
        tree1.z = 0;
        objects.add(new Trees(tree1,size*1.2f));
        Vector3f tree2 = new Vector3f();
        tree2.x = 15;
        tree2.y = 15;
        tree2.z = 0;
        objects.add(new Trees(tree2,size));
        Vector3f tree3 = new Vector3f();
        tree3.x = -7.5f;
        tree3.y = 7.5f;
        tree3.z = 0;
        objects.add(new Trees(tree3,size*1.3f));
        Vector3f tree4 = new Vector3f();
        tree4.x = -12;
        tree4.y = 19.5f;
        tree4.z = 0;
        objects.add(new Trees(tree4,size*1.15f));
        Vector3f tree5 = new Vector3f();
        tree5.x = 18;
        tree5.y = -13;
        tree5.z = 0;
        objects.add(new Trees(tree5,size));
            objects.add(new Axis(new Vector3f(10.7f,9.5f,1.1f),0.5f));
            objects.add(new TrainNoMovement(new Vector3f(9.1f, 10, 1), 0.1f));
            objects.add(new CarouselStatic(new Vector3f(100, 100, 10), 0.1f));
            objects.add(new Trees(new Vector3f(10.7f, 10.5f, 1), 0.1f));
            objects.add(new LightPole(new Vector3f(9.1f, 10.5f, 1.0f), 0.1f, 1,1));


    }
    
    /**
     * This is the main render method which calls the display method of Renderer
     * for passing camera parameters to opengl and rendering the objects array.
     * Here you can do additional things that have to be done each frame, e.g. 
     * selection of the camera.
     * @param glAutoDrawable
     */
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {        
        // select new camera if view mode changed
        if (currentViewMode != VIEWMODE.getValue()) { 
            // camera parameters
            boolean reset = camera==null || currentViewMode==-1;
            switch (VIEWMODE.getValue()) {
                case 0: camera = new FixedCamera();  break;
                case 1: 
                    // pickup current camera parameters and pass them on.
                    Vector3f eye = reset? null: camera.getEye();
                    Vector3f cnt = reset? null: camera.getCenter();
                    Vector3f up  = reset? null: camera.getUp();
                    camera = new FlyingCamera(eye,cnt,up); break;
                case 2: camera = new FirstPersonCamera(new Vector3f(10,8,1.8f)); break;
                default:
                    System.err.println(VIEWMODE.getValue());
            }
            currentViewMode = VIEWMODE.getValue();
        }

        
        // pass camera parameters to opengl,
        // render all objects in the objects array, ...
        super.display(glAutoDrawable);
    }
    
    @Override
    public void resetView() {
        currentViewMode=-1;   // resets the parameters of the current camera
    }
}
