package de.unistuttgart.vis.dsass2020.ex05.p2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;
import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;
import de.unistuttgart.vis.dsass2020.ex05.p2.QuadTree;

public class SimpleQuadTree<T extends QuadTreeElement> extends QuadTree<T> {

  public SimpleQuadTree(final List<T> elements, final int maxElementsInLeaf)
      throws IllegalArgumentException {
    if (elements == null || maxElementsInLeaf < 1) {
      throw new IllegalArgumentException();
    } else {
      boundingBox = computeBoundingBox(elements);
      this.maxLeafElements = maxElementsInLeaf;
      createQuadTree(elements);
    }
  }

  private SimpleQuadTree(final List<T> elements, final int maxElementsInLeaf,
      final Rectangle boundingBox) throws IllegalArgumentException {
    this.boundingBox = boundingBox;
    this.maxLeafElements = maxElementsInLeaf;
    createQuadTree(elements);
  }

  private Rectangle computeBoundingBox(final List<T> elements) {
    // TODO Insert code for assignment 5.2.a
	  Point origin = elements.get(0).getPosition();//set the first point as origin of all rectangles
	  Point point;
	  Collection<Rectangle> rectangles = new ArrayList<>();
	  for(T element : elements) {
		 point = element.getPosition();
		 Rectangle r = new Rectangle(origin.x, origin.y,point.x - origin.x,point.y - origin.y);
		 rectangles.add(r);
	  }
	  return Rectangle.getBoundingBox(rectangles);

	
  }

  void createQuadTree(final List<T> list) throws IllegalArgumentException {
    // TODO Insert code for assignment 5.2.b
	//divide into 4 rectangles through the middle point
	Point middlePoint = new Point(boundingBox.x + boundingBox.width/2, boundingBox.y + boundingBox.height/2);
	Rectangle topleft = new Rectangle(middlePoint.x, middlePoint.y,
			-boundingBox.width/2, boundingBox.height/2); 
	Rectangle topright = new Rectangle(middlePoint.x, middlePoint.y,
			boundingBox.width/2, boundingBox.height/2);
	Rectangle bottomleft =new Rectangle(middlePoint.x, middlePoint.y,
			-boundingBox.width/2, -boundingBox.height/2);
	Rectangle bottomright = new Rectangle(middlePoint.x, middlePoint.y,
			boundingBox.width/2, -boundingBox.height/2);
	List<T> tlList = new ArrayList<>();
	List<T> trList = new ArrayList<>();
	List<T> blList = new ArrayList<>();
	List<T> brList = new ArrayList<>();
	
	for (T element : list) {
		if(topleft.contains(element.getPosition()))
			tlList.add(element);
		else if(topright.contains(element.getPosition()))
			trList.add(element);
		else if(bottomleft.contains(element.getPosition()))
			blList.add(element);
		else if(bottomright.contains(element.getPosition()))
			brList.add(element);
	}
	
	if(tlList.size() > maxLeafElements) {
		tl = new SimpleQuadTree<T>(tlList, maxLeafElements, topleft);
	}
	if(trList.size() > maxLeafElements) {
		tr = new SimpleQuadTree<T>(trList, maxLeafElements, topright);
	}
	if(blList.size() > maxLeafElements) {
		bl = new SimpleQuadTree<T>(blList, maxLeafElements, bottomleft);
	}
	if(brList.size() > maxLeafElements) {
		br = new SimpleQuadTree<T>(brList, maxLeafElements, bottomright);
	}
  }

  @Override
  public void rangeQuery(final List<T> resultList, final Rectangle query) {
    // TODO Insert code for assignment 5.2.c
	  if(!boundingBox.intersects(query))
		  return;
	  if(tl.boundingBox.intersects(query)) {
		  if(tl.leafElements.size() > maxLeafElements) {
			  tl.rangeQuery(resultList, query);
		  }else if(query.contains(tl.leafElements.get(0).getPosition()))
			resultList.add(tl.leafElements.get(0));
	  }
	  if(tr.boundingBox.intersects(query)) {
		  if(tr.leafElements.size() > maxLeafElements) {
			  tr.rangeQuery(resultList, query);
		  }else if(query.contains(tr.leafElements.get(0).getPosition()))
			resultList.add(tr.leafElements.get(0));
	  }
	  if(bl.boundingBox.intersects(query)) {
		  if(bl.leafElements.size() > maxLeafElements) {
			  bl.rangeQuery(resultList, query);
		  }else if(query.contains(bl.leafElements.get(0).getPosition()))
			resultList.add(bl.leafElements.get(0));
	  }
	  if(br.boundingBox.intersects(query)) {
		  if(br.leafElements.size() > maxLeafElements) {
			  br.rangeQuery(resultList, query);
		  }else if(query.contains(br.leafElements.get(0).getPosition()))
			resultList.add(br.leafElements.get(0));
	  }
	  
  }
}
