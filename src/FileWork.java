import sun.java2d.pipe.AAShapePipe;

import java.io.*;
import java.util.ArrayList;

public class FileWork {
    public String path;

    public FileWork(String _path) {
        path = _path;
    }

    public void creatWorld(ArrayList<Line> _startWorld) {
        String line;
        int row = 0;
        int index = 0;
        int value;
        String strval = "0";
        Line tmpL = new Line();
        try {
            //Type of life
            File file = new File(path);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)
                    )
            );

            try {
                while ((line = br.readLine()) != null) {
                    //Skip comments
                    if (!line.startsWith("#") && !line.startsWith("x")) {
                        int len = line.length();


                        int counter;
                        //Идем по строке
                        for (int j = 0; j < len; j++) {

                            //Read the number of sells by 1 element
                            if (Character.isDigit(line.charAt(j))) {
                                strval += line.charAt(j);
                            } else {

                                //Take the number of current type of sells
                                value = Integer.parseInt(strval);

                                //Если ничего не было считано -> кол-во клеток = 1
                                if (value == 0) {
                                    value = 1;
                                }

                                //Count fill index
                                counter = index + value;

                                //Note alive sells
                                if (line.charAt(j) == 'o') {
                                    for (int in = index; in < counter; in++) {
                                        tmpL.add(new Integer(in));
                                    }
                                }

                                //Shift the index the number of read values
                                index += value;


                                if (line.charAt(j) == '$' || line.charAt(j) == '!') {

                                    index = 0;
                                    tmpL.y = row;
                                    _startWorld.add(tmpL);
                                    tmpL = new Line();
                                    row++;

                                }

                                //Reset string to write a new value
                                strval = "0";

                            }
                        }
                    }

                }
                br.close();
            } catch (IOException e) {

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

