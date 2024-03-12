package com.huchengzhen;

import com.huchengzhen.model.Vector2;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingComponent extends Component {

  public void paint(Graphics g) {
    BufferedImage image =
        new BufferedImage(Main.frameWidth, Main.frameHeight, BufferedImage.TYPE_INT_RGB);
    for (var x = -Main.frameWidth / 2; x < Main.frameWidth / 2; x++) {
      for (var y = -Main.frameHeight / 2; y < Main.frameHeight / 2; y++) {
        Vector2 point = new Vector2(x, y);
        var direction = Utils.canvasToViewport(point);
        var color = Utils.traceRay(Main.CAMERA_POSITION, direction, 1, Double.POSITIVE_INFINITY);
        Utils.putPixel(point, color, image);
      }
    }
    g.drawImage(image, 0, 0, null);
  }
}
