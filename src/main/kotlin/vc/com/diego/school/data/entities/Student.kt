package vc.com.diego.school.data.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Student(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long?,
        private var name: String,
        private var birthDate: Date,
        private var cpf: String
) {
    override fun toString(): String {
        return "Student(id=$id, name='$name', birthDate=$birthDate, cpf='$cpf')"
    }
}