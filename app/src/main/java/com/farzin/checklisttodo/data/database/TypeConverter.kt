package com.farzin.checklisttodo.data.database

import androidx.room.TypeConverter
import com.farzin.checklisttodo.model.home.Subtask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TypeConverter {

    @TypeConverter
    fun fromSubtaskList(subtaskList: List<Subtask>): String {
        val json = Gson().toJson(subtaskList)
        return json
    }

    @TypeConverter
    fun toSubtaskList(jsonString: String): List<Subtask> {
        val subtaskList =
            Gson().fromJson<List<Subtask>>(
                jsonString,
                object : TypeToken<List<Subtask>>() {}.type)
        return subtaskList
    }
}