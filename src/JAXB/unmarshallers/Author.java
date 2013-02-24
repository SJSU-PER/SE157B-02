package JAXB.unmarshallers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.author.AuthorRoot;
import jaxb.generated.author.AuthorType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "Author")
public class Author
{
   private String firstname;
   private String lastname;
   private long id;
   private List<Book> books = new ArrayList<>();

   public Author()
   {
   }

   public Author(String firstname, String lastname)
   {
      this.firstname = firstname;
      this.lastname = lastname;
   }

   @ManyToMany
   @JoinTable(name="Book_Author",
               joinColumns={@JoinColumn(name="author_id")},
               inverseJoinColumns={@JoinColumn(name="book_id")})
   public List<Book> getBooks()
   {
      return books;
   }

   public void setBooks(List<Book> books)
   {
      this.books = books;
   }

   @Id
   @GeneratedValue
   @Column(name = "author_id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name = "first_name")
   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   @Column(name = "last_name")
   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }


   public void unMarshall(File xmlDocument)
   {

      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.author");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<AuthorRoot> authorElement =
                 (JAXBElement<AuthorRoot>) unMarshaller.unmarshal(xmlDocument);
         AuthorRoot author = authorElement.getValue();
         List<AuthorType> authorList = author.getAuthorInfo();


         Session session = HibernateContext.getSession();


         Transaction tx = session.beginTransaction();
         {
            for (AuthorType auth : authorList)
            {
               session.save(new Author(auth.getFirstName(), auth.getLastName()));
            }
         }
         tx.commit();
         session.close();
         System.out.println("Author table loaded.");

      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    Fetch the author with a matching name.

    @param firstname the first name of author to match.
    @param lastname the last name of author to match.
    @return the author or null.
    */
   public static Author find(String firstname, String lastname)
   {
      // Query by example.
      Author prototype = new Author();
      prototype.setFirstname(firstname);
      prototype.setLastname(lastname);
      Example example = Example.create(prototype);

      Session session = HibernateContext.getSession();
      Criteria criteria = session.createCriteria(Author.class);
      criteria.add(example);

      Author author = (Author) criteria.uniqueResult();

      session.close();
      return author;
   }
}
