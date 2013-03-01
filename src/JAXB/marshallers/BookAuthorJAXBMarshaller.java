package JAXB.marshallers;

import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.book_author.BookAuthorRoot;
import jaxb.generated.book_author.BookAuthorType;
import jaxb.generated.book_author.ObjectFactory;

/**
 @author Team Cosmos Erni Ali, Randy Zaatri, Philip Vaca

 Solution for CS157B Project #1 The BookAuthorJAXBMarshaller class is
 responsible for taking the hibernate generated BookAuthor objects
 representation and generating an XML document representation by calling the
 generateXMLDocument() method.
 */
public class BookAuthorJAXBMarshaller
{
   public void generateXMLDocument()
   {
      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.book_author");
         Marshaller marshaller = jaxbContext.createMarshaller();
         ObjectFactory factory = new ObjectFactory();

         BookAuthorRoot bookAuthor = factory.createBookAuthorRoot();
         List<BookAuthorType> bookAuthorList = bookAuthor.getBookAuthorInfo();

         BookAuthorType bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(1);
         bookAuthorInfo.setBookId(1);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(2);
         bookAuthorInfo.setBookId(2);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(3);
         bookAuthorInfo.setBookId(3);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(6);
         bookAuthorInfo.setBookId(4);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(4);
         bookAuthorInfo.setBookId(4);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(5);
         bookAuthorInfo.setBookId(4);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(7);
         bookAuthorInfo.setBookId(5);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(1);
         bookAuthorInfo.setBookId(6);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(2);
         bookAuthorInfo.setBookId(7);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(1);
         bookAuthorInfo.setBookId(8);
         bookAuthorList.add(bookAuthorInfo);

         bookAuthorInfo = factory.createBookAuthorType();
         bookAuthorInfo.setAuthorId(1);
         bookAuthorInfo.setBookId(9);
         bookAuthorList.add(bookAuthorInfo);

         JAXBElement<BookAuthorRoot> bookElement =
                 factory.createBookAuthor(bookAuthor);
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         marshaller.marshal(bookElement, System.out);
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    Main method for the BookAuthorJAXBMarshaller class. Creates a new
    BookAuthorJAXBMarshaller and then calls the generateXMLDocument method on
    the newly created BookAuthorJAXBMarshaller.

    @param argv list of string arguments
    */
   public static void main(String[] argv)
   {
      BookAuthorJAXBMarshaller jaxbMarshaller = new BookAuthorJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
