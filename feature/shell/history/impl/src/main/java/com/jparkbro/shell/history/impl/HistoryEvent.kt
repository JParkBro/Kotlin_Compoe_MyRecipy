package com.jparkbro.shell.history.impl

sealed interface HistoryEvent {
    data object DeleteSuccess : HistoryEvent
    data object DeleteFailure : HistoryEvent
}
