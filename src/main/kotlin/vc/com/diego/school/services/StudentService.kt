package vc.com.diego.school.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.forms.student.StudentForm
import vc.com.diego.school.exception.HttpException
import vc.com.diego.school.repositories.IStudentRepository
import java.util.logging.Level
import java.util.logging.Logger

@Service
class StudentService(
        @Autowired
        private var studentRepository: IStudentRepository
) {

    private var logger = Logger.getLogger("studentService")

    fun getAll(): List<Student> {
        var students = this.studentRepository.findAll()
        logger.log(Level.INFO, students.size.toString())
        students.stream().forEach { s -> logger.log(Level.INFO, s.toString()) }
        return students
    }

    fun createStudent(form: StudentForm): Student {
        var student = this.createStudentFromForm(form)
        return this.studentRepository.save(student)
    }


    fun updateStudent(id: Long, form: StudentForm): Student {
        var oldStudent = this.studentRepository.findById(id)
        if (oldStudent.isPresent) {
            var newStudent = this.updateStudentFromForm(oldStudent.get(), form)
            this.studentRepository.save(newStudent)
        }
        throw HttpException(HttpStatus.NOT_FOUND, Student::class.toString(), "The student with id [$id] was not found")

    }

    private fun updateStudentFromForm(old: Student, new: StudentForm): Student {
        old.name = new.name
        old.cpf = new.cpf
        old.birthDate = if (new.birthDate != null) new.birthDate else old.birthDate
        return old
    }

    private fun createStudentFromForm(form: StudentForm): Student {
        return Student(null, form.name, form.birthDate, form.cpf)
    }

}