package telran.citizens.dao;

import telran.citizens.entities.Person;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitizensImpl implements Citizens{
    private static Comparator<Person> lastNameComparator;
    private static Comparator<Person> ageComparator;


    static {
        lastNameComparator = (p1,p2) -> {
            int res = p1.getLastName().compareTo(p2.getLastName());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
        ageComparator = (p1, p2) -> {
            int res = Integer.compare(p1.getAge(),p2.getAge());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
    }

    private List<Person> idList;
    private List<Person> lastNameList;
    private List<Person> ageList;

    public CitizensImpl() {
        idList = new ArrayList<>();
        lastNameList = new ArrayList<>();
        ageList = new ArrayList<>();
    }

    public CitizensImpl(List<Person> citizens) {
        this();
        citizens.forEach(p->add(p));
    }



//    public CitizensImpl(List<Person> idList, List<Person> lastNameList, List<Person> ageList) {
//        this.idList = idList;
//        this.lastNameList = lastNameList;
//        this.ageList = ageList;
//    }

    @Override
    public boolean add(Person person) {
        if (person == null)
            return false;
        int indexList = Collections.binarySearch(idList, person);
        if (indexList>=0)
            return false;
        indexList = - indexList - 1;
        idList.add(indexList, person);
        int indexLastName = Collections.binarySearch(lastNameList, person, lastNameComparator);
        if (indexLastName < 0)
            indexLastName = - indexLastName - 1;
        int indexAge = Collections.binarySearch(ageList, person, ageComparator);
        if (indexAge < 0)
            indexAge = - indexAge - 1;

        lastNameList.add(indexLastName, person);
        ageList.add(indexAge, person);

        return true;
    }
    // TODO add + remove + tests

    @Override
    public boolean remove(int id) {
        Person removePerson = find(id);
        if (removePerson == null)
            return false;
        idList.remove(removePerson);
        lastNameList.remove(removePerson);
        ageList.remove(removePerson);
        return true;
    }

    @Override
    public Person find(int id) {
        Person p = new Person(id, null, null, null);
        int index = Collections.binarySearch(idList, p);
        return index < 0 ? null : idList.get(index);
    }

    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        return null;
    }

    @Override
    public Iterable<Person> find(String lastName) {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonsSortedById() {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonsSortedByAge() {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonsSortedByLastName() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
