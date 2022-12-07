import javax.security.auth.Subject;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        //CONFIG
        SubjectRecordSystem system = new SubjectRecordSystem();
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/./rft", "sa", "admin");
        String create = "CREATE TABLE IF NOT EXISTS DATABASE" + "(ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "NAME VARCHAR(255), " + " IDOPONT VARCHAR(255), " + " AZONOSITO VARCHAR(255), " + " TARGYNEV VARCHAR(255),"
                + "TANÁR BOOLEAN,"+ "TANULONEPTUNKOD VARCHAR(255))";
        Statement statement = connection.createStatement();
        statement.executeUpdate(create);


        //PROGRAM
        //Ask the details of the lecturer and the subject's
        System.out.println("Kérem az előadó nevét: ");
        String name = scanner.next();
        System.out.println("Kérem az előadás időpontját: ");
        String time = scanner.next();
        System.out.println("Kérem a tárgy azonosítóját ");
        String id = scanner.next();
        System.out.println("Kérem a tárgy nevét: ");
        String nameOfTheSubject = scanner.next();
        system.addSubject(id, nameOfTheSubject, time, name);

        //Ask the student's details, and register the student to the course, what the lecturer has given
        System.out.println("Kérem a diák nevét: ");
        String hallgatoName = scanner.next();
        System.out.println("Kérem a diák neptun kódját: ");
        String hallgatoId = scanner.next();
        Student student = new Student(hallgatoName, hallgatoId);

        //Register the student to the course and print it out
        system.joinSubject(student.getNeptunKod(), student.getName(), id);
        system.printSubjectNames();
        system.printStudentsForSubject(id);


        //Send the details to the database
        String query = "SELECT NAME FROM DATABASE";
        Statement statement2 = connection.createStatement();
        ResultSet result = statement2.executeQuery(query);
        String eloado;
        String hal;
        int count = 0;
        int count2 = 0;
        while (result.next()) {
            eloado = result.getString("NAME");
            hal = result.getString("NAME");
            if (eloado.equals(name)) {
                count++;
            } else if (hal.equals(hallgatoName)) {
                count2++;
            }
        }
        if (count == 0) {
            String crUser = "INSERT INTO DATABASE (NAME, IDOPONT, AZONOSITO, TARGYNEV, TANÁR) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(crUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, id);
            preparedStatement.setString(4, nameOfTheSubject);
            preparedStatement.setBoolean(5, true);

            preparedStatement.executeUpdate();
        }
            if (count2 == 0) {
                String cr2User = "INSERT INTO DATABASE (NAME, IDOPONT, AZONOSITO, TARGYNEV, TANÁR, TANULONEPTUNKOD) VALUES(?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(cr2User);
                preparedStatement.setString(1, hallgatoName);
                preparedStatement.setString(2, time);
                preparedStatement.setString(3, id);
                preparedStatement.setString(4, nameOfTheSubject);
                preparedStatement.setBoolean(5, false);
                preparedStatement.setString(6, student.getNeptunKod());
                preparedStatement.executeUpdate();
            }

        }
    }

