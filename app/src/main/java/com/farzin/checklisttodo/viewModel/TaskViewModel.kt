package com.farzin.checklisttodo.viewModel

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.checklisttodo.model.home.Task
import com.farzin.checklisttodo.repo.TaskDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
@Inject constructor(
    private val repo:TaskDBRepository,
    private val alarmManager: AlarmManager,
    private val alarmIntent: Intent
) : ViewModel() {

    val allTasks = repo.allTasks

    val tasksBasedOnPriorityDesc = repo.sortedTasksPriorityDesc()
    val tasksBasedOnPriorityAsc = repo.sortedTasksPriorityAsc()

    fun insertTask(task: Task){
        viewModelScope.launch {
            repo.insertTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTask(task)
        }
    }



    var singleTask = MutableStateFlow(Task())

    fun getSingleTask(taskId:Int){
            viewModelScope.launch {
                repo.getSingleTask(taskId).collectLatest {task->
                    singleTask.emit(task)
                }
            }
    }


    val searchedTasks = MutableStateFlow<List<Task>>(emptyList())

    fun searchTasks(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            searchedTasks.emit(repo.searchTasks(query))
        }
    }

    //first state whether the search is happening or not
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }



    @SuppressLint("ScheduleExactAlarm")
    fun scheduleNotification(context: Context, triggerTime: Calendar, taskId: Int,task:Task) {


        alarmIntent.putExtra("TASK_ID", taskId) // Pass task ID or other info to the receiver
        alarmIntent.putExtra("TASK_TITLE",task.title) // pass title of task

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskId, // Unique request code, use task ID for simplicity
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime.timeInMillis,
            pendingIntent
        )
    }


    fun cancelNotification(context: Context, taskId: Int){

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskId, // Unique request code, use task ID for simplicity
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
    }


    fun vibrate(context: Context){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(200) // Vibrate method for below API Level 26
            }
        }
    }

}