package JAXB.marshallers;

import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.genre.GenreRoot;
import jaxb.generated.genre.GenreType;
import jaxb.generated.genre.ObjectFactory;

/**

 @author Team Cosmo Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for taking the java object representation of a books
 genre and calling the genreateXMLDocument to create an XML representation of
 these objects.
 */
public class GenreJAXBMarshaller
{
   public void generateXMLDocument()
   {
      try
      {
         JAXBContext jaxbContext = JAXBContext.newInstance("jaxb.generated.genre");
         Marshaller marshaller = jaxbContext.createMarshaller();
         ObjectFactory factory = new ObjectFactory();

         GenreRoot genre = factory.createGenreRoot();
         List<GenreType> genreList = genre.getGenreInfo();

         GenreType genreInfo = factory.createGenreType();
         genreInfo.setId(1);
         genreInfo.setGenreName("Mystery");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(2);
         genreInfo.setGenreName("Horror");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(3);
         genreInfo.setGenreName("Education");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(4);
         genreInfo.setGenreName("Math");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(5);
         genreInfo.setGenreName("Database");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(6);
         genreInfo.setGenreName("Thriller");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(7);
         genreInfo.setGenreName("Child Book");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(8);
         genreInfo.setGenreName("Adventure");
         genreList.add(genreInfo);

         JAXBElement<GenreRoot> genreElement =
                 factory.createGenre(genre);
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         marshaller.marshal(genreElement, System.out);
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
    Main method for the GenreJAXBMarshaller class. Creates a GenreJAXBMarshaller
    object and calls the generateXMLDocument on this newly created object to
    create an XML document representation

    @param argv list of string arguments for this method.
    */
   public static void main(String[] argv)
   {
      GenreJAXBMarshaller jaxbMarshaller = new GenreJAXBMarshaller();
      jaxbMarshaller.generateXMLDocument();
   }
}
