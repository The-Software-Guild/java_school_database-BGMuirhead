package com.wileyedge.database.dao;
import com.wileyedge.database.dao.CourseMapper;
import com.wileyedge.database.dao.SchoolDao;
import com.wileyedge.database.dao.StudentCountMapper;
import com.wileyedge.database.dao.StudentMapper;
import com.wileyedge.database.dao.TeacherCountMapper;
import com.wileyedge.database.dao.TeacherMapper;
import com.wileyedge.database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SchoolDaoImpl implements SchoolDao {

    private final JdbcTemplate jdbcTemplate;

    //complete
    @Autowired
    public SchoolDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    * Add each SQL statement to the methods below.
    * The SQL statement must be completely inside the quotation marks provided
    * in the existing Java statement:
    *   String sql = "";
    *
    * Special notes:
    *   - Strings must be inside single quotation marks (' ').
    *   - Strings are case-sensitive.
    *   - Semi-colons are optional at the end of the SQL statement.
    *
    * Do not change any code outside of the placeholders provided.
     */

    @Override
    public List<Student> allStudents() {
        // Write a query that returns all students (first name, last name only)
        // sorted by last name.
        // YOUR CODE STARTS HERE

        String sql = "SELECT fName,lName FROM student order by lName;";

        // YOUR CODE ENDS HERE

        return jdbcTemplate.query(sql, new StudentMapper());
    }

    @Override
    public List<Course> csCourses() {
        // Write a query that lists the course code and course name
        // for all courses in the Computer Science department.
        // YOUR CODE STARTS HERE

         String sql = "select courseCode,courseDesc from course where courseCode like 'CS%';";

        // YOUR CODE ENDS HERE
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public List<TeacherCount> teacherCountByDept() {
        //  Write a query that displays the department and the total number of teachers assigned to each department.
        //  Name the aggregate field `teacherCount`.
        // YOUR CODE STARTS HERE

        String sql = "select dept,count(*) as teacherCount from teacher group by dept;";

        // YOUR CODE ENDS HERE
        return jdbcTemplate.query(sql, new TeacherCountMapper());
    }

    @Override
    public List<StudentClassCount> studentsPerClass() {
        // Write a query that lists the course code and course description for each course,
        // with the number of students enrolled in each course.
        // Name the aggregate field `numStudents`.
        // YOUR CODE STARTS HERE

        String sql = "select courseCode,courseDesc,count(*) as numStudents  from course c, course_student cs where c.cid = cs.course_id group by( courseCode);";

        // YOUR CODE ENDS HERE
        return jdbcTemplate.query(sql, new StudentCountMapper());
    }

    // This step includes two parts. Both parts must be completed to pass the test.
    // Create a new student and enroll the new student in a course
     @Override
    public void addStudent() {
        // Part 1: Write a query to add the student Robert Dylan to the student table.
        // YOUR CODE STARTS HERE

        String sql = "insert into student(fName,lName) values ('Robert','Dylan');";

        // YOUR CODE ENDS HERE
         System.out.println(jdbcTemplate.update(sql));

    }

    @Override
    public void addStudentToClass() {
        // Part 2: Write a query to add Robert Dylan to CS148.
        // YOUR CODE STARTS HERE

        String sql = "insert into course_student values(10,1);";

        // YOUR CODE ENDS HERE
        jdbcTemplate.update(sql);
    }

    @Override
    public void editCourseDescription() {
        // Write a query to change the course description for course CS305 to "Advanced Python with Flask".
        // YOUR CODE STARTS HERE

        String sql = "update course set courseDesc = 'Advanced Python with Flask' where courseCode = 'CS305';";

        // YOUR CODE ENDS HERE
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteTeacher() {
        // Write a query to remove David Mitchell as a teacher.
        // YOUR CODE STARTS HERE

        String sql = "delete from teacher where tFName ='David' and tLName = 'Mitchell';";

        // YOUR CODE ENDS HERE
        jdbcTemplate.update(sql);
    }

    //***** EXTRA HELPER METHODS
    //***** DO NOT CHANGE THE SQL STRING IN THESE METHODS!!!
    @Override
    public List<Teacher> listAllTeachers() {
        String sql = "Select * from Teacher;";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }

    @Override
    public List<Student> studentsCS148() {
        String sql = "select fname, lname\n" +
                "from student s \n" +
                "join course_student cs on s.sid = cs.student_id\n" +
                "where course_id = 1;";
        return jdbcTemplate.query(sql, new StudentMapper());
    }
}
