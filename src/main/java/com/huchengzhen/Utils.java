package com.huchengzhen;

import com.huchengzhen.model.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {
  public static Vector3 canvasToViewport(Vector2 point) {
    return new Vector3(
        point.getX() / Main.jFrame.getWidth(), point.getY() / Main.jFrame.getHeight(), 1);
  }

  public static double[] intersectRaySphere(Vector3 origin, Vector3 direction, Sphere sphere) {
    var oc = Vector3.subtract(origin, sphere.getCenter());
    var k1 = Vector3.dotProduct(direction, direction);
    var k2 = 2 * Vector3.dotProduct(oc, direction);
    var k3 = Vector3.dotProduct(oc, oc) - sphere.getRadius() * sphere.getRadius();

    var discriminant = k2 * k2 - 4 * k1 * k3;
    if (discriminant < 0) {
      return new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
    }

    var t1 = (-k2 + Math.sqrt(discriminant)) / (2 * k1);
    var t2 = (-k2 - Math.sqrt(discriminant)) / (2 * k1);

    return new double[] {t1, t2};
  }

  public static Color traceRay(Vector3 origin, Vector3 direction, double minT, double maxT) {
    var closestT = Double.POSITIVE_INFINITY;
    Sphere closestSphere = null;

    for (Sphere sphere : Main.spheres) {
      double[] ts = intersectRaySphere(origin, direction, sphere);
      if (ts[0] < closestT && minT < ts[0] && ts[0] < maxT) {
        closestT = ts[0];
        closestSphere = sphere;
      }
      if (ts[1] < closestT && minT < ts[1] && ts[1] < maxT) {
        closestT = ts[1];
        closestSphere = sphere;
      }
    }

    if (closestSphere == null) {
      return Main.BACKGROUND_COLOR;
    }

    Vector3 point = Vector3.add(origin, Vector3.multiply(closestT, direction));
    Vector3 normal = Vector3.subtract(point, closestSphere.getCenter());
    normal = Vector3.multiply(1.0 / Vector3.length(normal), normal);
    Vector3 view = Vector3.multiply(-1, direction);
    Vector3 colorv =
        Vector3.clamp(
            Vector3.multiply(
                computeLighting(point, normal, view, closestSphere.getSpecular()),
                Vector3.fromColor(closestSphere.getColor())));
    return new Color((int) colorv.getX(), (int) colorv.getY(), (int) colorv.getZ());
  }

  public static void putPixel(Vector2 point, Color color, BufferedImage image) {
    int x = Main.frameWidth / 2 + (int) Math.round(point.getX());
    int y = Main.frameHeight / 2 - (int) Math.round(point.getY()) - 1;
    image.setRGB(x, y, color.getRGB());
  }

  public static double computeLighting(
      Vector3 point, Vector3 normal, Vector3 view, double specular) {
    double intensity = 0;
    double length_n = Vector3.length(normal);
    for (Light light : Main.lights) {
      if (light.getType() == LightType.AMBIENT) {
        intensity += light.getIntensity();
      } else {
        Vector3 vec_l;
        if (light.getType() == LightType.POINT) {
          vec_l = Vector3.subtract(light.getPosition(), point);
        } else {
          vec_l = light.getPosition();
        }
        double n_dot_l = Vector3.dotProduct(normal, vec_l);
        if (n_dot_l > 0) {
          intensity += light.getIntensity() * n_dot_l / (length_n * Vector3.length(vec_l));
        }

        if (specular != -1) {
          Vector3 r =
              Vector3.subtract(
                  Vector3.multiply(2.0 * Vector3.dotProduct(normal, vec_l), normal), vec_l);
          double dotV = Vector3.dotProduct(r, view);
          if (dotV > 0) {
            intensity +=
                light.getIntensity()
                    * Math.pow(dotV / Vector3.length(r) / Vector3.length(view), specular);
          }
        }
      }
    }
    return intensity;
  }
}
