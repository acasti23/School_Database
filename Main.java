// Andrea Castillo 
// Midterm carries 20% of your final grade. 

//Libraries
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args)throws IOException {
      Scanner keyboard = new Scanner(System.in);
      //StudentFileManager Object and data associated
      StudentFileManager s = new StudentFileManager("student.txt");
      String SID,firstName,lastName,stAddress,City,state,zipCode;
      Student stud;
      //CourseFileManager Object and data associated
      CourseFileManager c = new CourseFileManager("course.txt");
      String CID, courseName, courseDescrip;
      Course cour;
      //
      EnrollmentFileManager e = new EnrollmentFileManager("enrollment.txt");
      
      int menueSelect = 0;
      do{
        menueSelect = DisplayMenue();
        switch(menueSelect){
          case 1://Add Student
            System.out.print("*****Add Student*****\nID:");
            SID = keyboard.nextLine();
            //If Student does not exist in Arraylist then collect info
            if( s.GetStudent(SID) == null){
              System.out.print("First Name: "); 
              firstName = keyboard.nextLine(); 
              System.out.print("Last Name: "); 
              lastName = keyboard.nextLine(); 
              System.out.print("Street Address: "); 
              stAddress = keyboard.nextLine(); 
              System.out.print("City: "); 
              City = keyboard.nextLine(); 
              System.out.print("State: "); 
              state = keyboard.nextLine(); 
              System.out.print("Zip Code:"); 
              zipCode = keyboard.nextLine();
              //pass info to add student method
              s.AddStudent(SID,firstName,lastName,stAddress,City,state,zipCode);
            }
            else //Print error message if Student already Exist
                System.out.println("Error: Student already exist");
            break;
            
          case 2://Edit Student
            System.out.print("*****Edit Student Info*****\nEnter Student ID: ");
            SID = keyboard.nextLine();
            //If Student exists in Arraylist then collect info to edit 
            if (s.GetStudent(SID) != null){
              System.out.print("First Name: "); 
              firstName = keyboard.nextLine(); 
              System.out.print("Last Name: "); 
              lastName = keyboard.nextLine(); 
              System.out.print("Street Address: "); 
              stAddress = keyboard.nextLine(); 
              System.out.print("City: "); 
              City = keyboard.nextLine(); 
              System.out.print("State: "); 
              state = keyboard.nextLine(); 
              System.out.print("Zip Code:"); 
              zipCode = keyboard.nextLine();
              s.updateStudent(SID,firstName, lastName, stAddress, City, state, zipCode); 
            }
            else//Print error if the Student does not exist
              System.out.println("Student not found"); 
            break;
              
          case 3:
            System.out.println("*****Add Course*****");
            System.out.print("ID:");
            CID = keyboard.nextLine();
            //if course does not exsist then collect course info, create a new course object and add to arraylist
            if( c.GetCourse(CID) == null){
              System.out.print("Course Name: "); 
              courseName = keyboard.nextLine(); 
              System.out.print("Course Decription: "); 
              courseDescrip = keyboard.nextLine(); 
              c.AddCourse(CID, courseName, courseDescrip);
            }
            else//if course exsit display error message
              System.out.println("Error: Course already exist");
            break; 
            
          case 4:
            System.out.println("Edit Course\nEnter Course ID: ");
            CID = keyboard.nextLine();
            //If Course exists in Arraylist then collect info to edit 
            if ( c.GetCourse(CID) != null){
              System.out.print("Course Name: "); 
              courseName = keyboard.nextLine(); 
              System.out.print("Course Description: "); 
              courseDescrip = keyboard.nextLine(); 
              c.updateCourse(CID, courseName,courseDescrip);
            }
            else//Print error if the Course does not exist
              System.out.println("Student not found"); 
              break;
          case 5:
              System.out.println("Add Enrollment ");
              break; 
          case 6:
              System.out.println("Edit Enrollment");
              break; 
          
          case 7:
            System.out.print("*****Display Student*****\nEnter Student ID: ");
            SID = keyboard.nextLine();
            //If Student exist then display info
            if (s.GetStudent(SID) != null){
              stud = s.GetStudent(SID);
              System.out.println("\nStudent ID:"+ stud.ID +"\nFirst Name: "+ stud.FirstName +"\nLast Name: " + stud.LastName + "\nStreet Adress:"+ stud.Address + "\nCity: "+stud.City +"\nState: "+ stud.State + "\nZip Code: " + stud.Zip);
            }
            else//If Student does not exist print error message
                System.out.println("Student does not exist");            
              break; 
            
          case 8:
            System.out.println("Display Course\nEnter Course ID: ");
            CID = keyboard.nextLine();
            //If course exist then display info
            if (c.GetCourse(CID) != null){
              cour = c.GetCourse(CID);
              System.out.println("\nCourse ID:"+ cour.courseID +"\nCourse Name "+ cour.name +"\nCourse Description: " + cour.description);
            }
            else//If Course does not exist print error message
                System.out.println("Course does not exist");  
              break; 
            
          case 9:
              System.out.println("Display Enrollment");
              break; 
          case 10:
            System.out.println("***********Thank You***********");
            System.exit(0);
            break;
          default:
              System.out.println("Error: Please Enter a valid Selction");
            break;
        }
      } while(menueSelect != 10);
      
    }
  static int DisplayMenue(){ 
        Scanner keyboard = new Scanner(System.in); 
        System.out.println("\n*********Menu********* \n 1.Add student"
                                          + "\n 2.Edit Student"
                                          + "\n 3.Add Course"
                                          + "\n 4.Edit Course"
                                          + "\n 5.Add Enrollment"
                                          + "\n 6.Edit Enrollment"
                                          + "\n 7.Display Student"
                                          + "\n 8.Display Course."
                                          + "\n 9.Display Enrollment"
                                          + "\n10.Exit\n**********************"); 
        int menuSelect = keyboard.nextInt(); 
        keyboard.nextLine(); //skip to next line   
        return menuSelect;  
  }     
}
class StudentFileManager{
  String filename;
  ArrayList<Student> student = new ArrayList<Student>();// arrayList<Student>
    
