/*
 * Canvas.java				18/04/2019
 * Version: 1.0
 * Programmers: Electronics Department University of York, Y3843317
 * Company: University of York
 * 
 */

package drawing;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import geometry.CartesianCoordinate;
import geometry.LineSegment;

/**
 * <h2>Canvas</h2> This class represents a canvas object that can be drawn to
 * with various line segments, portals and wall objects.
 * 
 * <P>The list of LineSegments, Portals and Walls are stored in a collection within 
 * the implementation of the class. This collection is now synchronised to deal 
 * with the issue of concurrent accesses to the collection.
 * 
 * <P>Repaints have now been removed from all methods to reduce flicker that 
 * was occurring with the high number of calls to drawLineSegment. Everything
 * is now drawn at the end of a loop using Canvas.repaint()
 */
public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private int xSize, ySize;
	private List<LineSegment> lines;
	private List<Shape> portals;
	private List<Shape> walls; 
	private final static int DEFAULT_X = 800;
	private final static int DEFAULT_Y = 600;

	/**
	 * Default constructor which produces a canvas of the default size of 800 x
	 * 600.
	 */
	public Canvas() {
		this(DEFAULT_X, DEFAULT_Y);
	}

	/**
	 * Constructor which produces a canvas of a specified size.
	 * 
	 * @param x
	 *            Width of the canvas.
	 * @param y
	 *            Height of the canvas.
	 */
	public Canvas(int x, int y) {
		xSize = x;
		ySize = y;
		setupCanvas();
		lines = Collections.synchronizedList(new ArrayList<LineSegment>());
		portals = Collections.synchronizedList(new ArrayList<Shape>());
		walls = Collections.synchronizedList(new ArrayList<Shape>());
	}

	private void setupCanvas() {
		setSize(xSize, ySize);
		setVisible(true);
		repaint();
	}

	/**
	 * <b>NB: You never need to call this method yourself.</b> It handles the
	 * drawing but is called automatically each time a line segment is drawn.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smoother
																									// lines
		g2.setStroke(new BasicStroke(3));

		synchronized (lines) {
			for (LineSegment line : lines) {
				g2.draw(new Line2D.Double(line.getStartPoint().getX(), line.getStartPoint().getY(),
						line.getEndPoint().getX(), line.getEndPoint().getY()));
			}
		}
		
		synchronized (portals) {
			for (Shape shape : portals) {
				g2.draw(shape);
			}
		}
		
		synchronized (walls) {
			for (Shape shape : walls) {
				g2.fill(shape);
				g2.draw(shape);
			}
		}
		
	}
	
	/**
	 * 
	 * @param shape The shape to draw.
	 */
	public void addPortal(Shape shape) {
		synchronized (shape) {
			portals.add(shape);
		}
	}
	
	/**
	 * Clear the full list of Portals.
	 */
	public void removePortals() {
		synchronized (portals) {
			portals.clear();
		}
	}
	
	/**
	 * 
	 * @param shape The shape to draw.
	 */
	public void addWall(Shape shape) {
		synchronized (walls) {
			walls.add(shape);
		}
	}
	
	/**
	 * Clear the full list of Walls.
	 */
	public void removeWalls() {
		synchronized (walls) {
			walls.clear();
		}
	}

	/**
	 * Draws a line between two CartesianCoordinates to the canvas.
	 * 
	 * @param startPoint
	 *            Starting coordinate.
	 * @param endPoint
	 *            Ending coordinate.
	 */
	public void drawLineBetweenPoints(CartesianCoordinate startPoint, CartesianCoordinate endPoint) {
		synchronized (lines) {
			lines.add(new LineSegment(startPoint, endPoint));
		}
	}

	/**
	 * Draws a line segment to the canvas.
	 * 
	 * @param lineSegment
	 *            The LineSegment to draw.
	 */
	public void drawLineSegment(LineSegment lineSegment) {
		synchronized (lines) {
			lines.add(lineSegment);
		}
	}

	/**
	 * Draws multiple line segments to the canvas.
	 * 
	 * @param lineSegments
	 *            An array of LineSegment.
	 */
	public void drawLineSegments(LineSegment[] lineSegments) {
		for (LineSegment thisLineSegment : lineSegments) {
			synchronized (lines) {
				lines.add(thisLineSegment);
			}
		}
	}

	/**
	 * Removes the most recently added line from the drawing.
	 */
	public void removeMostRecentLine() {
		synchronized (lines) {
			lines.remove(lines.size() - 1);
		}
	}

	/**
	 * Clears the canvas of all drawing.
	 */
	public void clear() {
		synchronized (lines) {
			lines.clear();
			portals.clear();
			walls.clear();
		}
	}
	
}
