

## Class StudentFileManager{
  ArrayList‹Students>
  
  ### StudentFileManager (string filename) // Constructor.
    Opens student file, reads each student information into ArrayList‹Student>.
    Closes the file.
  
  ### bool AddStudent(int id, string name, string address, string city, string state,string zip )
    Calls GetStudent method with id to checks to see if the student with that id exist in the Student Arraylist
      If no, the adds the student to the end of the Arraylist. Opens the student file and Writes the ArrayList to the file. returns true.
      If the student with id exist, Displays error message.return false
  
  ### Student GetStudent (int id)
    checks to see if the student with that id exist in the ArrayList. 
      If yes, then reads the student info into student object and returns student object.
      If the student with id does not exist, return null
  
  ### bool updateStudent (int id, string name, string address, string city, string state,string zip)
    Calls Getstudent (id) method with id to checks to see if the student withthat id exist in the Student Arraylist
     If found, updates the object in the Arraylist for that student id.opens the student file, writes the whole arraylist back to the file and returns true.
     If not found, displays error message and returns false
}

## Class CourseFileManager{
  ArrayList<Course>
  
  ### CourseFileManager (string filename) // Constructor
    Opens file, reads each Course information into ArrayList‹Courses>
    Closes the file
  
  ### bool AddCourse(int cid, string coursename, string coursedescrip )
    Calls GetCourse method with id to checks to see if the Course with that id exist in the Course Arraylist.
      If no, then/adds the Course to the end of the Arraylist.Opens the course file and Writes the ArrayList to the file. returns true.
      If the Course with id exist, Displays error message return false,
  
  ### Course GetCourse(int cid)
    checks to see if the Course with that id exist in the Arraylist. 
      If yes, then// reads the course info into Course object and returns course object.
      If the course with id does not exist, return null
  
  ### bool updateCourse(int id, string coursename, string coursedescrip)
    Calls GetCourse(id) method with id to checks to see if the Course with that id exist in the Course Arraylist.
      If found, updates the object in the Arraylist for that Course id. Opens the course file.writes the whole arraylist back to the file and returns true.
      If not found, displays error message and returns false.
}

## Class EnrollmentFileManager{
  ArrayList‹Enrollment›
  
  ### EnrollmentFileManager (string filename) // Constructor
    Opens Enrollment file, reads each Enrollment information into ArrayList‹Enrollment)
    Closes the file
  
  ### bool AddEnrollment (int cid, int stid, int year, string semester, char grade )
    Calls GetEnrollment method to checks to see if the Enrollment with student id,and Courseid and year, semester exist Enrollment Arraylist
      If no, then adds the Enrollment to the end of the Arraylist .Opens the Enrollment file and Writes the ArrayList to the file. returns true.
      If the Enrollment with id, year, studentid, course, semester exist, Displays error message.return false
  
  ### Enrollment GetEnrollment(int sid, int cid, int year, string semester)
    checks to see if the enrollment with student id, and Courseid and year, semesterexist in the ArrayList
      If yes, then reads the Enrollment info into Enrollment object and returns Enrollment object.
      If the course with id does not exist, return null
  
  ### bool updateEnrollment(int eid, int sid, int cid, int year, string semester, char grade)
    Calls GetEnrollment method with id to checks to see if the Enrollment with that student id, courseid, year, semester exist in the Enrollment Arraylist
      If found, updates the object in the Arraylist for that enrolomentOpens the Enrollment file, writes the whole arraylist back to the file and returns true.
      If not found, displays error message and returns false.
}
