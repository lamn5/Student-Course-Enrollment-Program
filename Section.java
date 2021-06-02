import java.util.ArrayList;

public class Section {
    private final String sectionName;
    private final int maxEnrolment;
    private final ArrayList<Student> enrolledStudents;

    Section(String sectionName, int maxEnrolment) {
        enrolledStudents = new ArrayList<>();
        this.maxEnrolment = maxEnrolment;
        this.sectionName = sectionName;
    }

    /**
     * Enroll student to section
     *
     * @param student Student to enroll
     * @return true - if student enrolled into section, false - otherwise
     */
    boolean enrollStudent(Student student) {
        if (enrolledStudents.size() >= maxEnrolment)
            return false;
        enrolledStudents.add(student);
        return true;
    }

    /**
     * Un-enroll student from section
     *
     * @param student Student to un-enroll
     * @return true - if student un-enrolled from section, false - otherwise
     */
    public boolean unenrollStudent(Student student) {
        for (int i = 0; i < enrolledStudents.size(); i++) {
            if (enrolledStudents.get(i).getStudentID() == student.getStudentID()) {
                enrolledStudents.remove(i);
                return true;
            }
        }
        return false;
    }

    public String getSectionName() {
        return sectionName;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}
