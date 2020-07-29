package vc.com.diego.school.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.entities.Subject
import vc.com.diego.school.data.forms.SubjectForm
import vc.com.diego.school.data.forms.SubjectStudent
import vc.com.diego.school.exception.HttpException
import vc.com.diego.school.repositories.ISubjectRepository
import java.lang.RuntimeException
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
            throw HttpException(HttpStatus.NOT_FOUND, Subject::class.toString(), "Not found Subject with id [$id]")
        }
        return subject.get()
    }

    fun createSubject(form: SubjectForm): Subject {
        var subject = Subject(null, form.name)
        this.repository.save(subject)
        return subject
    }

    fun addStudentToClass(subjectId: Long, studentsList: SubjectStudent): List<Student> {
        val subject = this.getById(subjectId)
        var student: Student
        for (id in studentsList.ids){
            student = this.studentService.findById(id)
            subject.addStudent(student)
        }
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