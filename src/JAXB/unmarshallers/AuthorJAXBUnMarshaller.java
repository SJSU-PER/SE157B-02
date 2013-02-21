package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.author.AuthorRoot;
import jaxb.generated.author.AuthorType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "Author")
public class AuthorJAXBUnMarshaller
{
   private long id;
   private String firstName;
   private String lastName;

   public AuthorJAXBUnMarshaller(long id, String firstName, String lastName)
   {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public AuthorJAXBUnMarshaller()
   {
   }

   @Id
   @Column(name = "id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name = "first_name")
   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   @Column(name = "last_name")
   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public void unMarshall(File xmlDocument)
   {

      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.author");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<AuthorRoot> catalogElement =
                 (JAXBElement<AuthorRoot>) unMarshaller.unmarshal(xmlDocument);
         AuthorRoot author = catalogElement.getValue();
         List<AuthorType> authorList = author.getAuthorInfo();


         Session session = HibernateContext.getSession();


         Transaction tx = session.beginTransaction();
         {
            for (AuthorType auth : authorList)
            {
               session.save(new AuthorJAXBUnMarshaller(auth.getAuthorId(), auth.getFirstName(), auth.getLastName()));
            }
         }
//            System.out.printf("\nAUTHOR id = '%d'\n",
//                    journal.getAuthorId());
//            System.out.printf("        firstName = '%s'\n",
//                    journal.getFirstName());
//            System.out.printf("        lastName = '%s'\n",
//                    journal.getLastName());
         tx.commit();
         session.close();
         System.out.println("Author table loaded.");

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
