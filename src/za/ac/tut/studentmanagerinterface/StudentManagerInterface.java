package za.ac.tut.studentmanagerinterface;

import za.ac.tut.exception.StudentException;
import za.ac.tut.student.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface StudentManagerInterface {
    public void writeStudentDataInTextFile(List<Student> students, File file) throws IOException;
    public void readStudentData(File file) throws IOException;
    public List<Student> extractStudentDataFromTheTextFile(File file) throws IOException, StudentException;
    public Student GetStudentData(File file,int studentNumber) throws StudentException,NumberFormatException,IOException;
    public boolean changeMarkInTextFile(File file,int studNum,int newMark) throws StudentException, IOException;
    public double determineClassAverage(File file) throws StudentException, IOException;
    public Student determineHighestMark(File file)throws StudentException,IOException;
}
