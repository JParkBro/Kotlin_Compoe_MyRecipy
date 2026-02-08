package com.jparkbro.shell.calendar.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object CalendarNavKey : NavKey

fun Navigator.navigateToCalendar() {
    navigate(CalendarNavKey)
}