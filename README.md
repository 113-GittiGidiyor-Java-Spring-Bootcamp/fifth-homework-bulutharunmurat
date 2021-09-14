# Beşinci hafta ödevi teslim tarihi : 14 Eylül Salı - Saat 23:00

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)

* The general data flow of Course is stated below:

CourseController(CourseDTO) -> CourseMapper(CourseDTO) -> CourseService(Course DTO is converted to Course with CourseMapper) -> CourseRepository(Course) -> CrudRepository -> Course Entity saved to Database

* The general data flow of Student is stated below:

StudentController(StudentDTO) -> StudentMapper(StudentDTO) -> StudentService(Student DTO is converted to Student with StudentMapper) -> StudentRepository(Student) -> CrudRepository -> Student Entity saved to Database

* The general data flow of Instructor(PermanentInstructor or VisitingResearcher) is stated below:

InstructorController(InstructorDTO) -> InstructorMapper(InstructorDTO) -> InstructorService(Instructor DTO is converted to Instructor with InstructorMapper) -> StudentRepository(Student) -> CrudRepository -> Instructor Entity saved to Database

* Logs are added to database using with log4j
    * log4j properties file for automatically saving logs to database can be found at: SchoolManagement/src/main/resources/log4j.properties
    * For StudentServise an example logger:
```java
public static Logger logger = Logger.getLogger(StudentService.class);

```



## Course Endpoints

### GET
- findAll() : `/api/courses`
  ```
  [
  {
  "id": 37,
  "name": "CALCULUS",
  "code": "MATH101",
  "creditScore": 5.5,
  "instructor": 7
  }
  ]
  ```
- findByNameContaining(String name): `/api/courses/findByNameContaining/{name}`

### POST
- saveCourse(CourseDTO courseDTO): `/api/courses`
```
  {
    "code": "MATH101",
    "creditScore": 5.5,
    "instructorId": 1,
    "name": "CALCULUS",
    "studentListId": [
    1,
    2
    ]
    }
```
- findCourseById(int id) : `/api/courses/{id}`

### PUT
- updateCourse(CourseDTO courseDTO) : `/api/courses`

### DELETE
- deleteById(int id) : `/api/courses/{id}`
- deleteCourseByName(String name) : `/api/courses/byname/{name}`

## Instructor Endpoints

### GET
- findAll() : `/api/instructors`
- findInstructorsById(int id): `/api/instructors/{id}`
- getThreeMostEarningInstructor(): `/api/instructors/getThreeMostEarningInstructor`
- findByNameContaining(String name): `/api/instructors/findByNameContaining/{name}`

### POST
- saveInstructor(InstructorDTO instructorDTO): `/api/instructors`
- saveVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO): `/api/instructors/visitingResearcher`
- savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO): `/api/instructors/permanentInstructor`

### PUT
- updateInstructor(InstructorDTO instructorDTO): `/api/instructors`

### DELETE
- deleteInstructorById(int id): `/api/instructors/{id}`
- deleteInstructorByName(String name): `/api/instructors/byname/{name}`

## Student Endpoints

### GET
- findAll() : `/api/students`
- getNumberOfStudents(): `/api/getNumberOfStudents`
- getAgesWithGrouping() : `/api/getGenderWithGrouping`
- getGenderWithGroupingWithNativeQuery(): `/api/getGenderWithGroupingWithNativeQuery`
- getStudentWithName(String name) : `/api/findByName/{name}`
  ```
  [
  {
  "id": 24,
  "name": "Bulut",
  "address": "Bursa",
  "birthDate": "1994-05-04",
  "gender": "male",
  "courseList": [37]
  }
  ]
  ```
- findByNameContaining(String name) : `/api/students/findByNameContaining/{name}`

### POST
- saveStudent(StudentDTO studentDTO): `/api/students`
- findStudentById(int id) : `/api/students/{id}`

### PUT
- updateStudent(StudentDTO studentDTO) : `/api/students`

### DELETE
- deleteStudentById(int id) : `/api/students/{id}`
- deleteStudentByName(String name) : `/api/students/byname/{name}`

## Logging Endpoints

### GET
- findById() : `/api/logs/{id}`
  ```
  {
  "id": 9,
  "localDate": 1630844960536,
  "message": "Course with Code : MATH1ds01 is already exists!",
  "errorType": "course error"
  }
  ```
  
## CLIENT REQUEST INFO class added to project:

With that class changes on Instructor Salary can be logged to database.
  ```  
public class InstructorSalaryUpdateLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int InstructorId;
    private double percentage;
    private double beforeSalary;
    private double afterSalary;
    private LocalDate transactionDate;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;

}
  ```