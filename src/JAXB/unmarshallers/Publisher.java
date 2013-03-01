package JAXB.unmarshallers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import jaxb.generated.publisher.PublisherRoot;
import jaxb.generated.publisher.PublisherType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 @author Team Cosmos Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for taking the XML document that represents the
 publisher data and deserializing it into java content trees to represent the
 publisher data.
 */
@Entity(name = "Publisher")
public class Publisher
{
   private String name;
   private long id;
   private List<Book> books = new ArrayList<>();

   /**
    Constructs a new publisher object, setting the publisher name to this
    objects publisher name.

    @param name the name of the publisher.
    */
   public Publisher(String name)
   {
      this.name = name;
   }

   /**
    Creates a new publisher object.
    */
   public Publisher()
   {
   }

   @Id
   @GeneratedValue
   @Column(name = "pub_id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name = "pub_name")
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @OneToMany(mappedBy = "publisher", targetEntity = Book.class,
   cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   public List<Book> getBooks()
   {
      return books;
   }

   public void setBooks(List<Book> books)
   {
      this.books = books;
   }
   
   /**
    Takes an XML document and unmarshalls the data into the java content trees
    to represent the publishers.

    @param xmlDocument the XML document that stores the publisher data.
    */
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
               session.save(new Publisher(pub.getPubName()));
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

   /**
    Fetch the publisher with a matching name.

    @param pub the publisher to match.
    @return the publisher or null.
    */
   public static Publisher find(String pub)
   {
      // Query by example.
      Publisher prototype = new Publisher();
      prototype.setName(pub);
      Example example = Example.create(prototype);

      Session session = HibernateContext.getSession();
      Criteria criteria = session.createCriteria(Publisher.class);
      criteria.add(example);

      Publisher publisher = (Publisher) criteria.uniqueResult();

      session.close();
      return publisher;
   }

   public String toString()
   {
      return name;
   }
}
