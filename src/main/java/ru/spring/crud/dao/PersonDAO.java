package ru.spring.crud.dao;

import org.springframework.stereotype.Component;
import ru.spring.crud.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/firstdb";

    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = "select * from \"Person\"";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            people.add(person);
        }

        return people;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) throws SQLException {

        Statement statement = connection.createStatement();
        String query = "insert into \"Person\" values(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";

        statement.executeUpdate(query);
    }

    public void update(int id, Person updatePerson) {
        /*Person personToUpdate = show(id);
        personToUpdate.setName(updatePerson.getName());
        personToUpdate.setAge(updatePerson.getAge());
        personToUpdate.setEmail(updatePerson.getEmail());*/
    }

    public void delete(int id) {
        //people.removeIf(person -> person.getId() == id);
    }

}