  StudentFileManager(String filename)throws IOException{//constuctor //cs136
    File file = new File(filename);
    this.filename = filename;
    if (file.exists()){
       Scanner FileScanner = new Scanner(file); //Create File scanner
        //Read File line by line
        while (FileScanner.hasNext()){ 
          String line = FileScanner.nextLine();//read next line
          String[] s = line.split(",");//Split line into a string array
           //Asign the element of array to variables
          String sid = s[0];
          String firstname = s[1];
          String lastname = s[2];
          String address = s[3];
          String city = s[4];
          String state = s[5];
          String zip = s[6];
          //Create Student object using variables
          Student stud = new Student(sid, firstname, lastname, address, city, state, zip);
          student.add(stud);//Add Student Object to array list  
        }
        FileScanner.close();
      }
      else
        System.out.println("Error: File does not exist");
  }
  boolean AddStudent(String ID, String FirstName,String LastName, String Address, String City, String State, String Zip)throws IOException{
      if (GetStudent(ID) == null){
        //If Student ID does not exist then it will add Student to arraylist
        Student stud = new Student(ID, FirstName, LastName, Address, City, State, Zip);
        student.add(stud);

        //Append Student info to file
        FileWriter fwriter = new FileWriter(filename,true); 
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println(stud.ID + "," + stud.FirstName + "," + stud.LastName+ "," + stud.Address + "," + stud.City + "," + stud.State + "," + stud.Zip);
        outputFile.close();
        //Comfirmation that student was Added and returnd true
        System.out.println("Student has been added");
        return true;
      }
      //If student Exist print an error message and return False
      else {
          System.out.println("Student Already Exists");
          return false;
      }
  }
  Student GetStudent(String id ){
    for(int i = 0; i < student.size(); i++){
      Student current = student.get(i);
      String ID = current.ID;
      if (ID.equals(id))
        return current;
    }
    return null; 
  }
    
