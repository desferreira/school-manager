package vc.com.diego.school.data.entities

import javax.persistence.*

@Entity
class Grade(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var subject: Long,
        var grade: Float,
        @OneToOne
        var student: Student
) {
}