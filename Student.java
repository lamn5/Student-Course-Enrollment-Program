public class Student {
    private final int studentID;
    private final String name;

    Student(String name, int studentID) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}
