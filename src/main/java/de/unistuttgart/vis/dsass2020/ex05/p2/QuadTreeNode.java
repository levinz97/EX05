package de.unistuttgart.vis.dsass2020.ex05.p2;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;

public class QuadTreeNode implements QuadTreeElement {
	Point point;
	public QuadTreeNode(Point point) {
		this.point = point;
	}
	public Point getPosition() {
		return this.point;
	}
	
}
