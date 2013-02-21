package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import jaxb.generated.publisher.PublisherRoot;
import jaxb.generated.publisher.PublisherType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "Publisher")
public class PublisherJAXBUnMarshaller
{
   private long id;
   private String name;

   public PublisherJAXBUnMarshaller(long id, String name)
   {
      this.id = id;
      this.name = name;
   }

   public PublisherJAXBUnMarshaller()
   {
   }

   @Id
   @Column(name="pub_id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name="pub_name")
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void unMarshall(File xmlDocument)
   {
      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.publisher");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<PublisherRoot> publisherElement =
                 (JAXBElement<PublisherRoot>) unMarshaller.unmarshal(xmlDocument);
         PublisherRoot publisher = publisherElement.getValue();
         List<PublisherType> publisherList = publisher.getPubInfo();

         Session session = HibernateContext.getSession();
         Transaction tx = session.beginTransaction();
         {
            for (PublisherType pub : publisherList)
            {
               session.save(new PublisherJAXBUnMarshaller(pub.getPubId(), pub.getPubName()));
            }
         }
         tx.commit();
         session.close();
         System.out.println("Publisher table loaded.");
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }
}
