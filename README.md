# Computer Graphics basics with Java OpenGL
Amusement park scene created from scratch with JOGL, the Java binding of OpenGL. This project was done in October 2020 for the 2IV60 Computer Graphics course of TU Eindhoven.

## Demo
Overview of the created scene, including: carousel, train, path, trees, light poles, stall:
![alt text](allview.png)

The control points of the Bezier curve highlighted:
![alt text](cpcarousel.png) 

First person camera:
![alt text](cpcarousel.png) 

## Features
The scene includes:
- Custom made 3D models directly in OpenGL
- Texture mapped on a path
- Phong shader for lightning
- Bezier curve to create the path, implemented with De Casteljau's algorithm
- Basic animation of the carousel and of the train on the path
- First-person camera
- Third-person camera
- Checkbox to enable/disable features
- Sliders to control size of the carousel and its speed of rotation

## How to run
1. Install Java (if not already installed); at least JDK 11 is required.
2. Clone the repository
3. Compile and then run the `Sandbox` file from the `sandbox` package.