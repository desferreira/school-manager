package vc.com.diego.school.jobs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import vc.com.diego.school.repositories.IStudentRepository
import java.util.logging.Level
import java.util.logging.Logger
import java.util.stream.Collectors

@Component
@EnableScheduling
class CheckStudents(
        @Autowired
        var studentRepository: IStudentRepository
) {
    private var logger = Logger.getLogger("studentJob")


    @Scheduled(fixedDelay = 6000)
    fun periodicPrintOfStudents() {
        logger.log(Level.WARNING, "Starting JOB")
        var students = this.studentRepository.findAll()
       students.stream().map { s -> {
            logger.log(Level.INFO, s.name)
        } }
    }



}