import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line {
    public int y;
    public List<Integer> list = new ArrayList<Integer>();

    public Line() {
    }

    public Line(int y) {
        this.y = y;
    }

    public void add(Integer _int) {
        list.add(_int);
    }

    public int countInLine(int x) {
        //Search current index
        int posInLine = Collections.binarySearch(list, x);
        int counter = 0;
        int check = list.size() - 1;

        //Count right and central cells
        if (posInLine >= 0) {
            //Current index was found in list
            counter++;
            //Check the index in list range
            if (check >= posInLine + 1) {
                if (list.get(posInLine + 1) == x + 1) {
                    counter++;
                }
            }
        } else {
            //Convert index if it wa not found
            posInLine = -posInLine - 1;
            if (posInLine <= check) {
                if (list.get(posInLine) == x + 1) {
                    counter++;
                }
            }
        }

        //Count left sell
        if (posInLine > 0) {
            if (list.get(posInLine - 1) == x - 1) {
                counter++;
            }
        }

        return counter;

    }

    public int countInMid(int x) {
        //The same as countInLine
        //but don't count central cell
        int posInLine = Collections.binarySearch(list, x);
        int counter = 0;
        int check = list.size() - 1;
        if (posInLine >= 0) {
            if (check >= posInLine + 1) {
                if (list.get(posInLine + 1) == x + 1) {
                    counter++;
                }
            }
        } else {
            posInLine = -posInLine - 1;
            if (posInLine <= check) {
                if (list.get(posInLine) == x + 1) {
                    counter++;
                }
            }
        }
        if (posInLine > 0) {
            if (list.get(posInLine - 1) == x - 1) {
                counter++;
            }
        }

        return counter;

    }
}
