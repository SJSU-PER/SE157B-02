package JAXB.marshallers;

import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.book_genre.BookGenreRoot;
import jaxb.generated.book_genre.BookGenreType;
import jaxb.generated.book_genre.ObjectFactory;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
/**

 @author Team Cosmo Erni Ali, Randy Zaatri, Philip Vaca

 The Book GenreJAXBMarshaller class is responsible for taking the BookGenre
 object representation and calling the generateXMLDocument() method to generate
 an XML representation of these objects.
 */
public class BookGenreJAXBMarshaller
{
   public void generateXMLDocument()
   {
      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.book_genre");
         Marshaller marshaller = jaxbContext.createMarshaller();
         ObjectFactory factory = new ObjectFactory();

         BookGenreRoot bookGenre = factory.createBookGenreRoot();
         List<BookGenreType> bookGenreList = bookGenre.getBookGenreInfo();

         BookGenreType bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(1);
         bookGenreInfo.setBookId(1);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(2);
         bookGenreInfo.setBookId(1);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(6);
         bookGenreInfo.setBookId(1);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(8);
         bookGenreInfo.setBookId(2);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(7);
         bookGenreInfo.setBookId(3);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(8);
         bookGenreInfo.setBookId(3);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(5);
         bookGenreInfo.setBookId(4);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(4);
         bookGenreInfo.setBookId(5);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(3);
         bookGenreInfo.setBookId(5);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(2);
         bookGenreInfo.setBookId(6);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(6);
         bookGenreInfo.setBookId(6);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(2);
         bookGenreInfo.setBookId(7);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(1);
         bookGenreInfo.setBookId(7);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(2);
         bookGenreInfo.setBookId(8);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(6);
         bookGenreInfo.setBookId(8);
         bookGenreList.add(bookGenreInfo);

         bookGenreInfo = factory.createBookGenreType();
         bookGenreInfo.setGenreId(2);
         bookGenreInfo.setBookId(9);
         bookGenreList.add(bookGenreInfo);

         JAXBElement<BookGenreRoot> bookElement =
                 factory.createBookGenre(bookGenre);
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         marshaller.marshal(bookElement, System.out);
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    The main method for for BookGenreJAXBMarshaller class. Creates a new
    BookGenreJAXBMarshaller and calls the generateXMLDocument method on the
    newly created object.

    @param argv
    */
   public static void main(String[] argv)
   {
      BookGenreJAXBMarshaller jaxbMarshaller = new BookGenreJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
