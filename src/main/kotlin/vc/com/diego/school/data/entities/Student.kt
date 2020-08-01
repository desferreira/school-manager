package vc.com.diego.school.data.entities

import vc.com.diego.school.data.enum.Status
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Student(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var name: String,
        var birthDate: Date,
        var cpf: String,
        var status: Status = Status.ACTIVE

) {
    override fun toString(): String {
        return "Student(id=$id, name='$name', birthDate=$birthDate, cpf='$cpf')"
    }
}