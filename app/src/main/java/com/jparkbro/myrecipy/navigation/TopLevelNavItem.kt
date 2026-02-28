package com.jparkbro.myrecipy.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jparkbro.core.designsystem.R
import com.jparkbro.shell.calendar.api.CalendarNavKey
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.shell.editor.api.EditorNavKey
import com.jparkbro.shell.history.api.HistoryNavKey
import com.jparkbro.shell.report.api.ReportNavKey
import com.jparkbro.shell.settings.api.SettingsNavKey

data class TopLevelNavItem(
    @param:DrawableRes val selectedIcon: Int,
    @param:DrawableRes val unselectedIcon: Int,
    @param:StringRes val iconTextId: Int,
)

val EDITOR = TopLevelNavItem(
    selectedIcon = R.drawable.ic_add_notes_fill,
    unselectedIcon = R.drawable.ic_add_notes_outline,
    iconTextId = R.string.feature_editor_api_title,
)

val HISTORY = TopLevelNavItem(
    selectedIcon = R.drawable.ic_analytics_fill,
    unselectedIcon = R.drawable.ic_analytics_outline,
    iconTextId = R.string.feature_history_api_title,
)

val CALENDAR = TopLevelNavItem(
    selectedIcon = R.drawable.ic_calendar_month_fill,
    unselectedIcon = R.drawable.ic_calendar_month_outline,
    iconTextId = R.string.feature_calendar_api_title,
)

val REPORT = TopLevelNavItem(
    selectedIcon = R.drawable.ic_receipt_long_fill,
    unselectedIcon = R.drawable.ic_receipt_long_outline,
    iconTextId = R.string.feature_report_api_title,
)

val SETTINGS = TopLevelNavItem(
    selectedIcon = R.drawable.ic_settings_fill,
    unselectedIcon = R.drawable.ic_settings_outline,
    iconTextId = R.string.feature_settings_api_title,
)

val BOTTOM_NAV_ITEMS = mapOf(
    HistoryNavKey to HISTORY,
    CalendarNavKey to CALENDAR,
    EditorNavKey(type = EditorType.INSERT, id = null) to EDITOR,
    ReportNavKey to REPORT,
    SettingsNavKey to SETTINGS,
)

val TOP_LEVEL_NAV_ITEMS = mapOf(
    HistoryNavKey to HISTORY,
    CalendarNavKey to CALENDAR,
    ReportNavKey to REPORT,
    SettingsNavKey to SETTINGS,
)