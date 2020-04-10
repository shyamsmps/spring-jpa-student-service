# spring-jpa-student-service
REST service for student resource using spring and JPA

### Payload for student entity

    {
      "id": 123,
      "name": "shyam suthar",
      "age": 28,
      "subjects": [
        {
          "id": 1,
          "subjectName": "History"
        },
        {
          "id": 2,
          "subjectName": "Maths"
        }
      ]
    }
    
### In memory database
- H2 is used as an in memory database. 
- H2 console can be viewed at http://localhost:8080/h2-console when app is running
