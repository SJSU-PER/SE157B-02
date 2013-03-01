package JAXB.marshallers;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.isbn.IsbnRoot;
import jaxb.generated.isbn.IsbnType;
import jaxb.generated.isbn.ObjectFactory;

/**

 @author Team Cosmos Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for taking the java object representation of a books
 ISBN number and generating an XML document file representation by calling the
 genreateXMLDocument method.
 */
public class ISBNJAXBMarshaller
{
   public void generateXMLDocument()
   {
      try
      {
         JAXBContext jaxbContext = JAXBContext.newInstance("jaxb.generated.isbn");
         Marshaller marshaller = jaxbContext.createMarshaller();
         ObjectFactory factory = new ObjectFactory();

         IsbnRoot isbn = factory.createIsbnRoot();
         List<IsbnType> isbnList = isbn.getIsbnInfo();

         IsbnType isbnInfo = factory.createIsbnType();
         isbnInfo.setId(1);
         isbnInfo.setIsbnNumber("9781451678628");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(2);
         isbnInfo.setIsbnNumber("9781453239766");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(3);
         isbnInfo.setIsbnNumber("9780671666071");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(4);
         isbnInfo.setIsbnNumber("9780131873254");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(5);
         isbnInfo.setIsbnNumber("9780134330693");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(6);
         isbnInfo.setIsbnNumber("9781476735474");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(7);
         isbnInfo.setIsbnNumber("9781842622001");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(8);
         isbnInfo.setIsbnNumber("9780307743657");
         isbnList.add(isbnInfo);

         isbnInfo = factory.createIsbnType();
         isbnInfo.setId(9);
         isbnInfo.setIsbnNumber("9780307743671");
         isbnList.add(isbnInfo);

         JAXBElement<IsbnRoot> authorElement = factory.createIsbn(isbn);
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         marshaller.marshal(authorElement, System.out);
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    The main method for the ISBNJAXBMarshaller class. Creates a new
    ISBNJAXBMarshaller and calls the generateXMLDocument method on the newly
    created jaxbMarshaller object.

    @param argv list string arguments for this class.
    */
   public static void main(String[] argv)
   {
      ISBNJAXBMarshaller jaxbMarshaller = new ISBNJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
