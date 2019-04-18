package selab.mvc.controllers;

import org.json.JSONObject;

import selab.mvc.models.DataContext;
import selab.mvc.controllers.Controller;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Grade;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Student;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddStudentToCourseController extends Controller {

	DataSet<Grade> grades;
	DataSet<Student> students;
	DataSet<Course> courses;
    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
        grades = dataContext.getGrades();
        students = dataContext.getStudents();
        courses = dataContext.getCourses();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        
        Grade grade = new Grade();
        
        JSONObject input = readJson(body);
        
        grade.setStudentNo(input.getString("studentNo"));
        grade.setCourseNo(input.getString("courseNo"));
        grade.setPoints(Integer.parseInt(input.getString("points")));
        
        Map<String, String> result = new HashMap<>();
        
        if(grade.validate(courses, students)){
        	grades.add(grade);
        	
	        result.put("success", "true");
        }else{
        	result.put("success", "false");
        }
        
        return new JsonView(new JSONObject(result));
    }
}
