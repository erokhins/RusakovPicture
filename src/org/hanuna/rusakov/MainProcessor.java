package org.hanuna.rusakov;

import java.util.Collection;
import java.util.List;

/**
 * @author: erokhins
 */
public interface MainProcessor {

    void calculate(int count, float alpha, float beta, boolean fixAngle);

    //(-size, + size)
    Collection<Point> getGraph(int sizeGraph);

    List<Integer> getDiagram(int columnCount, int maxValue);

    public class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
