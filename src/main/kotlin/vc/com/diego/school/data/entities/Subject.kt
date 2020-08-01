package vc.com.diego.school.data.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Subject(
        @Id
        var id: Long,
        var name: String,
        @ManyToMany
        var students: List<Student>,
        var capacity: Integer

) {
}