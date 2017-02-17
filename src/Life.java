import java.util.*;
import java.awt.*;


public class Life {

    public static int min(int a, int b, int c) {
        int min;
        if (a > b) {
            min = b;
        } else {
            min = a;
        }
        if (min < c) {
            return min;
        } else {
            return c;
        }

    }

    public static int max(int a, int b, int c) {
        int max;
        if (a > b) {
            max = a;
        } else {
            max = b;
        }
        if (max > c) {
            return max;
        } else {
            return c;
        }

    }


    public static Line counter(Line top, Line middle, Line bottom) {
        Line result = new Line();
        int sizeT = top.list.size() - 1;
        int sizeM = middle.list.size() - 1;
        int sizeB = bottom.list.size() - 1;


        //Counting max index among three input lists
        int end = max((sizeT >= 0) ? top.list.get(sizeT) : -1,
                (sizeM >= 0) ? middle.list.get(sizeM) : -1,
                (sizeB >= 0) ? bottom.list.get(sizeB) : -1);


        //Counting min  index
        int start = min((sizeT >= 0) ? top.list.get(0) : end,
                (sizeM >= 0) ? middle.list.get(0) : end,
                (sizeB >= 0) ? bottom.list.get(0) : end);

        int closeX = 0;
        int count;

        //Main "IF" of life
        for (int i = start; i <= end + 1; i++) {
            count = top.countInLine(i) + middle.countInMid(i) + bottom.countInLine(i);
            if (count == 3) {
                result.add(i);
            }
            if (Collections.binarySearch(middle.list, i) >= 0 && count == 2) {
                result.add(i);
            }

        }
        result.y = middle.y;
        return result;
    }

    public static void main(String[] args) throws InterruptedException {

        double time = System.currentTimeMillis();

        ArrayList<Line> world = new ArrayList<Line>();

        //Change the size of canvas and max length
        //if switching between files
        int MAX_LENGTH_X = 100;
        int MAX_LENGTH_Y = 100;
        StdDraw.setCanvasSize(300, 300);
        FileWork file = new FileWork("123.txt");
        file.creatWorld(world);


        StdDraw.setXscale(0, (MAX_LENGTH_X - 1));
        StdDraw.setYscale((MAX_LENGTH_Y - 1), 0);

        double endtime = System.currentTimeMillis();
        System.out.println();
        System.out.print(endtime - time);


        StdDraw.setPenColor(Color.BLACK);

        StdDraw.show(0);

        Line top;
        Line middle;
        Line bottom;
        Line tmpL;

        while (true) {
            ArrayList<Line> newWorld = new ArrayList<Line>();

            StdDraw.clear();

            //Draw
            for (int i = 0; i < world.size(); i++) {
                int x;
                int size = world.get(i).list.size();
                tmpL = world.get(i);
                for (int j = 0; j < size; j++) {
                    x = tmpL.list.get(j);
                    StdDraw.filledRectangle(x, tmpL.y, 0.5, 0.5);

                }
            }

            StdDraw.show(0);
            time = System.currentTimeMillis();

            //Counting new generation
            int _y = world.get(0).y;

            top = new Line();
            middle = new Line(_y - 1);
            bottom = world.get(0);
            Line tmpLine = counter(top, middle, bottom);
            if (!tmpLine.list.isEmpty()) {
                newWorld.add(tmpLine);
            }

            top = new Line();
            middle = world.get(0);
            bottom = world.get(1);
            newWorld.add(counter(top, middle, bottom));

            for (int i = 1; i < world.size() - 1; i++) {
                top = world.get(i - 1);
                middle = world.get(i);
                bottom = world.get(i + 1);
                newWorld.add(counter(top, middle, bottom));
            }

            top = world.get(world.size() - 2);
            middle = world.get(world.size() - 1);
            _y = middle.y;
            bottom = new Line();
            newWorld.add(counter(top, middle, bottom));

            top = world.get(world.size() - 1);
            middle = new Line(_y + 1);
            bottom = new Line();
            newWorld.add(counter(top, middle, bottom));

            world.clear();
            world = newWorld;
            endtime = System.currentTimeMillis();
            System.out.println("time spent " + (endtime - time) );
        }
    }

}
