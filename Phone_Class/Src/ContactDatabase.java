package Src;

import Application.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabase {
    private Connection connection;

    public ContactDatabase() {
        try { if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Phone", "postgres", "mano12");
                createTableIfNotExists();
        }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts (" +
                    "id SERIAL PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "phoneNumber TEXT NOT NULL)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveContact(Contact contact) {
        if (contactExists(contact.getName())) {
            System.out.println("Contact with the same name already exists.");
            return;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO contacts (name, phoneNumber) VALUES (?, ?)")) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setInt(2, contact.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean contactExists(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) FROM contacts WHERE name = ?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void allContacts() {
        List<Contact> contacts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT name, phoneNumber FROM contacts")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    Integer phoneNumber = resultSet.getInt("phoneNumber");
                    Contact contact = new Contact(name, phoneNumber);
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Contact c : contacts) {
            System.out.println(c);
        }
    }

    public void updateContactPhoneNumber(String contactName, Integer newPhoneNumber) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE contacts SET phoneNumber = ? WHERE name = ?")) {
            preparedStatement.setInt(1, newPhoneNumber);
            preparedStatement.setString(2, contactName);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contact phone number updated successfully.");
            } else {
                System.out.println("Contact not found or phone number not updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteContactByName(String contactName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM contacts WHERE name = ?")) {
            preparedStatement.setString(1, contactName);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("Contact not found or not deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
