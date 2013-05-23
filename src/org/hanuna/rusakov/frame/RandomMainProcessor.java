package org.hanuna.rusakov.frame;

import org.hanuna.rusakov.MainProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author: erokhins
 */
public class RandomMainProcessor implements MainProcessor {
    private int count = 0;
    private Random random = new Random();

    @Override
    public void calculate(int count, float alpha, float beta, boolean fixAngle) {
        this.count = count;
    }

    private Point getRandomPoint(int sizeGraph) {
        int x = random.nextInt(2 * sizeGraph - 1) - sizeGraph + 1;
        int y = random.nextInt(2 * sizeGraph - 1) - sizeGraph + 1;
        return new Point(x, y);
    }

    @Override
    public Collection<Point> getGraph(int sizeGraph) {
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < count; i++) {
            points.add(getRandomPoint(sizeGraph));
        }
        return points;
    }

    @Override
    public List<Integer> getDiagram(int columnCount, int maxValue) {
        List<Integer> resultList = new ArrayList<Integer>(columnCount);
        for (int i = 0; i < columnCount; i++) {
            resultList.add(random.nextInt(maxValue));
        }
        return resultList;
    }
}
