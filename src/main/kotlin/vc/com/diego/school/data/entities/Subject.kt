package vc.com.diego.school.data.entities

import javax.persistence.*

@Entity
class Subject(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var name: String,
        @ManyToMany
        var students: MutableList<Student>

) {

    fun addStudent(student: Student): List<Student> {
        if (this.students.add(student)) {
            return this.students
        }
        throw RuntimeException("Error")
    }

    fun removeStudent(student: Student): List<Student> {
        if (this.students.remove(student)) {
            return this.students
        }
        throw RuntimeException("Error")
    }


}