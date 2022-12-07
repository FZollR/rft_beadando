import javax.security.auth.Subject;
import java.util.HashMap;
import java.util.Map;

    public class SubjectRecordSystem {
        // Map to store subjects, where the key is the subject ID
        // and the value is the subject object
        private Map<String, Subject> subjects;

        public SubjectRecordSystem() {
            // Initialize the subjects map
            subjects = new HashMap<>();
        }

        public void addSubject(String id, String name, String time, String nameOfTheLecturer) {
            // Create a new subject object with the given information
            Subject subject = new Subject(id, name, time, nameOfTheLecturer);

            // Add the subject to the map
            subjects.put(id, subject);
        }

        public Subject getSubject(String id) {
            // Return the subject object for the given ID, or null if not found
            return subjects.get(id);
        }

        public void updateSubject(String id, String name, String time, String nameOfTheLecturer) {
            // Get the subject object for the given ID
            Subject subject = subjects.get(id);

            // Update the subject's fields with the given information
            subject.setName(name);
            subject.setTime(time);
            subject.setName(nameOfTheLecturer);
        }

        public void deleteSubject(String id) {
            // Remove the subject from the map
            subjects.remove(id);
        }


        // Inner class to represent a subject
        public class Subject {
            public Map<String, Student> students;
            private String id;
            private String name;
            private String time;
            private String nameOfTheLecturer;


            public Subject(String id, String name, String time, String nameOfTheLecturer) {
                this.id = id;
                this.name = name;
                this.time = time;
                this.nameOfTheLecturer = nameOfTheLecturer;
                students = new HashMap<>();

            }



            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getNameOfTheLecturer() {
                return name;
            }

            public void setNameOfTheLecturer(String name) {
                this.name = name;
            }
            public void printStudents() {
                // Loop through all subjects in the map
                for (Student student : students.values()) {
                    // Print the subject's name to the console
                    System.out.println(" A hallgató neptun-kódja:  " + student.getName() + " || A hallgató neve: " +student.getNeptunKod());

                }
            }


        }

            public void joinSubject(String studentId, String studentName, String subjectId) {
                // Get the subject object for the given ID
                Subject subject = subjects.get(subjectId);

                // Check if the subject exists
                if (subject == null) {
                    // The subject doesn't exist, return without doing anything
                    return;
                }

                // Create a new student object with the given information
                Student student = new Student(studentId, studentName);

                // Add the student to the subject's students map
                subject.students.put(studentId, student);

            }


            public void printSubjectNames() {
                // Loop through all subjects in the map
                for (Subject subject : subjects.values()) {
                    // Print the subject's name to the console
                    System.out.println("A tárgy neve: " + subject.getName() + "|| Az időpontja: " + subject.time + " || Az előadó neve: " + subject.nameOfTheLecturer);
                    subject.printStudents();
                }
            }
        public void printStudentsForSubject(String subjectId) {
            // Get the subject object for the given ID
            Subject subject = subjects.get(subjectId);

            // Check if the subject exists
            if (subject == null) {
                // The subject doesn't exist, return without doing anything
                return;
            }

            // Print the students for the subject
            subject.printStudents();
        }

            @Override
            public String toString() {
                return "SubjectRecordSystem{" +
                        "subjects=" + subjects +
                        '}';
            }
        }


