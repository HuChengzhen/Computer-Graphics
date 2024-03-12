package com.huchengzhen;

import com.huchengzhen.model.Light;
import com.huchengzhen.model.LightType;
import com.huchengzhen.model.Sphere;
import com.huchengzhen.model.Vector3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static JFrame jFrame = new javax.swing.JFrame();
  public static int frameWidth = 600;
  public static int frameHeight = 600;
  public static Color BACKGROUND_COLOR = new Color(255, 255, 255);
  //  public static Sphere[] spheres =
  //      new Sphere[] {
  //        new Sphere(new Vector3(0, -1, 3), 1, new Color(255, 0, 0)),
  //        new Sphere(new Vector3(2, 0, 4), 1, new Color(0, 0, 255)),
  //        new Sphere(new Vector3(-2, 0, 4), 1, new Color(0, 255, 0))
  //      };

  public static Sphere[] spheres =
      new Sphere[] {
        new Sphere(new Vector3(0, -1, 3), 1, new Color(255, 0, 0)),
        new Sphere(new Vector3(2, 0, 4), 1, new Color(0, 0, 255)),
        new Sphere(new Vector3(-2, 0, 4), 1, new Color(0, 255, 0)),
        new Sphere(new Vector3(0, -5001, 0), 5000, new Color(255, 255, 0))
      };
  public static Light[] lights =
      new Light[] {
        new Light(LightType.AMBIENT, 0.2, null),
        new Light(LightType.POINT, 0.6, new Vector3(2, 1, 0)),
        new Light(LightType.DIRECTIONAL, 0.2, new Vector3(1, 4, 4))
      };

  public static Vector3 CAMERA_POSITION = new Vector3(0, 0, 0);

  public static void main(String[] args) {

    jFrame.setSize(frameWidth, frameHeight);

    jFrame.getContentPane().add(new DrawingComponent());
    jFrame.setVisible(true);

    Timer timer =
        new Timer(
            20,
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                CAMERA_POSITION =
                    new Vector3(
                        CAMERA_POSITION.getX() - 0.005,
                        CAMERA_POSITION.getY() - 0.005,
                        CAMERA_POSITION.getZ() - 0.01);
                Vector3 position = lights[1].getPosition();
                lights[1].getPosition().setX(position.getX() + 0.05);

                jFrame.repaint();
              }
            });
    timer.setRepeats(true);
    timer.start();
  }
}
