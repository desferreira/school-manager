package vc.com.diego.school.handler

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import vc.com.diego.school.exception.HttpException

@ControllerAdvice
class HttpResponseHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(HttpException::class)
    fun handleHttpException(e: HttpException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity.status(e.code).body(Message(e.message, e.code))
    }

}