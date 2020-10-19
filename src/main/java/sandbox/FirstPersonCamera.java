package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Camera;
import nl.tue.s2iv60.core.app.GS;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Set;

class FirstPersonCamera extends Camera {
    public FirstPersonCamera() {
        this.eye = new Vector3f(0,0, 1.8f);       // eys at 1.8 meter
        this.center = new Vector3f(0, 10, 1.8f);
        this.up = new Vector3f(0, 0, 1);
    }
    
    @Override
    public void apply(GL2 gl, GLU glu) {
        String key = GS.keysPressed.toString();
        char[] keyArray = key.toCharArray();
        for (int i = 0; i < keyArray.length; i++) {
            float angle = 1f;
            Vector3f direction = new Vector3f(0);
            Vector3f centerCopy = center;
            Vector3f eyeCopy = eye;
            centerCopy.sub(eyeCopy,direction);

            Vector3f ground = new Vector3f();
            ground.x = eye.x;
            ground.y = eye.y;
            ground.z = 0.0f;
            Vector3f perp = new Vector3f(0);

            perp.x = eye.x - ground.x;
            perp.y = eye.y - ground.y;
            perp.z = eye.z - ground.z;

            if (keyArray[i] == 'q') {
                direction.normalize();
                direction.rotateAxis(angle,perp.x,perp.y,perp.z);
                center.add(direction);
            }
            if (keyArray[i] == 'e') {
                direction.normalize();
                direction.rotateAxis(angle,perp.x,perp.y,perp.z);
                center.sub(direction);
            }
            if (keyArray[i] == 'w') {
                direction.normalize();
                direction.mul(0.2f);
                eye.add(direction);
                center.add(direction);
            }
            if (keyArray[i] == 's') {
                direction.normalize();
                direction.mul(0.2f);
                eye.sub(direction);
                center.sub(direction);
            }
            if (keyArray[i] == 'a') {

            }
            if (keyArray[i] == 'd') {

            }
        }
        super.apply(gl, glu);
    }
}
