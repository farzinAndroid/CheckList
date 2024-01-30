package com.farzin.checklisttodo.ui.screen.add_update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerComposable(onTimePicked: (String, String, String) -> Unit, clockState: UseCaseState) {


    CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Ltr)) {


        ClockDialog(
            state = clockState,
            selection = ClockSelection.HoursMinutesSeconds { hours, minutes, seconds ->
                onTimePicked(hours.toString(), minutes.toString(), seconds.toString())
            })


    }


}