  boolean updateStudent(String id, String firstname, String lastname, String address, String city, String state, String zip)throws IOException{
    //Call GetStudent student to show is student exist, if student exist then it will update Student info
    if (GetStudent(id) != null){
      Student stud = GetStudent(id);
      stud.setFirstName(firstname);
      stud.setLastName(lastname);
      stud.setAddress(address);
      stud.setCity(city);
      stud.setState(state);
      stud.setZip(zip);
      int index = student.indexOf(GetStudent(id));//Find the location of the student in the arraylist
      student.set(index, stud);//Replace student object in arraylist
      //Write the whole arraylist to the file
      FileWriter fwriter = new FileWriter(filename); 
      PrintWriter outputFile = new PrintWriter(fwriter);
      for (int i = 0; i < student.size(); i++){
        outputFile.println(student.get(i).ID + "," + student.get(i).FirstName + "," + student.get(i).LastName+ "," + student.get(i).Address + "," + student.get(i).City + "," + student.get(i).State + "," + student.get(i).Zip);
      }
      outputFile.close();
      System.out.println("Student has been updated");
        return true;
      }
      //If Student not found, display error message and return false
      System.out.println("Student Does Not Exist");
      return false;
    }
}

class CourseFileManager{
  ArrayList<Course> courses = new ArrayList<Course>(); // arrayList<Courset>
  String filename; 
    
  //Constructor
  CourseFileManager(String filename)throws IOException{
    this.filename = filename;
    File file = new File(filename);
    Scanner FileScanner = new Scanner(file); //Create File scanner
    //Read File line by line
    if (file.exists()){
      while (FileScanner.hasNext()){ 
        String line = FileScanner.nextLine();//read next line
        String[] c = line.split(",");//Split line into a string array
        //Asign the element of array to variables
        String courseId = c[0];
        String courseName = c[1];
        String courseDescription = c[2];
        //Create course object using variables
        Course course = new Course(courseId, courseName, courseDescription);
        courses.add(course);//Add course Object to array list 
      }
      FileScanner.close();
    }
    else//If file does not exist then display an error message
      System.out.println("Error: File Does Not Exist");
  }
  
  boolean AddCourse(String CID, String courseName, String courseDescrip)throws IOException{
    //If course does not exist collect info, create new course object and add to arraylist and return true 
    if (GetCourse(CID) == null){//Call getCourse method to find if course exist
      Course cour = new Course(CID, courseName, courseDescrip);
      courses.add(cour);
      
      //Append the new course object to the file
      FileWriter fwriter = new FileWriter(filename,true); 
      PrintWriter outputFile = new PrintWriter(fwriter);
      outputFile.println(cour.courseID + "," + cour.name + "," + cour.description);
      outputFile.close();
      
      System.out.println("Course has been added/n");//Confirmation Statement
      return true;
    }
    else {//If the course already exist then display an error message and return false
      System.out.println("Course Already Exists");
      return false;
    }
  }
  Course GetCourse(String cid){
    //Scan through the course arraylist and return the course object that has the same cid
    for(int i = 0; i < courses.size(); i++){
      Course current = courses.get(i);
      String ID = current.courseID;
      if (ID.equals(cid))        
        return current;
    }
    //If course not found, return null
    return null;
  }
  boolean updateCourse(String cid, String courName, String courseDescription)throws IOException{
    //Check if course exists, if it does then update the course object and return true
      if (GetCourse(cid) != null){
        System.out.println("Course Exists");
        Course cour = GetCourse(cid);
        cour.setID(cid);
        cour.setName(courName);
        cour.setDescription(courseDescription);
        
        //print every course in Arraylist in a wiped fi;e
        FileWriter fwriter = new FileWriter(filename); 
        PrintWriter outputFile = new PrintWriter(fwriter);
        for (int i = 0; i < courses.size(); i++){
          outputFile.println(courses.get(i).name + "," + courses.get(i).name + "," + courses.get(i).description);  
        }
        outputFile.close();
        System.out.println("Course Has Been Updated");
        return true;
      }
      //if the course does not exsist then display an error message and return false
      System.out.println("Course Does Not Exist");
      return false;
  }
}
class EnrollmentFileManager{
  File file = new File ("enrollment.txt"); //Open File
  Scanner FileScanner = new Scanner(file); //Create File scanner
  ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();// arrayList<Enrollment>
  //Constructure
  EnrollmentFileManager(String filename)throws IOException{
    File file = new File(filename);
    Scanner FileScanner = new Scanner(file); //Create File scanner
    //Read File line by line
    while (FileScanner.hasNext()){ 
      String line = FileScanner.nextLine();//read next line
      String[] e = line.split(",");//Split line into a string array
      String SID = e[0];
      //Asign the element of array to variables
      String CID = e[1];
      String EID = e[2];
      String Year = e[3];
      String Semester = e[4];
      char Grade = e[5].charAt(0);
      //Create course object using variables
      Enrollment enrollment = new Enrollment(SID, CID, EID, Year, Semester,Grade);
      enrollments.add(enrollment);//Add course Object to array list 
    }
  }
  boolean addEnrollment(String CID, String SID, String EID, String year, String semester, char grade)throws IOException{
    if (GetEnrollment(CID, SID, EID, year, semester, grade) != null){
      Enrollment enroll = GetEnrollment(CID, SID, EID, year, semester, grade);
      enroll.setCID(CID);
      enroll.setSID(SID);
      enroll.setYear(year);
      enroll.setSemester(semester);
      enroll.setGrade(grade);
      System.out.println("Enrollment Has Been Added");
      return true;
    }
    System.out.println("Enrollment Already Exists");
    return false;
  }
  
