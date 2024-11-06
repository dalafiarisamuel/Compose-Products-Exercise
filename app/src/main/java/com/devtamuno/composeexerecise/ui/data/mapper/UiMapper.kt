package com.devtamuno.composeexerecise.ui.data.mapper

interface UiMapper<In, Out> {
    fun mapToUi(input: In): Out
}
