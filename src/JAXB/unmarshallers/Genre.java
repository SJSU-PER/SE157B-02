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
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "genre")
public class Genre
{
      private String name;
   private long id;
   private List<Book> books = new ArrayList<>();

   public Genre(String genreName)
   {
      this.name = genreName;
   }

   public Genre()
   {
   }

   @Id
   @GeneratedValue
   @Column(name="id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name="genre_name")
   public String getGenreName()
   {
      return name;
   }

   public void setGenreName(String genreName)
   {
      this.name = genreName;
   }

   @ManyToMany
   @JoinTable(name="Book_Genre",
               joinColumns={@JoinColumn(name="genre_id")},
               inverseJoinColumns={@JoinColumn(name="book_id")})
   public List<Book> getBooks()
   {
      return books;
   }

   public void setBooks(List<Book> books)
   {
      this.books = books;
   }


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
}
