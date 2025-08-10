package telran.citizens.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.citizens.dao.Citizens;
import telran.citizens.dao.CitizensImpl;
import telran.citizens.entities.Person;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CitizensImplTest2 {
    Person[] people;
    Citizens citizens;

    @BeforeEach
    void setUp(){
        people = new Person[] {
                new Person(1, "John", "Smith", LocalDate.of(1977, 5, 5)),
                new Person(2, "Layla", "Smith", LocalDate.of(1981, 12, 10)),
                new Person(3, "Tyler", "Dyrden", LocalDate.of(1975, 3, 15))
        };
        citizens = new CitizensImpl(Arrays.asList(people));
    }

    @Test
    void testAddPositive() {
        Person newPerson = new Person(4, "John", "Lenon", LocalDate.of(1956, 7, 7));

        assertNull(citizens.find(4));
        assertTrue(citizens.add(newPerson));
        assertNotNull(citizens.find(4));
    }

    @Test
    void testAddNegative() {
        assertFalse(citizens.add(people[0]));
        assertFalse(citizens.add(null));
    }

    @Test
    void testRemovePositive() {
        assertTrue(citizens.remove(people[2].getId()));
    }

    @Test
    void testRemoveNegative() {
        assertFalse(citizens.remove(100));
        assertFalse(citizens.remove(0));
    }
}
