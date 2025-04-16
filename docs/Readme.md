# Robot Arm - Label

![Label](/docs/images/Label.svg)

### Angles

- $\Theta_0, \Theta_1, \Theta_2, \Theta_3, \Theta_4, \Theta_5$
- The default position, where every $\Theta_n$ has an angle of $0^\circ$, is shown in the bottom right of the visualization.
- Depending on the relative rotation perspective, the angles might need to be inverted: $\Theta_n \cdot (-1)$.

### Arm Segments

- $L_0, L_1, L_2, L_3, L_4, L_5$
- Length of each of the six arm segments.

### Arm Vectors

- $\vec{v_{L0}}, \vec{v_{L1}}, \vec{v_{L2}}, \vec{v_{L3}}, \vec{v_{L4}}, \vec{v_{L5}}$
- These vectors have the length of the corresponding arm segments and point in the arm direction, with the $\Theta_n$ rotation center as the origin of the vector.
- $\vec{v_{L2}}, \vec{v_{L3}}$ are collinear because they both rotate around their own/shared axis.

### Point and Direction

- $\vec{v_P}$: Destination point in 3D space where the tip should point.
- $\vec{v_D}$: Direction vector indicating where the arm should aim, pointing toward $\vec{v_P}$.

---

# Inverse Kinematics

