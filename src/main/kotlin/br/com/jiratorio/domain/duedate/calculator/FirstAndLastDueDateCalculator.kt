package br.com.jiratorio.domain.duedate.calculator

import br.com.jiratorio.domain.entity.embedded.DueDateHistory
import br.com.jiratorio.extension.time.daysDiff
import br.com.jiratorio.extension.log
import java.time.LocalDate
import java.time.LocalDateTime

object FirstAndLastDueDateCalculator : DueDateCalculator {

    override fun calcDeviationOfEstimate(
        dueDateHistories: List<DueDateHistory>,
        endDate: LocalDateTime,
        ignoreWeekend: Boolean?,
        holidays: List<LocalDate>
    ): Long {
        log.info(
            "Method=calcDeviationOfEstimateBetweenFirstAndLastDueDate, dueDateHistories={}, ignoreWeekend={}, holidays={}",
            dueDateHistories, ignoreWeekend, holidays
        )

        if (dueDateHistories.isEmpty()) {
            return 0
        }

        val first = dueDateHistories.first()
        val last = dueDateHistories.last()

        return first.dueDate.daysDiff(last.dueDate, holidays, ignoreWeekend)
    }

}
