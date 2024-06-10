import org.example.HW3.Person;
import org.example.HW3.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPerson {

    @Test
    public void testSetAgeInvalid() {
        Person actual = new Person("Wadha", 20, "123 Main St");

        // This should throw an IllegalArgumentException

        Assertions.assertThrows(IllegalArgumentException.class, () -> actual.setAge(160));
    }



    @Test
    public void testSetAgeValid() {
        Person person = new Person("John Doe", 30, "123 Main St");

        person.setAge(40);
        Assertions.assertDoesNotThrow(() -> person.setAge(40));
    }

    @Test
    public void testCompareById() {

        Student student = new Student("Wadha", 23, "123 Main St", 123456, "Software Engineering");

        assertEquals(true, student.compareById(123456));


    }


    @Test
    public void testSetStudentIdInvalid() {
        Student student = new Student("Bob Johnson", 23, "101 Pine St", 444444, "Physics");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> student.setStudentId(-1));

        Assertions.assertEquals("Invalid student ID", exception.getMessage());
    }






}
