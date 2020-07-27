package vc.com.diego.school.data.forms

import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.annotations.NotNull
import java.util.*

class StudentForm(
        @NotNull(value = "Name must be provided")
        var name: String,
        @NotNull(value = "CPF is required")
        var cpf: String,
        @JsonFormat(pattern = "dd-mm-yyyy")
        @NotNull(value = "Birthdate is required")
        var birthDate: Date
) {
}