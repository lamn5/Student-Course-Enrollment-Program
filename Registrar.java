import java.util.ArrayList;
import java.util.Scanner;

public class Registrar {
    ArrayList<Student> students;
    ArrayList<Course> courses;

    private final Scanner input = new Scanner(System.in);

    public Registrar() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    /**
     * Find course by name
     *
     * @param courseName Name of course to find
     * @return Course object if found, null - otherwise
     */
    private Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getUniqueName().equals(courseName))
                return course;
        }

        return null;
    }

    /**
     * Find student by ID
     *
     * @param studentId Id of student to find
     * @return Student object if found, null - otherwise
     */
    private Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentID() == studentId)
                return student;
        }

        return null;
    }

    /**
     * Parse commands and execute them until 'FINISH' command entered
     */
    public void parseCommands() {
        boolean done = false;
        while (!done) {
            String command = input.next();
            switch (command) {
                case "COURSE":
                    String courseName = input.next();
                    courses.add(new Course(courseName));
                    break;

                case "REQUIREMENT":
                    String course1Name = input.next();
                    String course2Name = input.next();
                    Course course1 = findCourseByName(course1Name);
                    Course course2 = findCourseByName(course2Name);
                    if (course1 != null) {
                        if (course2 != null) {
                            course1.addRequirement(course2);
                            course2.addDepends(course1);
                        } else {
                            System.out.println("Cannot find course with name '" + course2Name + "'");
                        }
                    } else {
                        System.out.println("Cannot find course with name '" + course1Name + "'");
                    }
                    break;

                case "STUDENT":
                    String name = input.next();
                    int studentId = input.nextInt();
                    students.add(new Student(name, studentId));
                    break;

                case "SECTION":
                    courseName = input.next();
                    String sectionName = input.next();
                    int capacity = input.nextInt();

                    // Check if we have such course
                    Course course = findCourseByName(courseName);
                    if (course != null) {
                        course.addSection(new Section(sectionName, capacity));
                    } else {
                        System.out.println("Cannot find course with name '" + courseName + "'");
                    }
                    break;

                case "ENROLL":
                    studentId = input.nextInt();
                    courseName = input.next();
                    sectionName = input.next();

                    Student student = findStudentById(studentId);
                    if (student != null) {
                        course = findCourseByName(courseName);
                        if (course != null) {
                            boolean enrollmentResult = course.enrollStudent(student, sectionName);
                            TerminalPrinter.printEnrollmentResult(enrollmentResult, student.getName(),
                                    course.getUniqueName(), sectionName);
                        } else {
                            System.out.println("Cannot find course with name '" + courseName + "'");
                        }
                    } else {
                        System.out.println("Cannot find student with id '" + studentId + "'");
                    }

                    break;

                case "UNENROLL":
                    studentId = input.nextInt();
                    courseName = input.next();
                    sectionName = input.next();

                    student = findStudentById(studentId);
                    if (student != null) {
                        course = findCourseByName(courseName);
                        if (course != null) {
                            boolean enrollmentResult = course.unenrollStudent(student, sectionName);
                            TerminalPrinter.printUnenrollmentResult(enrollmentResult, student.getName(),
                                    course.getUniqueName(), sectionName);
                        } else {
                            System.out.println("Cannot find course with name '" + courseName + "'");
                        }
                    } else {
                        System.out.println("Cannot find student with id '" + studentId + "'");
                    }

                    break;

                case "FINISH":
                    done = true;
                    break;

                default:
                    break;
            }
        }
    }
}
