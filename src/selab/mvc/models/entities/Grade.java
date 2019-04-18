package selab.mvc.models.entities;

import selab.mvc.models.DataSet;
import selab.mvc.models.Model;

public class Grade implements Model {
    private String courseNo;
    private String studentNo;
    private int points;

    @Override
    public String getPrimaryKey() {
        return this.studentNo + " - " + this.courseNo;
    }
    
    public String getCourseNo() {
		return courseNo;
	}
    
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public void setStudentNo(String value) {
        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public boolean validate(DataSet<Course> courses, DataSet<Student> students) {
		try{
			courses.get(this.courseNo);
			students.get(this.studentNo);
		}catch(Exception e) {
			return false;
		}
		if(this.points > 20)
			return false;
		
        return true;
    }
}
