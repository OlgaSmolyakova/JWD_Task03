package by.ralovets.epamcourse;

import by.ralovets.epamcourse.xml.XMLNodeFileReader;
import by.ralovets.epamcourse.xml.XMLNodeReader;
import by.ralovets.epamcourse.xml.XMLNodeReaderException;

import java.io.File;

public class App
{
    public static void main( String[] args ) throws XMLNodeReaderException {
        File file = new File("C:\\test.xml");
        XMLNodeFileReader reader = new XMLNodeFileReader(file);

        while (reader.hasNext()) {
            System.out.println(reader.next());
        }
    }
}