  Enrollment GetEnrollment(String cid, String sid, String eid, String Year, String Semester, char Grade) throws IOException{
    CourseFileManager c = new CourseFileManager("course.txt");
    if (c.GetCourse(cid) != null){
      StudentFileManager s = new StudentFileManager("student.txt");
      if (s.GetStudent(sid) != null){
        for(int i =0; i<= enrollments.size(); i++){
        Enrollment current = enrollments.get(i);
        String ID = current.EID;
          if(ID.equals(eid))   
            return current;
        }
      }
    }
    System.out.println("Enrollment Does Not Exist");
    return null;
  }
}


class Student{
  String ID, FirstName, LastName, Address, City, State, Zip;
  //Constructor
  Student(String ID, String FirstName,String LastName, String Address, String City, String State, String Zip){
      this.ID = ID;
      this.FirstName = FirstName;
      this.LastName = LastName;
      this.Address = Address;
      this.City = City;
      this.State = State;
      this.Zip = Zip;
  }
  //Setters
  void setID(String SID){
      ID = SID;
  }
  void setFirstName(String firstName){
      FirstName = firstName;
  }
  void setLastName(String lastName){
      LastName = lastName;
  }
  void setAddress(String address){
      Address = address;
  }
  void setCity(String city){
      City = city;
  }
  void setState(String state){
      State = state;
  }
  void setZip(String zip){
      Zip = zip;
  }
}

class Course{
  String courseID, name, description;
  Course(String courseID, String name, String description){
    this.courseID = courseID;
    this.name = name;
    this.description = description;
  }
  void setID(String courseID){
    this.courseID = courseID;
  }
  void setName(String name){
    this.name = name;
  }
  void setDescription(String description){
    this.description = description;
  }
}
class Enrollment{
    String SID, CID, EID, year, semester;
    char grade;
    Enrollment(String CID, String SID, String EID, String year, String semester, char grade){
      this.SID = SID;
      this.CID = CID;
      this.EID = EID;
      this.year = year;
      this.semester = semester;
      this.grade = grade;
    }
    void setCID(String CID) {
        this.CID = CID;
    }
    void setSID(String SID){
        this.SID = SID;
    }
  void setEID(String EID){
    this.EID = EID; 
  }
    void setYear(String year) {
        this.year = year;
    }
    void setSemester(String semester) {
        this.semester = semester;
    }
  void setGrade(char grade){
    this.grade = grade;
  }
}