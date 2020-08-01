package vc.com.diego.school.data.entities

import org.springframework.http.HttpStatus
import vc.com.diego.school.data.entities.enum.Status
import vc.com.diego.school.exception.HttpException
import javax.persistence.*

@Entity
class Subject(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var name: String,
        var capacity: Integer,
        var status: Status = Status.ACTIVE
) {

    @ManyToMany
    var students: MutableList<Student> = ArrayList()

    fun addStudent(student: Student): List<Student> {
        if (this.students.add(student)) {
            return this.students
        }
        throw HttpException(HttpStatus.BAD_REQUEST, Subject::class.toString(), "Error while inserting student on subject with id $id")
    }

    fun removeStudent(student: Student): List<Student> {
        if (this.students.remove(student)) {
            return this.students
        }
        throw HttpException(HttpStatus.BAD_REQUEST, Subject::class.toString(), "Error while removing student on subject with id $id")
    }

    fun hasSpace(): Boolean {
        return this.capacity > this.students.size
    }


}