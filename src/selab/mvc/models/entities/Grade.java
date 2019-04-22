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
		Course c;
		Student s;
		try{
			c=courses.get(this.courseNo);
			s=students.get(this.studentNo);
		}catch(Exception e) {
			return false;
		}
		if(this.points > 20)
			return false;
		
		System.out.println(c);
		System.out.println(s);
		
		c.getGrades().add(this);
		c.getStudents_list().add(s);
		
		s.getGrades().add(this);
		s.getCourses_list().add(c.getTitle());
		
        return true;
    }
}
