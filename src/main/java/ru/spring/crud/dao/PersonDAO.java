package ru.spring.crud.dao;

import org.springframework.stereotype.Component;
import ru.spring.crud.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 20, "tom@email"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 30, "bob@email"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 40, "mike@email"));
        people.add(new Person(++PEOPLE_COUNT, "Kate", 50, "kate@email"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToUpdate = show(id);
        personToUpdate.setName(updatePerson.getName());
        personToUpdate.setAge(updatePerson.getAge());
        personToUpdate.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}
