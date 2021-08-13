package za.ac.tut.studentdatainterface;

public interface StudentDataInterface {
    public int MIN_TEST_MARK=0;
    public int MAX_TEST_MARK=110;
    public int OVERALL_PERCENTAGE=100;
    public int STUDENT_NUMBER_LENGTH=5;
    public String TEST_MARK_ERROR_MESSAGE=" is not between 0 and 110.Please enter a mark between 0 and 100";
    public String STUDENT_NUMBER_ERROR_MESSAGE=" is not 5 characters long. Please enter a 5 digit long ";
}
