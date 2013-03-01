package JAXB.unmarshallers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.author.AuthorRoot;
import jaxb.generated.author.AuthorType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**

 @author Team Cosmo Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for deserializing the XML data representation of the
 book's author and generating the Java content tree object representation for
 the authors.
 */
@Entity(name = "Author")
public class Author
{
   private String firstname;
   private String lastname;
   private long id;
   private List<Book> books = new ArrayList<>();

   /**
    Constructs a new author with null firstname and lastname string parameters.
    */
   public Author()
   {
   }

   /**
    Constructor for a new author object.

    @param firstname the first name of the author.
    @param lastname the last name of the author.
    */
   public Author(String firstname, String lastname)
   {
      this.firstname = firstname;
      this.lastname = lastname;
   }

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name="Book_Author",
               joinColumns={@JoinColumn(name="author_id")},
               inverseJoinColumns={@JoinColumn(name="book_id")})
   /**
    * Returns the list of books that the author has written.
    */
   public List<Book> getBooks()
   {
      return books;
   }

   /**
    Sets the list of books to the provided list of books parameter.

    @param books the list of books provided when the method is called, this list
    of books will be set to the author objects current list of books.
    */
   public void setBooks(List<Book> books)
   {
      this.books = books;
   }

   @Id
   @GeneratedValue
   @Column(name = "author_id")
   /**
    Returns the ID of the author.
    */
   public long getId()
   {
      return id;
   }

   /**
    Sets the ID of the author.

    @param id
    */
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

   /**
    Unmarshalls the XML document file provided in the method call into a java
    object representation of each author.

    @param xmlDocument the xml document that stores the author data.
    */
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
