import org.junit.jupiter.api.Test;

import javax.security.auth.Subject;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


    public class SubjectRecordSystemTest {

        @Test
        public void testAddSubject() {
            // Create a new SubjectRecordSystem instance
            SubjectRecordSystem system = new SubjectRecordSystem();

            // Create some test data
            String id = "1";
            String name = "Math";
            String time = "9am - 10am";
            String nameOfTheLecturer = "John Doe";

            // Call the addSubject method
            system.addSubject(id, name, time, nameOfTheLecturer);

            // Get the subject that was just added
            SubjectRecordSystem.Subject subject = system.getSubject(id);

            // Make sure the subject was added correctly
            assertEquals(subject.getId(), id);
            assertEquals(subject.getName(), name);
            assertEquals(subject.getTime(), time);
        }
        @Test
        public void DeleteSubjectTest() {
            // Create a new SubjectRecordSystem
            SubjectRecordSystem system = new SubjectRecordSystem();

            // Add a subject to the system
            String id = "123";
            String name = "Math";
            String time = "09:00";
            String lecturer = "John Doe";
            system.addSubject(id, name, time, lecturer);

            // Verify that the subject was added to the system
            assertNotNull(system.getSubject(id));

            // Delete the subject from the system
            system.deleteSubject(id);

            // Verify that the subject was removed from the system
            assertNull(system.getSubject(id));
        }

        @Test
        public void JoinSubjectTest() {
            // Create a new SubjectRecordSystem object
            SubjectRecordSystem system = new SubjectRecordSystem();

            // Add a subject to the system
            system.addSubject("123", "Math", "9-11", "John Doe");

            // Join a student to the subject
            system.joinSubject("abc", "Jane Doe", "123");

            // Get the subject from the system
            SubjectRecordSystem.Subject subject = system.getSubject("123");

            // Get the student from the subject
            Student student = subject.students.get("abc");

            assertFalse("Jane Doe".equals(student.getName()));

        }
    }






