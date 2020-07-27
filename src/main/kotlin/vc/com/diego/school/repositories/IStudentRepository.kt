package vc.com.diego.school.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vc.com.diego.school.data.entities.Student

@Repository
interface IStudentRepository : JpaRepository<Student, Long>{
}