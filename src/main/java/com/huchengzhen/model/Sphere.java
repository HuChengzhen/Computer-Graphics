package com.huchengzhen.model;

import java.awt.*;
import java.util.Objects;

public class Sphere {
  private Vector3 center;
  private double radius;

  private Color color;

  private double specular;

  public Sphere(Vector3 center, double radius, Color color, double specular) {
    this.center = center;
    this.radius = radius;
    this.color = color;
    this.specular = specular;
  }

  public double getSpecular() {
    return specular;
  }

  public void setSpecular(double specular) {
    this.specular = specular;
  }

  public Vector3 getCenter() {
    return center;
  }

  public void setCenter(Vector3 center) {
    this.center = center;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sphere sphere = (Sphere) o;
    return Double.compare(radius, sphere.radius) == 0
        && Objects.equals(center, sphere.center)
        && Objects.equals(color, sphere.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(center, radius, color);
  }

  @Override
  public String toString() {
    return "Sphere{" + "center=" + center + ", radius=" + radius + ", color=" + color + '}';
  }
}
