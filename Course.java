import java.util.ArrayList;

public class Course {
    private final String uniqueName;
    private final ArrayList<Section> sections;
    private final ArrayList<Course> requirements;
    private final ArrayList<Course> depeneds;

    Course(String uniqueName) {
        this.uniqueName = uniqueName;
        sections = new ArrayList<>();
        requirements = new ArrayList<>();
        depeneds = new ArrayList<>();
    }

    /**
     * Add section to course
     *
     * @param section Section to add
     * @return true - if section added, false - if section already exists in course
     */
    boolean addSection(Section section) {
        if (sections.contains(section))
            return false;
        sections.add(section);
        return true;
    }

    /**
     * Add requirement for course
     *
     * @param course Course that required to take this course
     */
    void addRequirement(Course course) {
        requirements.add(course);
    }

    /**
     * Add depend course
     *
     * @param course Course that depends on this course
     */
    void addDepends(Course course) {
        depeneds.add(course);
    }

    /**
     * Enroll student to section of the course
     *
     * @param student     Student to enroll
     * @param sectionName Section to enroll to
     * @return true - if student enrolled to section of the course, false - otherwise (section not found, section is
     * full, etc)
     */
    boolean enrollStudent(Student student, String sectionName) {
        int sectionIndex = getSectionIndex(sectionName);
        if (sectionIndex == -1) {
            return false;
        } else {
            // Check if student enrolled not enrolled in required courses - we cannot enroll to this course
            Section section = sections.get(sectionIndex);
            for (Course course : requirements) {
                if (!course.studentEnrolledInAnySection(student))
                    return false;
            }

            return section.enrollStudent(student);
        }
    }

    /**
     * Check if student enrolled in any section of the course
     *
     * @param student Student to check
     * @return true - if student enrolled in any section of the course, false - otherwise
     */
    private boolean studentEnrolledInAnySection(Student student) {
        for (Section section : sections) {
            if (section.getEnrolledStudents().contains(student))
                return true;
        }

        return false;
    }

    /**
     * Un-enroll student from section of the course
     *
     * @param student     Student to un-enroll
     * @param sectionName Section to un-enroll from
     * @return true - if student un-enrolled from section of the course, false - otherwise (section not found,
     * student is not in section, etc)
     */
    boolean unenrollStudent(Student student, String sectionName) {
        int sectionIndex = getSectionIndex(sectionName);
        if (sectionIndex == -1) {
            return false;
        } else {
            // Check if student enrolled in depend courses - we cannot un-enroll from this course
            Section section = sections.get(sectionIndex);
            for (Course course : depeneds) {
                if (course.studentEnrolledInAnySection(student))
                    return false;
            }
            return section.unenrollStudent(student);
        }
    }

    public String getUniqueName() {
        return uniqueName;
    }

    /**
     * Get section index by section name
     *
     * @param sectionName Section name
     * @return Index of section name - if found, -1 - otherwise
     */
    private int getSectionIndex(String sectionName) {
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).getSectionName().equals(sectionName)) {
                return i;
            }
        }
        return -1;
    }
}
