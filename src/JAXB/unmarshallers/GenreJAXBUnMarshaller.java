package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import jaxb.generated.genre.GenreRoot;
import jaxb.generated.genre.GenreType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "genre")
public class GenreJAXBUnMarshaller
{
   private String name;
   private long id;

   public GenreJAXBUnMarshaller(long id, String name)
   {
      this.name = name;
      this.id = id;
   }

   public GenreJAXBUnMarshaller()
   {
   }

   @Id
   @Column()
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name="genre_name")
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
               session.save(new GenreJAXBUnMarshaller(genr.getId(), genr.getGenreName()));
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
