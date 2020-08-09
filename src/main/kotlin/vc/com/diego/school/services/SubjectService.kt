package vc.com.diego.school.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import vc.com.diego.school.data.entities.Grade
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.entities.Subject
import vc.com.diego.school.data.forms.subject.GradeForm
import vc.com.diego.school.data.forms.subject.SubjectForm
import vc.com.diego.school.data.forms.subject.SubjectStudent
import vc.com.diego.school.exception.HttpException
import vc.com.diego.school.repositories.ISubjectRepository
import java.util.logging.Level
import java.util.logging.Logger

@Service
class SubjectService(
        @Autowired
        private var repository: ISubjectRepository,
        @Autowired
        private var studentService: StudentService
) {

    private var logger = Logger.getLogger("subjectService")

    fun getAll(): List<Subject> = this.repository.findAll()

    fun getById(id: Long): Subject {
        val subject = this.repository.findById(id)
        if (subject.isEmpty()) {
            logger.log(Level.INFO, "Subject with id [$id] does not exist")
            throw HttpException(HttpStatus.NOT_FOUND, Subject::class.toString(), "Not found Subject with id [$id]")
        }
        return subject.get()
    }

    fun update(form: SubjectForm, id: Long): Subject{
        var actualSubject = this.getById(id)
        var newSubject = this.updateSubject(actualSubject, form)
        this.repository.save(newSubject)
        return newSubject
    }

    fun createSubject(form: SubjectForm): Subject {
        var subject = Subject(null, form.name, form.capacity)
        this.repository.save(subject)
        return subject
    }

    fun addStudentToClass(subjectId: Long, studentsList: SubjectStudent): List<Student> {
        val subject = this.getById(subjectId)
        var student: Student
        if (subject.hasSpace()) {
            for (id in studentsList.ids) {
                student = this.studentService.findById(id)
                subject.addStudent(student)
            }
            this.repository.save(subject)
            return subject.students
        }
        throw HttpException(HttpStatus.BAD_REQUEST, Subject::class.toString(), "The student can't be assingned to the subject [${subject.name}]")
    }

    fun removeStudentFromClass(subjectId: Long, studentsList: SubjectStudent): List<Student> {
        val subject = this.getById(subjectId)
        var student: Student
        for (id in studentsList.ids){
            student = this.studentService.findById(id)
            subject.removeStudent(student)
        }
        this.repository.save(subject)
        return subject.students
    }

    fun block(id: Long): Subject {
        var subject = this.getById(id)
        subject.status = subject.status.block()
        return this.repository.save(subject)
    }

    fun active(id: Long): Subject {
        var subject = this.getById(id)
        subject.status = subject.status.active()
        return this.repository.save(subject)
    }

    fun inactive(id: Long): Subject {
        var subject = this.getById(id)
        subject.status = subject.status.inactive()
        return this.repository.save(subject)
    }
    
    private fun updateSubject(old: Subject, new: SubjectForm): Subject {
        old.name = new.name
        old.capacity = new.capacity
        return old
    }

}