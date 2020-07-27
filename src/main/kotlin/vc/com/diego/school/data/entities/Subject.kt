package vc.com.diego.school.data.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Subject(
        @Id
        private var id: Long,
        private var name: String,
        @ManyToMany
        private var students: List<Student>

) {
}