package JAXB.marshallers;

import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.book_author.BookAuthorRoot;
import jaxb.generated.book_author.BookAuthorType;
import jaxb.generated.book_author.ObjectFactory;


// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
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

   public static void main(String[] argv)
   {
      BookAuthorJAXBMarshaller jaxbMarshaller = new BookAuthorJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
