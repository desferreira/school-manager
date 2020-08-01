package vc.com.diego.school.data.forms.subject

import org.jetbrains.annotations.NotNull

class GradeForm(
        var student: Long,
        var subject: Long,
        @NotNull("The grade must be informed")
        var grade: Float
) {
}