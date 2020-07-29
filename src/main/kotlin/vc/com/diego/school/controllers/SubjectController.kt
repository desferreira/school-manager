package vc.com.diego.school.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import vc.com.diego.school.data.entities.Subject
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
    fun findById(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            var subject = this.subjectService.getById(id)
            return ok(subject)
        } catch (e: Exception){
            return notFound()
        }
    }

}