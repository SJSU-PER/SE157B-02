package JAXB.marshallers;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.author.AuthorRoot;
import jaxb.generated.author.AuthorType;
import jaxb.generated.author.ObjectFactory;

/**
 @author Team Cosmos: Erni Ali, Phil Vaca, Randy Zaatri

 Solution for CS157B Project #2 AuthorJAXBMarshaller class is responsible for
 taking the author objects representation and generates an XML document by
 calling the generateXMLDocument() method with this information.
 */
public class AuthorJAXBMarshaller
{
   public void generateXMLDocument()
   {
      try
      {
         JAXBContext jaxbContext = JAXBContext.newInstance("jaxb.generated.author");
         Marshaller marshaller = jaxbContext.createMarshaller();
         ObjectFactory factory = new ObjectFactory();

         AuthorRoot author = factory.createAuthorRoot();
         List<AuthorType> authorList = author.getAuthorInfo();

         AuthorType authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(1);
         authorInfo.setFirstName("Stephen");
         authorInfo.setLastName("King");
         authorList.add(authorInfo);

         authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(2);
         authorInfo.setFirstName("James");
         authorInfo.setLastName("Hilton");
         authorList.add(authorInfo);

         authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(3);
         authorInfo.setFirstName("Felix");
         authorInfo.setLastName("Salten");
         authorList.add(authorInfo);

         authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(4);
         authorInfo.setFirstName("Hector");
         authorInfo.setLastName("Garcia-Milina");
         authorList.add(authorInfo);

         authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(5);
         authorInfo.setFirstName("Jeffry");
         authorInfo.setLastName("Ullman");
         authorList.add(authorInfo);

         authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(6);
         authorInfo.setFirstName("Jennifer");
         authorInfo.setLastName("Widom");
         authorList.add(authorInfo);

         authorInfo = factory.createAuthorType();
         authorInfo.setAuthorId(7);
         authorInfo.setFirstName("Prentice");
         authorInfo.setLastName("Hall");
         authorList.add(authorInfo);

         JAXBElement<AuthorRoot> authorElement =
                 factory.createAuthor(author);
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         marshaller.marshal(authorElement, System.out);
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   public static void main(String[] argv)
   {
      AuthorJAXBMarshaller jaxbMarshaller = new AuthorJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
