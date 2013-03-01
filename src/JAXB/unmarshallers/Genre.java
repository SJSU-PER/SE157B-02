package JAXB.unmarshallers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import jaxb.generated.genre.GenreRoot;
import jaxb.generated.genre.GenreType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**

 @author Team Cosmos Erni Ali, Randy Zaatri, Philip Vaca

 The genre class is an object representation of the genre for each book. This
 class stores the name of th ebook, the id of the book and the list of books
 that fall under each genre.
 */
@Entity(name = "genre")
public class Genre
{
   private String name;
   private long id;
   private List<Book> books = new ArrayList<>();

   /**
    Constructor for creating a new Genre with a genre name.

    @param genreName the name of the genre
    */
   public Genre(String genreName)
   {
      this.name = genreName;
   }

   /**
    Constructor for creating a new genre object.
    */
   public Genre()
   {
   }

   @Id
   @GeneratedValue
   @Column(name = "id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name = "genre_name")
   public String getGenreName()
   {
      return name;
   }

   public void setGenreName(String genreName)
   {
      this.name = genreName;
   }

   @ManyToMany
   @JoinTable(name = "Book_Genre",
   joinColumns =
   {
      @JoinColumn(name = "genre_id")
   },
   inverseJoinColumns =
   {
      @JoinColumn(name = "book_id")
   })
   public List<Book> getBooks()
   {
      return books;
   }

   public void setBooks(List<Book> books)
   {
      this.books = books;
   }

   /**
    Takes the Genre XML file representation and deserializes the data into Java
    content trees.

    @param xmlDocument the XML document storing the genre data.
    */
   public void unMarshall(File xmlDocument)
   {

      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.genre");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<GenreRoot> genreElement =
                 (JAXBElement<GenreRoot>) unMarshaller.unmarshal(xmlDocument);
         GenreRoot genre = genreElement.getValue();
         List<GenreType> genreList = genre.getGenreInfo();

         Session session = HibernateContext.getSession();

         Transaction tx = session.beginTransaction();
         {
            for (GenreType genr : genreList)
            {
               session.save(new Genre(genr.getGenreName()));
            }
         }
         tx.commit();
         session.close();
         System.out.println("Genre table loaded.");

      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    Fetch the genre with a matching name.

    @param genre the genre to match.
    @return the genre or null.
    */
   public static Genre find(String genre)
   {
      // Query by example.
      Genre prototype = new Genre();
      prototype.setGenreName(genre);
      Example example = Example.create(prototype);

      Session session = HibernateContext.getSession();
      Criteria criteria = session.createCriteria(Genre.class);
      criteria.add(example);

      Genre gen = (Genre) criteria.uniqueResult();

      session.close();
      return gen;
   }
}
