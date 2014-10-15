/**
 * 
 */
package burgertime;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Class that stores the map layout, burger part locations, and character locations.
 *
 * @author havensid.
 *         Created Feb 5, 2014.
 */
public interface Environment {
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param guard
	 */
	void addGuard(Guard guard);
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param guard
	 */
	void removeChar(Guard guard);

	/**
	 * Checks whether the given point is within the horizontal extents of this
	 * world. Ignores the vertical location of the point.
	 * 
	 * @param point
	 * @return true iff the given point is within the horizontal extents of this
	 */
	boolean isInsideWorldX(Point2D point);

	/**
	 * Checks whether the given point is within the vertical extents of this
	 * world. Ignores the horizontal location of the point.
	 * 
	 * @param point
	 * @return true iff the given point is within the vertical extents of this
	 */
	boolean isInsideWorldY(Point2D point);

	/**
	 * Checks whether the given point is within the bounds of this world, both
	 * vertically and horizontally.
	 * 
	 * @param point
	 * @return true iff the given point is within this world
	 */
	boolean isInsideWorld(Point2D point);
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public List<Drawable> getDrawableGuards();
}
