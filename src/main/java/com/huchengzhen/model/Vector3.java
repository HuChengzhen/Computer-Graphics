package com.huchengzhen.model;

import java.awt.*;
import java.util.Objects;

public class Vector3 {
  private double x;
  private double y;
  private double z;

  public Vector3(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public static Vector3 fromColor(Color color) {
    return new Vector3(color.getRed(), color.getGreen(), color.getBlue());
  }

  public static double dotProduct(Vector3 p1, Vector3 p2) {
    return p1.x * p2.x + p1.y * p2.y + p1.z * p2.z;
  }

  public static Vector3 subtract(Vector3 p1, Vector3 p2) {
    return new Vector3(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z);
  }

  public static Vector3 add(Vector3 v1, Vector3 v2) {
    return new Vector3(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
  }

  public static double length(Vector3 vec) {
    return Math.sqrt(dotProduct(vec, vec));
  }

  public static Vector3 clamp(Vector3 vector3) {
    return new Vector3(clamp(vector3.x), clamp(vector3.y), clamp(vector3.z));
  }

  public static double clamp(double v) {
    return Math.min(255, Math.max(0, v));
  }

  public static Vector3 multiply(double k, Vector3 v) {
    return new Vector3(k * v.getX(), k * v.getY(), k * v.getZ());
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  @Override
  public String toString() {
    return "Vector3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vector3 vector3 = (Vector3) o;
    return Double.compare(x, vector3.x) == 0
        && Double.compare(y, vector3.y) == 0
        && Double.compare(z, vector3.z) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }
}
