//Authors: Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)
varying vec3 N;
varying vec3 v;
uniform int isDay;
uniform int headLightOn;
void main (void)
{
        vec3 L = normalize(gl_LightSource[0].position.xyz - v);
        vec3 E = normalize(-v);// we are in Eye Coordinates, so EyePos is (0,0,0)
        vec3 R = normalize(-reflect(L, N));

        //calculate Ambient Term:
        vec4 Iambient = gl_FrontLightProduct[0].ambient;

        //calculate Diffuse Term:
        vec4 Idiffuse = gl_FrontLightProduct[0].diffuse * max(dot(N, L), 0.0);
        Idiffuse = clamp(Idiffuse, 0.0, 1.0);

        // calculate Specular Term:
        vec4 Ispecular = gl_FrontLightProduct[0].specular
        * pow(max(dot(R, E), 0.0), 0.3*gl_FrontMaterial.shininess);
        Ispecular = clamp(Ispecular, 0.0, 1.0);
        // write Total Color:
        gl_FragColor =gl_Color * (Iambient + Idiffuse + Ispecular);
        if (isDay == 1){
            gl_FragColor=gl_FragColor*1.5;
        }
        else{
            gl_FragColor=gl_FragColor*0.5;
        }
}
