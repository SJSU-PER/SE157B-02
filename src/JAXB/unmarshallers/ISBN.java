package JAXB.unmarshallers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 @author Team Cosmo Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for taking the XML document that represents the books
 ISBN numbers and deserializing it into Java content trees.
 */
@Entity(name = "isbn")
public class ISBN
{
   private String ISBNNumber;
   private long id;

   /**
    Constructor for creating an ISBN object
    */
   public ISBN()
   {
   }

   /**
    Creates a new ISBN object setting the ISBNNumber passed as a parameter to
    this objects ISBNNumber.

    @param ISBNNumber the ISBNNumber of the book.
    */
   public ISBN(String ISBNNumber)
   {
      this.ISBNNumber = ISBNNumber;
   }

   @Column(name = "isbn_number")
   public String getISBNNumber()
   {
      return ISBNNumber;
   }

   public void setISBNNumber(String ISBNNumber)
   {
      this.ISBNNumber = ISBNNumber;
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

   public String toString()
   {
      return ISBNNumber;
   }
}
