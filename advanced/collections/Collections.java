package advanced.collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Person implements Comparable<Person>{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

    @Override
    public int compareTo(Person other) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.name,other.name);
    } 
}

class AgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.age, o2.age);
    }
}

public class Collections {

    public static void main(String[] args) {
        Person p1 = new Person("Y", 18);
        Person p2 = new Person("X", 34);
        Person p3 = new Person("A", 12);

        // // For Comparable : compareTo implementation
        // Set<Person> persons = new TreeSet<>();
        // // Add persons to list
        // persons.add(p1);
        // persons.add(p2);
        // persons.add(p3);

        // For Custom comparator
        ArrayList<Person> persons = new ArrayList<Person>();

        // Add persons to list
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        // Sort using custom comparator
        java.util.Collections.sort(persons, new AgeComparator());


        for(Person p: persons) {
            System.out.println("Name: " + p.name + " Age: "+ p.age);
        }
    }
}
