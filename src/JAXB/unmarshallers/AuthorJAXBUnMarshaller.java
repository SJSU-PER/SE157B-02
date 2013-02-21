package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.author.AuthorRoot;
import jaxb.generated.author.AuthorType;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
public class AuthorJAXBUnMarshaller
{
   public void unMarshall(File xmlDocument)
   {
      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.author");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<AuthorRoot> catalogElement =
                 (JAXBElement<AuthorRoot>) unMarshaller.unmarshal(xmlDocument);
         AuthorRoot catalog = catalogElement.getValue();
         List<AuthorType> journalList = catalog.getAuthorInfo();

         for (AuthorType journal : journalList)
         {
            System.out.printf("\nAUTHOR id = '%d'\n",
                    journal.getAuthorId());
            System.out.printf("        firstName = '%s'\n",
                    journal.getFirstName());
            System.out.printf("        lastName = '%s'\n",
                    journal.getLastName());
         }
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   public static void main(String[] argv)
   {
      File xmlDocument = new File("src/author.xml");
      AuthorJAXBUnMarshaller jaxbUnmarshaller = new AuthorJAXBUnMarshaller();
      jaxbUnmarshaller.unMarshall(xmlDocument);
   }
}
