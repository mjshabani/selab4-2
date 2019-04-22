package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;

    private ArrayList<Grade> grades=new ArrayList<>();
    private ArrayList<String> courses_list=new ArrayList<>();
    
    public ArrayList<Grade> getGrades() {
    	return grades;
    }
    
    public ArrayList<String> getCourses_list() {
    	return courses_list;
    }
    
    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
    	float ans=0;
        int y=0;
        for (Grade i :this.getGrades()) {
        	ans+=i.getPoints();
        	y++;
        }
        if(y<1)
        	y=10;
        return ans/y;
    }

    public String getCourses() {

    	String ans="";
        for (String i :this.getCourses_list()) {
        	ans+=i+",";
        }
        return ans;
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}
