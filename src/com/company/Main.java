package com.company;

import za.ac.tut.exception.StudentException;
import za.ac.tut.student.Student;
import za.ac.tut.studentmanager.StudentManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws StudentException, IOException {
        // Declare Variables
        int choice, doItAgain = 1;
        List<Student> students = new ArrayList<Student>();
        File fname = new File("./marks.txt");
        StudentManager sm = new StudentManager();
        boolean checkInput = true;
        boolean change;

        do{
            //User Menu for things they would like to do in the application
            choice=menu();

            //Store the marks of the students in a text file
            if(choice==1){
             try {
                    while (doItAgain != 0) {

                        int studNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the student number"));

                        int totMark = Integer.parseInt(JOptionPane.showInputDialog(null, "enter the total mark of " + studNum));

                        do {
                            //ask user whether they would like to continue storing student numbers and marks
                            doItAgain = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter 1---continue " + "\n " +
                                    "Enter 0---End"));
                            if (doItAgain >= 0 && doItAgain < 2) {
                                checkInput = false;
                            } else {
                                checkInput = true;
                                JOptionPane.showMessageDialog(null, "Enter 1 or 0", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } while (checkInput);

                        //instance of student
                        Student student = new Student(studNum, totMark);

                        //add in a list
                        students.add(student);

                    }

                    //write data to file
                    sm.writeStudentDataInTextFile(students, fname);

                } catch (StudentException | NumberFormatException ne){
                 checkInput=true;
                        JOptionPane.showMessageDialog(null,ne.getMessage());
                } catch (IOException ex){
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                    //Read the students data from the text file and display
            }else if(choice==2){
                try {
                    sm.readStudentData(fname);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Get the test data of a specific student
            }else if(choice==3){

                try {
                    //user input
                        int targetStudNo = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student number of the student you would like to get data"));

                      //invoking the method
                      Student std = sm.GetStudentData(fname,targetStudNo);

                       if (std==null) {
                            JOptionPane.showMessageDialog(null, "The student has not been found", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {

                           JOptionPane.showMessageDialog(null, std.toString(),"Information",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (StudentException | NumberFormatException se) {

                        JOptionPane.showMessageDialog(null, se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                    }


                //Change the marks of a specific student in the text file.
            }else if(choice ==4){

                    try {
                        int studNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the student number, of the student you want to change"));

                        int newMark = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new mark:"));

                        change = sm.changeMarkInTextFile(fname, studNum, newMark);

                        if(change){
                            JOptionPane.showMessageDialog(null,"Successfully changed in the file");
                        }else {
                            JOptionPane.showMessageDialog(null,"Student not found in the file,enter correct student number");
                        }
                    }catch (NumberFormatException | StudentException nfe){
                        checkInput=true;
                        JOptionPane.showMessageDialog(null,nfe.getMessage());
                    }
            }
            // the average test mark.
            else if(choice==5){

                //average
                double average = sm.determineClassAverage(fname);

                //display
                JOptionPane.showMessageDialog(null,"The class average is " + average);
            }
            //Determine and display the highest mark
            else if(choice==6){

                //invoking highest mark method
                Student highestMark= sm.determineHighestMark(fname);

                //display
                JOptionPane.showMessageDialog(null,highestMark.toString());
            }
            //end the application
            else {
                JOptionPane.showMessageDialog(null,"Goodbye");
            }
        }while(choice!=7);

    }

    public static int menu(){
        int choice=0;
        boolean reDo=true;
        do{
            try{
                choice=Integer.parseInt(JOptionPane.showInputDialog("""
                        TEACHER MENU\s
                        --------------------------
                        Enter 1 -- Store the marks of the students in a text file
                        Enter 2 -- Read the students data from the text file and display.
                        Enter 3 -- Get the test data of a specific student
                        Enter 4 -- Change the marks of a specific student in the text file.
                        Enter 5 -- The average test mark.
                        Enter 6 -- display the highest mark.
                        Enter 7 -- End Application"""));
                if(choice>0 && choice<8) {
                    reDo = false;
                }else{
                    reDo=true;
                    JOptionPane.showMessageDialog(null,"Wrong input.Enter a correct number from 1 to 7","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,nfe.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }while (reDo);
        return choice;
    }


    }

