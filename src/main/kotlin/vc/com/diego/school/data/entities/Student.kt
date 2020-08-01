package vc.com.diego.school.data.entities

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
        var cpf: String
) {
    override fun toString(): String {
        return "Student(id=$id, name='$name', birthDate=$birthDate, cpf='$cpf')"
    }
}