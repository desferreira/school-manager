package vc.com.diego.school.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import vc.com.diego.school.data.entities.Subject
import vc.com.diego.school.data.forms.SubjectForm
import vc.com.diego.school.data.forms.SubjectStudent
import vc.com.diego.school.services.SubjectService

@Controller
@RequestMapping("/api/subject")
class SubjectController(
        @Autowired
        val subjectService: SubjectService
) {

    @GetMapping
    fun findAll(): ResponseEntity<Any> = ok(this.subjectService.getAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Any> = ok(this.subjectService.getById(id))

    @PostMapping
    fun creteSubject(@RequestBody form: SubjectForm): ResponseEntity<Any> = ok(this.subjectService.createSubject(form))

    @PostMapping("/{id}")
    fun registerStudentIntoSubject(@PathVariable id: Long, @RequestBody form: SubjectStudent): ResponseEntity<Any> = ok(this.subjectService.addStudentToClass(id, form))

}