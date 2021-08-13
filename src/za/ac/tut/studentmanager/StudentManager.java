package za.ac.tut.studentmanager;

import za.ac.tut.exception.StudentException;
import za.ac.tut.student.Student;
import za.ac.tut.studentmanagerinterface.StudentManagerInterface;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentManager implements StudentManagerInterface {
    public StudentManager(){

    }

    @Override
    public void writeStudentDataInTextFile(List<Student> students, File file) throws IOException {
        //open the stream
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        //iterate through each student
        for (Student student : students) {

            //write data
            bw.write(student.toString());

            //get cursor to the next line
            bw.newLine();
        }

        //close
        bw.close();
    }

    @Override
    public void readStudentData(File file) throws IOException {
        String data, records = "";
        //open the reading stream
        BufferedReader br = new BufferedReader(new FileReader(file));

        //read data from the file
        data = br.readLine();

        //read whilst there's still data
        while(data != null){
            //concatenate the records
            records = records + data + "\n";

            //read again
            data = br.readLine();
        }

        //close the reading stream
        br.close();

        //display the data
        JOptionPane.showMessageDialog(null, records);
    }

    @Override
    public List<Student> extractStudentDataFromTheTextFile(File file) throws IOException, StudentException {
        //Declare Variables
        String data;
        Student student;
        int studentNumber,totalMark;

        //empty list of students
        List<Student> students = new ArrayList<>();

        //open the reading stream
        BufferedReader br = new BufferedReader(new FileReader(file));

        //read whilst there's still data
        while((data = br.readLine())!= null){

            //spliting student data to get each element at index,in order to create an object
            String[] studentInfo = data.split(",");

            //converting string to int
            studentNumber=Integer.parseInt(studentInfo[0]);
            totalMark=Integer.parseInt(studentInfo[1]);

            //instantiate a student
            student = new Student(studentNumber,totalMark);

            //add in the list
            students.add(student);
        }

        return students;

    }

    @Override
    public Student GetStudentData( File file,int studentNumber) throws StudentException, NumberFormatException, IOException {

        //List with student data taken out of a text file
        List<Student> students=extractStudentDataFromTheTextFile(file);

        //linear search
        for (Student student : students) {

            if (student.getStudNum() == studentNumber) {

                return student;
            }
        }
    return null;
    }

    @Override
    public boolean changeMarkInTextFile(File file,int studNum,int newMark) throws StudentException, IOException {

        //List with student data taken out of a text file
        List<Student> students=extractStudentDataFromTheTextFile(file);

        boolean isFound=false;

        for(Student student: students){
            //check if the student number is in the list
            if(student.getStudNum()==studNum){

                student.setTotalMark(newMark);

                isFound=true;

            }
        }
        //write back to file
        writeStudentDataInTextFile(students,file);

        return  isFound;

    }

    @Override
    public double determineClassAverage(File file) throws StudentException, IOException {
        //Declare variable
       double average = 0;

        //List with student data taken out of a text file
        List<Student> students= extractStudentDataFromTheTextFile(file);

        //iterate through each student
        for(int i=0;i<students.size();i++){

            //calculate average
            average+= (students.get(i).getTotalMark())/ students.size();
        }

        return average;
    }

    @Override
    public Student determineHighestMark(File file) throws StudentException, IOException {

        //List with student data taken out of a text file
        List<Student> students= extractStudentDataFromTheTextFile(file);

        //Sorting the List of students then reversing to get the student with the highest mark at index at 0
        students.sort(Comparator.comparingInt(Student::getTotalMark).reversed());

        //returns the student with the highest mark found at index 0
        return students.get(0);

    }

}
