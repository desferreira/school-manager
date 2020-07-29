package vc.com.diego.school.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.forms.StudentForm
import vc.com.diego.school.services.StudentService

@Controller
@RequestMapping("/api/students")
class StudentController(@Autowired
                        val studentService: StudentService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Student>> = ok(this.studentService.getAll())

    @PostMapping
    fun createStudent(@RequestBody form: StudentForm): ResponseEntity<Student> = ok(this.studentService.createStudent(form))

}