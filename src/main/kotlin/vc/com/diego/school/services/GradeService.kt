package vc.com.diego.school.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import vc.com.diego.school.data.entities.Grade
import vc.com.diego.school.data.forms.subject.GradeForm
import vc.com.diego.school.exception.HttpException
import vc.com.diego.school.repositories.IGradeRepository

@Service
class GradeService(
        @Autowired
        var repository: IGradeRepository,
        @Autowired
        var studentService: StudentService
) {

    fun findById(id: Long): Grade {
        var grade = this.repository.findById(id)
        if (grade.isPresent) {
            return grade.get()
        }
        throw HttpException(HttpStatus.NOT_FOUND, Grade::class.toString(), "The grade with id [$id] does not exist")
    }

    fun registerGrade(form: GradeForm): Grade {
        var grade = this.createFromForm(form)
        return this.repository.save(grade)
    }

    fun updateGrade(id: Long, form: GradeForm): Grade {
        var grade = this.findById(id)
        var newGrade = this.updateFromForm(grade, form)
        this.repository.save(newGrade)
        return newGrade
    }

    fun getMeanGrade(id: Long): Float {
        var grades = this.repository.findAllBySubject(id)
        var sum = Float.MIN_VALUE
        grades.stream().forEach { grade -> sum += grade.grade}
        return sum/grades.size
    }

    private fun updateFromForm(old: Grade, new: GradeForm): Grade {
        old.grade = new.grade
        return old
    }

    private fun createFromForm(form: GradeForm): Grade {
        var student = this.studentService.findById(form.student)
        return Grade(null, form.subject, form.grade, student)
    }

}