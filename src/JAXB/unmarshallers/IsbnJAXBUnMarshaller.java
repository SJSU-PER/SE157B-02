package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import jaxb.generated.isbn.IsbnRoot;
import jaxb.generated.isbn.IsbnType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "isbn")
public class IsbnJAXBUnMarshaller
{
   private long id;
   private String isbn13;

   public IsbnJAXBUnMarshaller()
   {
   }

   public IsbnJAXBUnMarshaller(long id, String isbn13)
   {
      this.id = id;
      this.isbn13 = isbn13;
   }

   @Id
   @Column
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name="isbn_number")
   public String getIsbn13()
   {
      return isbn13;
   }

   public void setIsbn13(String isbn13)
   {
      this.isbn13 = isbn13;
   }



   public void unMarshall(File xmlDocument)
   {

      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.isbn");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<IsbnRoot> isbnElement =
                 (JAXBElement<IsbnRoot>) unMarshaller.unmarshal(xmlDocument);
         IsbnRoot isbn = isbnElement.getValue();
         List<IsbnType> isbnList = isbn.getIsbnInfo();


         Session session = HibernateContext.getSession();


         Transaction tx = session.beginTransaction();
         {
            for (IsbnType isbnNum : isbnList)
            {
               session.save(new IsbnJAXBUnMarshaller(isbnNum.getId(),
                       isbnNum.getIsbnNumber()));
            }
         }
         tx.commit();
         session.close();
         System.out.println("ISBN table loaded.");

      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }
}
