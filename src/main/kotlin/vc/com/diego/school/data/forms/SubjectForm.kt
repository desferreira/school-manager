package vc.com.diego.school.data.forms

import org.jetbrains.annotations.NotNull

class SubjectForm(
        @NotNull(value = "Name is required")
        var name: String
) {
}