//Authors: Teodor Lungu and Radu Lucian Radulescu (1416332 & 1438808)

void main()
{
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;      // model view transform
    gl_FrontColor = gl_Color;
}