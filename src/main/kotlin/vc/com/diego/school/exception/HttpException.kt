package vc.com.diego.school.exception

import org.springframework.http.HttpStatus

/**
 * Exceção erro http. Serve para melhor lidar com erros HTTP nos controllers
 */
class HttpException (

        /**
         * Código HTTP da resposta
         */
        var code : HttpStatus,

        /**
         * Tipo da entidade que não foi encontrada
         */
        var type : String,

        /**
         * Mensagem a ser enviada como resposta
         */
        override val message : String
) : RuntimeException()