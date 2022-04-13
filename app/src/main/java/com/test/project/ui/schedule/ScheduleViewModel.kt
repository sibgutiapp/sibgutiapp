package com.test.project.ui.schedule

import androidx.lifecycle.ViewModel
import com.test.project.domain.entity.Lesson

class ScheduleViewModel : ViewModel() {
    val tabTitles = arrayOf(
        "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"
    )
    val scheduleArray = listOf(
        listOf(
            Lesson("Математика", "Трофимов", "Лекция", "210(К.1)"),
            Lesson("Физика", "Гулидов", "Практика", "408(К.1)")
        ),
        listOf(
            Lesson("АиВМО", "Галкина", "Лекция", "202(К.1)"),
            Lesson("АиВМО", "Галкина", "Практика", "408(К.1)")
        ),
        listOf(
            Lesson("Математика", "Трофимов", "Лекция", "210(К.1)"),
            Lesson("Физика", "Гулидов", "Практика", "408(К.1)")
        ),
        listOf(
            Lesson("АиВМО", "Галкина", "Лекция", "202(К.1)"),
            Lesson("АиВМО", "Галкина", "Практика", "408(К.1)")
        ),
        listOf(
            Lesson("Математика", "Трофимов", "Лекция", "210(К.1)"),
            Lesson("Физика", "Гулидов", "Практика", "408(К.1)")
        ),
        listOf(
            Lesson("АиВМО", "Галкина", "Лекция", "202(К.1)"),
            Lesson("АиВМО", "Галкина", "Практика", "408(К.1)")
        )
    )
}