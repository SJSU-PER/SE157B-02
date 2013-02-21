package JAXB.marshallers;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.genre.GenreRoot;
import jaxb.generated.genre.GenreType;
import jaxb.generated.genre.ObjectFactory;


// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006

public class GenreJAXBMarshaller
{
	public void generateXMLDocument()
    {
		try {
			JAXBContext jaxbContext =
                    JAXBContext.newInstance("jaxb.generated.genre");
			Marshaller marshaller = jaxbContext.createMarshaller();
			ObjectFactory factory = new ObjectFactory();

         GenreRoot genre = factory.createGenreRoot();
         List<GenreType> genreList = genre.getGenreInfo();

         GenreType genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("1"));
         genreInfo.setGenreName("Mystery");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("2"));
         genreInfo.setGenreName("Horror");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("3"));
         genreInfo.setGenreName("Education");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("4"));
         genreInfo.setGenreName("Math");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("5"));
         genreInfo.setGenreName("Database");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("6"));
         genreInfo.setGenreName("Thriller");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("7"));
         genreInfo.setGenreName("Child Book");
         genreList.add(genreInfo);

         genreInfo = factory.createGenreType();
         genreInfo.setId(new BigInteger("8"));
         genreInfo.setGenreName("Adventure");
         genreList.add(genreInfo);

			JAXBElement<GenreRoot> genreElement =
                    factory.createGenre(genre);
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(genreElement, System.out);
		}
        catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] argv)
    {
		GenreJAXBMarshaller jaxbMarshaller = new GenreJAXBMarshaller();
		jaxbMarshaller.generateXMLDocument();
	}
}