## Table of Contents
- [vQ – Introduction](#--introduction)
- [Θ0 – Rotation](#--rotation)
- [2D Wrist Position](#---rotation--2d-wrist-position)
- [Θ3,Θ4,Θ5 – Tip Rotation](#--tip-rotation)
  - [Vector Angle Using a Normal Vector](#vector-angle--with)
  - [Θ3 – Rotation Around Horizontal Axis](#--rotation-1)
  - [Θ4 – Rotation Toward Direction Vector](#--rotation-2)
  - [Θ5 – Cross Axis Leveling](#--rotation-3)


## $\vec{v_Q}$ - Introduction

$$
\vec{v_Q} = \vec{v_P} - \vec{v_D} - 
\begin{pmatrix}
0\\
0\\
L_0 + L_1
\end{pmatrix}
$$  

$\vec{v_Q}$ is the direct vector from the rotation center of $\Theta_1$ to $\Theta_4$, always lying in a plane with a normal vector:

$$
\vec{n} = 
\begin{pmatrix}
x\\
y\\
0
\end{pmatrix}
$$  

This places a portion of the arm into two dimensions, which is later useful for calculating $\Theta_1, \Theta_2$.

## $\Theta_0$ - Rotation

The rotation angle $\Theta_0$ is derived from the projection of $\vec{v_Q}$ onto the x-y plane, forming a triangle to solve for $\Theta_0$.

![T0](/docs/images/T0.svg)

Using the atan2 function, the angle can be determined within a range of $0^\circ$ to $360^\circ$:

$$\Theta_0 = tan2^{-1}(v_{Qx}, v_{Qy})$$  

## $\Theta_1$, $\Theta_2$ - Rotation | 2D Wrist Position

With $\vec{v_Q}$ lying in a 2D plane, we can calculate $\Theta_1$ and $\Theta_2$ using triangle geometry:

![T1T2](/docs/images/T1T2.svg)

$$
\begin{cases}
(x \cdot |\vec{v_Q}|)^2 + h^2 = L_2^2 \\
((1 - x) \cdot |\vec{v_Q}|)^2 + h^2 = (L_3 + L_4)^2
\end{cases}
$$  

Solving for $x$:

$$
x = \frac{|\vec{v_Q}|^2 + L_2^2 - (L_3 + L_4)^2}{2 \cdot |\vec{v_Q}|^2}
$$  

Now we compute:

$$
h = \sqrt{L_2^2 - (x \cdot |\vec{v_Q}|)^2}
$$  

And the auxiliary angles:

$$
\alpha = \tan_2^{-1}(h, x \cdot |\vec{v_Q}|)
$$

$$
\beta = \tan_2^{-1}(\sqrt{v_{Qx}^2 + v_{Qy}^2}, v_{Qz})
$$

$$
\gamma = \tan_2^{-1}(h, (1 - x) \cdot |\vec{v_Q}|)
$$

Finally:

$$
\Theta_1 = \beta - \alpha
$$

$$
\Theta_2 = \alpha + \gamma
$$

### $\Theta_3, \Theta_4, \Theta_5$ - Tip Rotation

These angles define the orientation of the arm's end-effector. We use vector angles for computation.

#### Vector Angle $0^°$ – $360^°$ with $\vec{n}$

To distinguish between $45^\circ$ and $315^\circ$, we use a normal vector $\vec{n}$:

![vAn](/docs/images/vAn.svg)

Compute:

$$
\Theta = \cos^{-1} \left( \frac{\vec{a} \cdot \vec{b}}{|\vec{a}||\vec{b}|} \right)
$$

Use Rodrigues’ rotation formula to test for $\pm\Theta$:

$$
\vec{a}_{\pm \Theta} = \vec{a} \cdot \cos(\Theta) + (\vec{n} \times \vec{a}) \cdot \sin(\Theta) + \vec{n} \cdot (\vec{n} \cdot \vec{a}) \cdot (1 - \cos(\Theta))
$$

Then check:

$$
|\vec{b} - \vec{a_{+\Theta}}| \approx 0 \rightarrow +\Theta
$$
$$
|\vec{b} - \vec{a_{-\Theta}}| \approx 0 \rightarrow -\Theta
$$

We denote this process:

$$
\Theta = vAn(\vec{a}, \vec{b}, \vec{n})
$$

#### $\Theta_3$ - Rotation

$\Theta_3$ is the angle between the horizontal axis $\vec{v_{Hor}}$ and the axis defined by $\vec{v_D} \times \vec{v_{L3}}$:

![T3](/docs/images/T3.svg)

$$
\vec{v_{Hor}} = \text{x-axis rotated by } \Theta_0 \text{ around the z-axis}
$$

$$
\vec{v_{Axis}} = \vec{v_D} \times \vec{v_{L3}}
$$

Then:

$$
\Theta_3 = vAn(\vec{v_{Hor}}, \vec{v_{Axis}}, \vec{v_{L3}})
$$

#### $\Theta_4$ - Rotation

![T4](/docs/images/T4.svg)

Recompute $\vec{v_{Axis}}$ via rotation:

$$
\vec{v_{Axis}} = \text{rotation of } \vec{v_{Hor}} \text{ by } \Theta_3 \text{ around } \vec{v_{L3}}
$$

Then:

$$
\Theta_4 = vAn(\vec{v_{L3}}, \vec{v_D}, \vec{v_{Axis}})
$$

#### $\Theta_5$ - Rotation

This angle levels the cross axis of the tip:

![T5](/docs/images/T5.svg)

Intersect two planes:

$$
E_1: \begin{pmatrix} 0 \\ 0 \\ 1 \end{pmatrix} \cdot \vec{x} = 0, \quad
E_2: \vec{v_D} \cdot \vec{x} = 0
$$

Solve for $\vec{x}$:

$$
x_z = \vec{v_{Dx}} x_x + \vec{v_{Dy}} x_y + \vec{v_{Dz}} x_z\ \Leftrightarrow 0 = \vec{v_{Dx}} * x_x + \vec{v_{Dy}} * x_y
$$

Choose $x_y = 1$:

$$
\vec{x}=
\begin{pmatrix}
\frac{-v_{Dy}*x_y}{v_{Dx}}\\
x_y\\
0
\end{pmatrix}
\rightarrow
\begin{pmatrix}
\frac{-v_{Dy}}{v_{Dx}}\\
1\\
0
\end{pmatrix}
$$

Then:

$$
\vec{v_{\Theta_5}} = \vec{v_D} \times \vec{v_{Axis}} \\
\Theta_5 = vAn(\vec{x}, \vec{v_{\Theta_5}}, \vec{v_D})
$$

In special cases we have to change $\vec{x}$ manually:

$$
\vec{x}=
\begin{pmatrix}
\infty\\
1\\
0
\end{pmatrix}
\rightarrow
\vec{x}=
\begin{pmatrix}
1\\
0\\
0
\end{pmatrix}
$$

---

# Forward Kinematics

COMING SOON
