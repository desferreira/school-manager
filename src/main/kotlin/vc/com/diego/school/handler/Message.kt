package vc.com.diego.school.handler

import org.springframework.http.HttpStatus

/**
 * Objeto que será enviado nas respostas quando acontecer um erro na aplicação
 * Ele será utilizado no ResponseHandler para transformar a exceção em mensagem de erro em um objeto corretamente
 */
class Message(
        /**
         * Mensagem que veio da aplicação
         */
        var message : String,

        /**
         * Código do erro da aplicação
         */
        var code: HttpStatus
)