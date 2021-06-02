public class TerminalPrinter {
    static void printEnrollmentResult(boolean wasSuccessful,
                                      String studentName,
                                      String courseName,
                                      String sectionName) {
        if (wasSuccessful) {
            System.out.println(studentName + " WAS ENROLLED IN " + courseName + " SECTION " + sectionName);
        } else {
            System.out.println(studentName + " WAS NOT ENROLLED IN " + courseName + " SECTION " + sectionName);
        }
    }

    static void printUnenrollmentResult(boolean wasSuccessful,
                                        String studentName,
                                        String courseName,
                                        String sectionName) {
        if (wasSuccessful) {
            System.out.println(studentName + " WAS UNENROLLED FROM " + courseName + " SECTION " + sectionName);
        } else {
            System.out.println(studentName + " WAS NOT UNENROLLED FROM " + courseName + " SECTION " + sectionName);
        }
    }

}
