package vc.com.diego.school.data.enum

import org.springframework.http.HttpStatus
import vc.com.diego.school.exception.HttpException

enum class Status(label: String) {
    INACTIVE("Inativo"),
    ACTIVE("Ativo"),
    BLOCKED("Bloqueado");

    fun block(): Status{
        if (this.ordinal != 2){
            return Status.BLOCKED
        }
        throw HttpException(HttpStatus.BAD_REQUEST, Status::class.toString(), "The actual status is BLOCKED already")
    }

    fun active(): Status{
        if (this.ordinal != 1){
            return Status.ACTIVE
        }
        throw HttpException(HttpStatus.BAD_REQUEST, Status::class.toString(), "The actual status is ACTIVE already")
    }

    fun inactive(): Status{
        if (this.ordinal != 0){
            return Status.INACTIVE
        }
        throw HttpException(HttpStatus.BAD_REQUEST, Status::class.toString(), "The actual status is INACTIVE already")
    }
}