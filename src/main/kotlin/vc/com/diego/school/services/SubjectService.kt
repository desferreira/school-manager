package vc.com.diego.school.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.entities.Subject
import vc.com.diego.school.repositories.ISubjectRepository
import java.lang.RuntimeException
import java.util.logging.Logger

@Service
class SubjectService(
        @Autowired
        private var repository: ISubjectRepository
) {

    private var logger = Logger.getLogger("subjectService")

    fun getAll(): List<Subject> = this.repository.findAll()

    fun getById(id: Long): Subject {
        val subject = this.repository.findById(id)
        if (subject.isEmpty()){
           throw RuntimeException("Not found")
        }
        return subject.get()
    }

    fun addStudentToClass(student: Student, subjectId: Long): List<Student> {
        val subject = this.getById(subjectId)
        subject.addStudent(student)
        this.repository.save(subject)
        return subject.students
    }

    fun removeStudentFromClass(student: Student, subjectId: Long): List<Student> {
        val subject = this.getById(subjectId)
        subject.removeStudent(student)
        this.repository.save(subject)
        return subject.students
    }
}