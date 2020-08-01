package vc.com.diego.school.data.forms.subject

import org.jetbrains.annotations.NotNull

class SubjectStudent(
        @NotNull(value = "The list of shoudn't be null")
        var ids: ArrayList<Long>
)