package vc.com.diego.school.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import vc.com.diego.school.data.entities.Grade

@Repository
interface IGradeRepository : JpaRepository<Grade, Long> {

    @Query(value = "select g from Grade g where g.subject = ?1")
    fun findAllBySubject(id: Long): List<Grade>

}