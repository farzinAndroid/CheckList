package com.farzin.checklisttodo.ui.screen.add_update

import android.content.Context
import com.farzin.checklisttodo.R
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener



fun datePicker(context:Context,onDatePickerSucceed:(Int,Int,Int)->Unit) {




    val picker = PersianDatePickerDialog(context)
        .setPositiveButtonString(context.getString(R.string.ok))
        .setNegativeButton(context.getString(R.string.no))
        .setTodayButton(context.getString(R.string.today))
        .setTodayButtonVisible(true)
        .setMinYear(PersianDatePickerDialog.THIS_YEAR)
        .setMaxYear(1500)
//        .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
//        .setMaxDay(PersianDatePickerDialog.THIS_DAY)
//        .setInitDate(1370, 3, 13)
//        .setActionTextColor(Color.GRAY)
//        .setTypeFace()
        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
        .setShowInBottomSheet(true)
        .setListener(object : PersianPickerListener {
            override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                onDatePickerSucceed(persianPickerDate.gregorianYear,persianPickerDate.gregorianMonth,persianPickerDate.gregorianDay)
            }

            override fun onDismissed() {

            }
        })
    picker.show()


}