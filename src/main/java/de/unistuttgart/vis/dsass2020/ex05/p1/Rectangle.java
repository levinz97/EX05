package de.unistuttgart.vis.dsass2020.ex05.p1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a quadrilateral, where the angles are 90 degrees.
 */
public class Rectangle {

  // Upper left corner of the rectangle
  public final float x;
  public final float y;

  public final float width;
  public final float height;

  /**
   * Generate a new rectangle
   * 
   * @param x      left position of the rectangle
   * @param y      upper position of the rectangle
   * @param width
   * @param heigth
   */
  public Rectangle(float x, float y, float width, float height) {
	// enforce positive width
	if (width >= 0) {
    	this.x = x;
    	this.width = width;
	} else {
    	this.x = x + width;
    	this.width = -width;
	}
	// enforce positive height
    if (height >= 0) {
    	this.y = y;
    	this.height = height;
    } else {
    	this.y = y + height;
    	this.height = -height;
    }
  }

  /**
   * @param point
   * @return true iff the point is inside the rectangle
   */
  public boolean contains(final Point point) {
    // TODO Insert code for assignment 5.1.a
	  final float threshold = (float) 0.0000001;
	  if(point.x > x && point.x < x + width && point.y > y && point.y < y + height 
			  || Math.abs(point.x - this.x) < threshold && Math.abs(point.y - this.y) < threshold 
			  ||Math.abs(this.x + width - point.x) < threshold 
			  && Math.abs(this.y + height - point.y) < threshold) {
		  return true;		  
	  }
	  return false;
  }

  /**
   * @param rectangle
   * @return true iff the rectangle intersects this rectangle
   */
  public boolean intersects(final Rectangle rectangle) {
    // TODO Insert code for assignment 5.1.b
	  if(rectangle.contains(new Point(this.x, this.y)) ||
			  rectangle.contains(new Point(this.x + width, this.y +height)))
		  return true;
	  else
		  return false;
  }

  /**
   * Compute the bounding rectangle for a set of rectangles.
   * 
   * @param rectangles
   * @return the bounding rectangle
   */
  public static Rectangle getBoundingBox(final Collection<Rectangle> rectangles) {
    float minX = Float.MAX_VALUE;
    float maxX = -Float.MAX_VALUE;
    float minY = Float.MAX_VALUE;
    float maxY = -Float.MAX_VALUE;
    for (Rectangle rectangle : rectangles) {
      if (rectangle.x < minX) {
        minX = rectangle.x;
      }
      if (rectangle.x + rectangle.width > maxX) {
        maxX = rectangle.x + rectangle.width;
      }
      if (rectangle.y < minY) {
        minY = rectangle.y;
      }
      if (rectangle.y + rectangle.height > maxY) {
        maxY = rectangle.y + rectangle.height;
      }
    }
    return new Rectangle(minX, minY, maxX - minX, maxY - minY);
  }

  /**
   * Extends the rectangle to cover a set of points.
   * 
   * @param points
   * @return the grown rectangle that includes all {@link points}.
   */
  public Rectangle extendTo(final Collection<Point> points) {
    // TODO Insert code for assignment 5.1.c
	Collection<Rectangle> rectangles = new ArrayList<>();
	for(Point point : points) {
		rectangles.add(new Rectangle(this.x,this.y,point.x - this.x,point.y - this.y));
	}
	return Rectangle.getBoundingBox(rectangles);
	
  }
  
}
