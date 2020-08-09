package vc.com.diego.school.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.forms.student.StudentForm
import vc.com.diego.school.services.StudentService

@Controller
@RequestMapping("/students")
class StudentController(@Autowired
                        val studentService: StudentService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Student>> = ok(this.studentService.getAll())

    @PostMapping
    fun createStudent(@RequestBody form: StudentForm): ResponseEntity<Student> = ok(this.studentService.createStudent(form))

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody form: StudentForm): ResponseEntity<Any> = ok(this.studentService.updateStudent(id, form))
}