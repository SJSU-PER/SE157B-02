package jaxb.marshallers;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.book.BookRoot;
import jaxb.generated.book.BookType;
import jaxb.generated.book.ObjectFactory;

/**

 @author Team Cosmos Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for taking the Book object representation and calling
 the generateXMLDocument() method to create an XML representation of these book
 objects.
 */
public class BookJAXBMarshaller
{
   public void generateXMLDocument()
   {
      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.book");
         Marshaller marshaller = jaxbContext.createMarshaller();
         ObjectFactory factory = new ObjectFactory();

         BookRoot book = factory.createBookRoot();
         List<BookType> bookList = book.getBookInfo();

         BookType bookInfo = factory.createBookType();
         bookInfo.setBookId(1);
         bookInfo.setPubDate("2008");
         bookInfo.setTitle("Bag of Bones");
         bookInfo.setIsbnId(1);
         bookInfo.setPubId(1);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(2);
         bookInfo.setPubDate("1933");
         bookInfo.setTitle("Lost Horizon");
         bookInfo.setIsbnId(2);
         bookInfo.setPubId(1);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(3);
         bookInfo.setPubDate("1923");
         bookInfo.setTitle("Bambi: A Life in the Woods");
         bookInfo.setIsbnId(3);
         bookInfo.setPubId(1);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(4);
         bookInfo.setPubDate("2008");
         bookInfo.setTitle("Database Systems, the Complete Book");
         bookInfo.setIsbnId(4);
         bookInfo.setPubId(2);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(5);
         bookInfo.setPubDate("1998");
         bookInfo.setTitle("Algebra: Tools for a Changing World");
         bookInfo.setIsbnId(5);
         bookInfo.setPubId(3);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(6);
         bookInfo.setPubDate("2009");
         bookInfo.setTitle("Under the Dome");
         bookInfo.setIsbnId(6);
         bookInfo.setPubId(4);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(7);
         bookInfo.setPubDate("2002");
         bookInfo.setTitle("Murder at School");
         bookInfo.setIsbnId(7);
         bookInfo.setPubId(5);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(8);
         bookInfo.setPubDate("1977");
         bookInfo.setTitle("The Shining");
         bookInfo.setIsbnId(8);
         bookInfo.setPubId(4);
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(9);
         bookInfo.setPubDate("1975");
         bookInfo.setTitle("Salem's Lot");
         bookInfo.setIsbnId(9);
         bookInfo.setPubId(6);
         bookList.add(bookInfo);

         JAXBElement<BookRoot> bookElement =
                 factory.createBook(book);
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         marshaller.marshal(bookElement, System.out);
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    Main method for the BookJAXBMarshaller class. Creates a BookJAXBMarshaller
    object and calls the generateXMLDocument method on this newly created
    object.

    @param argv list of string arguments for this class.
    */
   public static void main(String[] argv)
   {
      BookJAXBMarshaller jaxbMarshaller = new BookJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
