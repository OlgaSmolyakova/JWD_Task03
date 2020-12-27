package by.ralovets.epamcourse;

import by.ralovets.epamcourse.xml.*;

import java.io.File;

public class App
{
    public static void main( String[] args ) throws XMLNodeReaderException {
        File file = new File("C:\\test.xml");
        XMLNodeReader reader = new XMLNodeFileReader(file);

        for (String s : reader) {
            System.out.println(s);
        }
    }
}
