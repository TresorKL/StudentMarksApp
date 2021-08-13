package za.ac.tut.student;

import za.ac.tut.exception.StudentException;
import za.ac.tut.studentdatainterface.StudentDataInterface;

public class Student implements StudentDataInterface {
    private  int studNum;
    private int totalMark;
    private int percentage;


    public Student(int studNum,int totalMark) throws StudentException{
        setStudNum(studNum);
        setTotalMark(totalMark);
    }

    public int getStudNum() {
        return studNum;
    }

    public void setStudNum(int studNum) throws StudentException {
        if(isStudNumValid(studNum)){
            this.studNum = studNum;
        }else{
            throw new StudentException(studNum + STUDENT_NUMBER_ERROR_MESSAGE);
        }

    }

    private boolean isStudNumValid(int studNum) {
        boolean isValid=false;
        int count=0;
        if(Integer.toString(studNum).length()==STUDENT_NUMBER_LENGTH){
            while ((count<STUDENT_NUMBER_LENGTH) &&(Character.isDigit(Integer.toString(studNum).charAt(count)))){
                count++;
            }
            if(count==STUDENT_NUMBER_LENGTH){
                isValid=true;
            }
        }
        return isValid;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) throws StudentException {
        if(isTotalMarkValid(totalMark)){
            this.totalMark = totalMark;
            setPercentage();
        }else{
            throw new StudentException(totalMark + TEST_MARK_ERROR_MESSAGE);
        }

    }

    private boolean isTotalMarkValid(int totalMark) {
        boolean isValid=false;
        if((totalMark>=MIN_TEST_MARK) &&(totalMark<=MAX_TEST_MARK) ){
        isValid=true;
        }
        return isValid;
    }

    //method to calculate percentage of each student
    public void setPercentage(){

        this.percentage= this.totalMark*OVERALL_PERCENTAGE/MAX_TEST_MARK;
    }

    public int getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return studNum +"," + totalMark + "," + percentage;
    }
}
