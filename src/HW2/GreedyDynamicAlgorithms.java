package HW2;

import java.util.*;

public class GreedyDynamicAlgorithms {

    /**
     * Goal: find the smallest number of red intervals to select, such that every
     * blue interval overlaps with at least one of the selected red intervals.
     * Output this number
     * 
     * @param red
     *            - the list of red intervals
     * @param blue
     *            - the list blue intervals
     * @return
     */
    public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
	Interval.sortByStartTime(blue);
	Interval.sortByFinishTime(red);

	int i = 0;
	int j = 0;
	int count = 0;

	while (i < blue.size() && j < red.size()) {
	    // do not consider the red[j] if it is not intersecting with blue[i]
	    if (!isIntersected(red.get(j), blue.get(i))) {
		j++;
		continue;
	    }

	    // find the latest red[j] interval that overlaps blue[i]
	    // NOTE: the last index that intersect blue[i] is [j-1]
	    while (j < red.size() && isIntersected(red.get(j), blue.get(i))) {
		j++;
	    }

	    count++;
	    // remove all the blue interval intersecting the red[j-1] interval
	    while (isIntersected(red.get(j - 1), blue.get(i))) {
		i++;
		if (i == blue.size()) {
		    return count;
		}
	    }
	}

	if (i < blue.size())
	    return -1;
	return count;
    }

    // helper method
    /**
     * Goal: return true if two numerical interval are intersecting
     * 
     * @param interval
     * @param interval2
     * @return
     */
    private static boolean isIntersected(Interval interval, Interval interval2) {
	// TODO Auto-generated method stub
	if (interval.start < interval2.start) {
	    return interval.finish >= interval2.start;
	} else {
	    return interval.start <= interval2.finish;
	}
    }

    /**
     * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
     * to the bottom right of the grid (grid[m-1][n-1]). Output this sequence of
     * directions
     * 
     * @param grid
     *            - the 2d grid containing the cost of each location in the grid.
     * @return
     */
    public static List<Direction> optimalGridPath(int[][] grid) {
	int rows = grid.length;
	int cols = grid[0].length;
	int maxRowIndex = rows - 1;
	int maxColIndex = cols - 1;
	int[][] costGrid = new int[rows][cols];
	LinkedList<Direction> optimalPathList = new LinkedList<Direction>();

	// build a temporary cost grid: in each cell I will save the total cost
	// necessary for reach that cell
	costGrid[0][0] = grid[0][0];
	for (int col = 1; col <= cols - 1; ++col) {
	    costGrid[0][col] = costGrid[0][col - 1] + grid[0][col];
	}

	for (int row = 1; row <= rows - 1; ++row) {
	    costGrid[row][0] = costGrid[row - 1][0] + grid[row][0];
	}

	for (int row = 1; row <= rows - 1; ++row) {
	    for (int col = 1; col <= cols - 1; ++col) {
		if (costGrid[row][col - 1] < costGrid[row - 1][col]) {
		    costGrid[row][col] = costGrid[row][col - 1] + grid[row][col];
		} else {
		    costGrid[row][col] = costGrid[row - 1][col] + grid[row][col];
		}
	    }
	}

	int i = maxRowIndex;
	int j = maxColIndex;
	while (!(i == 0 && j == 0)) {
	    // only the left cell is available
	    if (i == 0) {
		j--;
		optimalPathList.addFirst(Direction.RIGHT);
	    } else if (j == 0) {// only the upper cell is available
		i--;
		optimalPathList.addFirst(Direction.DOWN);
	    } else { // typical condition
		if (costGrid[i][j - 1] < costGrid[i - 1][j]) {
		    j--;
		    optimalPathList.addFirst(Direction.RIGHT);
		} else {
		    i--;
		    optimalPathList.addFirst(Direction.DOWN);
		}
	    }
	}

	return optimalPathList;
    }

    /**
     * A simple Direction enum directions can be either DOWN or RIGHT You will
     * output a list of these in the grid-path problem
     */
    public static enum Direction {
	DOWN, RIGHT
    }

    /**
     * A private Interval class to help with the interval question
     */
    public static class Interval {

	int start;
	int finish;

	public Interval(int start, int finish) {
	    this.start = start;
	    this.finish = finish;
	}

	/**
	 * sorts a list of intervals by start time, you are free to use this on the
	 * first question
	 */
	public static void sortByStartTime(ArrayList<Interval> l) {
	    Collections.sort(l, new Comparator<Interval>() {
		public int compare(Interval o1, Interval o2) {
		    Interval i1 = (Interval) o1;
		    Interval i2 = (Interval) o2;
		    return i1.start - i2.start;
		}
	    });
	}

	/**
	 * sorts a list of intervals by finish time, you are free to use this on the
	 * first question
	 */
	public static void sortByFinishTime(ArrayList<Interval> l) {
	    Collections.sort(l, new Comparator<Interval>() {
		public int compare(Interval o1, Interval o2) {
		    Interval i1 = (Interval) o1;
		    Interval i2 = (Interval) o2;
		    return i1.finish - i2.finish;
		}
	    });
	}
    }

}
