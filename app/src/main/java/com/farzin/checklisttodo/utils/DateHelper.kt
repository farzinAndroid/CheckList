package com.farzin.checklisttodo.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateHelper {

    fun splitWholeDate(dateString: String): Array<Int> {

        if (dateString == "") {
            return arrayOf(0, 0, 0)
        }

        val parts = dateString.split("-")// 2024-01-8
        val year = parts[0].toInt()
        val month = parts[1].toInt()
        val day = parts[2].toInt()

        return arrayOf(year, month, day)
    }

    fun gregorianToJalali(gy: Int, gm: Int, gd: Int): String {

        if (gy == 0 || gm == 0 || gd == 0) {
            return ""
        }

        val gDaysInMonth: IntArray =
            intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
        val gy2: Int = if (gm > 2) (gy + 1) else gy
        var gTotalDays: Int =
            355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400) + gd + gDaysInMonth[gm - 1]
        var jy: Int = -1595 + (33 * (gTotalDays / 12053))
        gTotalDays %= 12053
        jy += 4 * (gTotalDays / 1461)
        gTotalDays %= 1461
        if (gTotalDays > 365) {
            jy += ((gTotalDays - 1) / 365)
            gTotalDays = (gTotalDays - 1) % 365
        }
        val jm: Int
        val jd: Int
        if (gTotalDays < 186) {
            jm = 1 + (gTotalDays / 31)
            jd = 1 + (gTotalDays % 31)
        } else {
            jm = 7 + ((gTotalDays - 186) / 30)
            jd = 1 + ((gTotalDays - 186) % 30)
        }
        return "$jy-$jm-$jd"
    }

    fun jalaliToGregorian(jy: Int, jm: Int, jd: Int): String {

        if (jy == 0 || jm == 0 || jd == 0) {
            return ""
        }

        val jy1: Int = jy + 1595
        var days: Int =
            -355668 + (365 * jy1) + ((jy1 / 33) * 8) + (((jy1 % 33) + 3) / 4) + jd + (if (jm < 7) ((jm - 1) * 31) else (((jm - 7) * 30) + 186))
        var gy: Int = 400 * (days / 146097)
        days %= 146097
        if (days > 36524) {
            gy += 100 * (--days / 36524)
            days %= 36524
            if (days >= 365) days++
        }
        gy += 4 * (days / 1461)
        days %= 1461
        if (days > 365) {
            gy += ((days - 1) / 365)
            days = (days - 1) % 365
        }
        var gd: Int = days + 1
        val sal_a: IntArray = intArrayOf(
            0,
            31,
            if ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0)) 29 else 28,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30,
            31
        )
        var gm: Int = 0
        while (gm < 13 && gd > sal_a[gm]) gd -= sal_a[gm++]
        return "$gy-$gm-$gd"
    }

    fun createCalendarWithDateTime(year: Int, month: Int, day: Int, timeString: String): Calendar {

        // Step 1: Parse the time string
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val time = timeFormat.parse(timeString)

        // Step 2: Create a Calendar instance and set the year, month, and day
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)

        // Note: Month value in Calendar is 0-based (January is 0), so subtract 1 from the month value
        calendar.set(Calendar.MONTH, month - 1)

        calendar.set(Calendar.DAY_OF_MONTH, day)

        if (time != null) {
            // Step 3: Set the hour, minute, and second
            val timeCalendar = Calendar.getInstance()
            timeCalendar.time = time

            calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY))
            calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE))
            calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND))
        }

        return calendar
    }

}