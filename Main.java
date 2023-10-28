// Andrea Castillo 
// Midterm carries 20% of your final grade. 

//Libraries
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args)throws IOException {
      Scanner keyboard = new Scanner(System.in);
      StudentFileManager e = new StudentFileManager("student.txt");
      CourseFileManager c = new CourseFileManager("course.txt");
      int menueSelect = 0;
      do{
        menueSelect = DisplayMenue();
        switch(menueSelect){
          case 1:
              System.out.println("*****Add Student*****");
              System.out.print("ID:");
              String SID = keyboard.nextLine();
              if( e.GetStudent(SID) == null){
                  System.out.print("First Name: "); 
                  String firstName = keyboard.nextLine(); 
                  System.out.print("Last Name: "); 
                  String lastName = keyboard.nextLine(); 
                  System.out.print("Street Address: "); 
                  String stAddress = keyboard.nextLine(); 
                  System.out.print("City: "); 
                  String City = keyboard.nextLine(); 
                  System.out.print("State: "); 
                  String state = keyboard.nextLine(); 
                  System.out.print("Zip Code:"); 
                  String zipCode = keyboard.nextLine();
                  e.AddStudent(SID,firstName,lastName,stAddress,City,state,zipCode);
                  //e.printOnFile(SID,firstName,lastName,stAddress,City,state,zipCode);
              }
              else
                  System.out.println("Error: Student already exist");
              break;
          case 2:
            //Edit Student
              System.out.print("*****Edit Student Info*****\nEnter Student ID: ");
              String id = keyboard.nextLine();
              Student stud = e.GetStudent(id);
              if (stud != null){
                System.out.print("First Name: "); 
                String firstName = keyboard.nextLine(); 
                System.out.print("Last Name: "); 
                String lastName = keyboard.nextLine(); 
                System.out.print("Street Address: "); 
                String stAddress = keyboard.nextLine(); 
                System.out.print("City: "); 
                String City = keyboard.nextLine(); 
                System.out.print("State: "); 
                String state = keyboard.nextLine(); 
                System.out.print("Zip Code:"); 
                String zipCode = keyboard.nextLine();
                e.updateStudent(id,firstName, lastName, stAddress, City, state, zipCode); 
                break;
              }
              else{
                System.out.println("Student not found"); 
                break;
              }
          case 3:
            System.out.println("*****Add Course*****");
            System.out.print("ID:");
            String CID = keyboard.nextLine();
            if( c.GetCourse(CID) == null){
                System.out.print("Course Name: "); 
                String courseName = keyboard.nextLine(); 
                System.out.print("Course Decription: "); 
                String courseDescrip = keyboard.nextLine(); 
                c.AddCourse(CID, courseName, courseDescrip);
                //c.printOnFile(SID,firstName,lastName,stAddress,City,state,zipCode);
            }
            else
                System.out.println("Error: Student already exist");
              break; 
          case 4:
              System.out.println("Edit Course");
              break;
          case 5:
              System.out.println("Add Enrollment ");
              break; 
          case 6:
              System.out.println("Edit Enrollment");
              break; 
          case 7:
              System.out.print("*****Display Student*****\nEnter Student ID: ");
              String sid = keyboard.nextLine();
              Student stu = e.GetStudent(sid);
              if (stu != null){
                System.out.println("\nStudent ID:"+ stu.ID +"\nFirst Name: "+ stu.FirstName +"\nLast Name: " + stu.LastName + "\nStreet Adress:"+ stu.Address + "\nCity: "+stu.City +"\nState: "+ stu.State + "\nZip Code: " + stu.Zip);
              }
            else
                System.out.println("Student does not exist");            
              break; 
          case 8:
              System.out.println("Display Course");
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
  
  ArrayList<Student> student = new ArrayList<Student>();// arrayList<Student>
  
  StudentFileManager(String filename)throws IOException{//constuctor //cs136
    File file = new File(filename);
    if (file.exists()){
       Scanner FileScanner = new Scanner(file); //Create File scanner
        //Read File line by line
        while (FileScanner.hasNext()){ 
          String line = FileScanner.nextLine();//read next line
          String[] s = line.split(",");//Split line into a string array
          String id = s[0];
          //Asign the element of array to variables
          String firstname = s[1];
          String lastname = s[2];
          String address = s[3];
          String city = s[4];
          String state = s[5];
          String zip = s[6];
          //Create Student object using variables
          Student stud = new Student(id, firstname, lastname, address, city, state, zip);
          student.add(stud);//Add Student Object to array list  
        }
      }
      else
        System.out.println("Error: File does not exist");
  }
  boolean AddStudent(String ID, String FirstName,String LastName, String Address, String City, String State, String Zip){
      if (GetStudent(ID) == null){
          Student stud = new Student(ID, FirstName, LastName, Address, City, State, Zip);
          student.add(stud);
          System.out.println("Student has been added");
          return true;
      }
      else {
          System.out.println("Student Already Exists");
          return false;
      }
  }
  Student GetStudent(String id ){
    for(int i = 0; i < student.size(); i++){
      Student current = student.get(i);
      String ID = current.ID;
      if (ID.equals(id)){
        return current;
      }    
    }
      return null; 
  }
    
  boolean updateStudent(String id, String firstname, String lastname, String address, String city, String state, String zip)throws IOException{
        if (GetStudent(id) != null){
          int index = student.indexOf(GetStudent(id));
          Student stud = GetStudent(id);
          stud.setFirstName(firstname);
          stud.setLastName(lastname);
          stud.setAddress(address);
          stud.setCity(city);
          stud.setState(state);
          stud.setZip(zip);
          //Replace student delete and add new student array list
          student.set(index, stud);
          File file = new File("student.txt");
          FileWriter fwriter = new FileWriter("student.txt", true); 
          PrintWriter outputFile = new PrintWriter(fwriter);
          outputFile.println();
          for (int i = 0; i < student.size(); i++){
            outputFile.println(student.get(i).ID + "," + student.get(i).FirstName + "," + student.get(i).LastName+ "," + student.get(i).Address + "," + student.get(i).City + "," + student.get(i).State + "," + student.get(i).Zip);
          }
          outputFile.close();
          System.out.println("Student has been updated");
            return true;
        }
        System.out.println("Student Does Not Exist");
        return false;
    }
}

class CourseFileManager{
    ArrayList<Course> courses = new ArrayList<Course>(); // arrayList<Courset>
    FileWriter fwriter = new FileWriter("course.txt", true); 
    PrintWriter outputFile = new PrintWriter(fwriter);
    //Constructor
    CourseFileManager(String filename)throws IOException{
      File file = new File(filename);
      Scanner FileScanner = new Scanner(file); //Create File scanner
      //Read File line by line
      while (FileScanner.hasNext()){ 
        String line = FileScanner.nextLine();//read next line
        String[] c = line.split(",");//Split line into a string array
        String courseId = c[0];
        //Asign the element of array to variables
        String courseName = c[1];
        String courseDescription = c[2];
        //Create course object using variables
        Course course = new Course(courseId, courseName, courseDescription);
        courses.add(course);//Add course Object to array list 
      }
    }
    boolean AddCourse(String CID, String courseName, String courseDescrip){
        if (GetCourse(CID) != null){
            Course cour = new Course(CID, courseName, courseDescrip);
            courses.add(cour);
            System.out.println("Course has been added");
            return true;
        }
        else {
            System.out.println("Course Already Exists");
            return false;
        }
    }
Course GetCourse(String cid ){
  for(int i = 0; i < courses.size(); i++){
    Course current = courses.get(i);
    String ID = current.courseID;
    if (ID.equals(cid)){        
        return current;
    }  
  }
  return null;
}
  boolean updateCourse(String cid, String courName, String courseDescription){
      if (GetCourse(cid) != null){
          System.out.println("Course Exists");
          Course cour = GetCourse(cid);
          cour.setID(cid);
          cour.setName(courName);
          cour.setDescription(courseDescription);
          System.out.println("Course Has Been Updated");
          return true;
      }
      System.out.println("Course Does Not Exist");
      return false;
  }
}
class EnrollmentFileManager{
  File file = new File ("Enrollment.txt"); //Open File
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
    CourseFileManager c = new CourseFileManager("Course.txt");
    if (c.GetCourse(cid) != null){
      StudentFileManager s = new StudentFileManager("Student.txt");
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