package vc.com.diego.school.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vc.com.diego.school.data.entities.Student
import vc.com.diego.school.data.forms.StudentForm
import vc.com.diego.school.repositories.IStudentRepository
import java.util.logging.Logger

@Service
class StudentService(
        @Autowired
        private var studentRepository: IStudentRepository
) {

    private var logger = Logger.getLogger("studentService")

    fun getAll(): List<Student> {
        return this.studentRepository.findAll();
    }

    fun createStudent(form: StudentForm): Student {
        var student = this.createStudentFromForm(form)
        return this.studentRepository.save(student)
    }

    private fun createStudentFromForm(form: StudentForm): Student {
        return Student(null, form.name, form.birthDate, form.cpf)
    }

}