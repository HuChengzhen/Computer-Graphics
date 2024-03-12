package com.huchengzhen.model;

public class Light {
  private LightType type;

  private double intensity;

  private Vector3 position;

  public Light(LightType type, double intensity, Vector3 position) {
    this.type = type;
    this.intensity = intensity;
    this.position = position;
  }

  public LightType getType() {
    return type;
  }

  public void setType(LightType type) {
    this.type = type;
  }

  public double getIntensity() {
    return intensity;
  }

  public void setIntensity(double intensity) {
    this.intensity = intensity;
  }

  public Vector3 getPosition() {
    return position;
  }

  public void setPosition(Vector3 position) {
    this.position = position;
  }
}
