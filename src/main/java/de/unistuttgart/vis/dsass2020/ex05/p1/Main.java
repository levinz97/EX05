package de.unistuttgart.vis.dsass2020.ex05.p1;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(2,2);
		Rectangle r1 = new Rectangle(0,0,2,2);
		System.out.println(r1.contains(p1));
		
//		Point p2 = new Point(2,3);
//		Point p3 = new Point(2,3);
//		System.out.print(p2);
		
//		System.out.print(new Rectangle(1,1,2,2).intersects(r1));
		Collection<Point> points = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			points.add(new Point(i,i));
		}
		
		System.out.println(r1.extendTo(points).height);
	}

}
