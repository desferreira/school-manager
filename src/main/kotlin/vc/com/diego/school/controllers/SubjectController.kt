package vc.com.diego.school.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import vc.com.diego.school.data.forms.subject.GradeForm
import vc.com.diego.school.data.forms.subject.SubjectForm
import vc.com.diego.school.data.forms.subject.SubjectStudent
import vc.com.diego.school.services.GradeService
import vc.com.diego.school.services.SubjectService

@Controller
@RequestMapping("/subject")
class SubjectController(
        @Autowired
        val subjectService: SubjectService,
        @Autowired
        val gradeService: GradeService
) {

    @GetMapping
    fun findAll(): ResponseEntity<Any> = ok(this.subjectService.getAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Any> = ok(this.subjectService.getById(id))

    @PostMapping
    fun creteSubject(@RequestBody form: SubjectForm): ResponseEntity<Any> = ok(this.subjectService.createSubject(form))

    @PutMapping("/{id}")
    fun updateSubject(@PathVariable id: Long, @RequestBody form: SubjectForm): ResponseEntity<Any> = ok(this.subjectService.update(form, id))

    @PostMapping("/{id}")
    fun registerStudentIntoSubject(@PathVariable id: Long, @RequestBody form: SubjectStudent): ResponseEntity<Any> = ok(this.subjectService.addStudentToClass(id, form))

    @DeleteMapping("/{id}")
    fun removeStudentFromSubject(@PathVariable id: Long, @RequestBody form: SubjectStudent): ResponseEntity<Any> = ok(this.subjectService.removeStudentFromClass(id, form))

    @GetMapping("/block/{id}")
    fun blockSubject(@PathVariable id: Long): ResponseEntity<Any> = ok(this.subjectService.block(id))

    @GetMapping("/active/{id}")
    fun activeSubject(@PathVariable id: Long): ResponseEntity<Any> = ok(this.subjectService.active(id))

    @GetMapping("/inactive/{id}")
    fun inactiveSubject(@PathVariable id: Long): ResponseEntity<Any> = ok(this.subjectService.inactive(id))

    @PutMapping("/grade/{id}")
    fun updateGrade(@PathVariable id: Long, @RequestBody form: GradeForm): ResponseEntity<Any> = ok(this.gradeService.updateGrade(id, form))

    @PostMapping("/grade")
    fun registerGrade(@RequestBody form: GradeForm): ResponseEntity<Any> = ok(this.gradeService.registerGrade(form))

    @GetMapping("/grade/mean/{id}")
    fun getMeanGrade(@PathVariable id: Long): ResponseEntity<Any> = ok(this.gradeService.getMeanGrade(id))

}