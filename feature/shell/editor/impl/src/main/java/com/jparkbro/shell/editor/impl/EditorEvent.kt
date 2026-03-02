package com.jparkbro.shell.editor.impl

sealed interface EditorEvent {
    data object SaveSuccess : EditorEvent
    data object SaveFailure : EditorEvent
}
