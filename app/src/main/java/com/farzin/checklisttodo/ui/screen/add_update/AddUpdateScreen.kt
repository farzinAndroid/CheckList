package com.farzin.checklisttodo.ui.screen.add_update

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.model.home.Subtask
import com.farzin.checklisttodo.model.home.Task
import com.farzin.checklisttodo.ui.components.MySpacerHeight
import com.farzin.checklisttodo.ui.theme.mainBackground
import com.farzin.checklisttodo.utils.DateHelper
import com.farzin.checklisttodo.utils.DigitHelper
import com.farzin.checklisttodo.viewModel.DataStoreViewModel
import com.farzin.checklisttodo.viewModel.TaskViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddUpdateScreen(
    taskId: Int,
    lastTaskId: Int,
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var singleTask by remember { mutableStateOf(Task()) }
    if (taskId != 0) {
        LaunchedEffect(taskId) {
            taskViewModel.getSingleTask(taskId)

            taskViewModel.singleTask.collectLatest { task ->
                singleTask = task
            }
        }
    }


    val titleText =
        if (taskId == 0) stringResource(R.string.create_task) else stringResource(R.string.edit_task)

    var taskTitleText by remember { mutableStateOf("") }
    singleTask.let {
        taskTitleText = it.title
    }
    var taskDescriptionText by remember { mutableStateOf("") }
    singleTask.let {
        taskDescriptionText = it.description
    }
    var taskDueTimeText by remember { mutableStateOf("") }
    singleTask.let {
        taskDueTimeText = it.dueTime
    }
    var taskDueYearText by remember { mutableIntStateOf(0) }
    singleTask.let {
        taskDueYearText = it.dueYear
    }
    var taskDueMonthText by remember { mutableIntStateOf(0) }
    singleTask.let {
        taskDueMonthText = it.dueMonth
    }
    var taskDueDayText by remember { mutableIntStateOf(0) }
    singleTask.let {
        taskDueDayText = it.dueDay
    }
    var priorityNumber by remember { mutableIntStateOf(-1) }
    singleTask.let {
        priorityNumber = it.priority
    }
    var subTasks by remember { mutableStateOf(emptyList<Subtask>()) }
    singleTask.let {
        subTasks = it.subTask
    }

    var wholePersianDate by remember { mutableStateOf("") }


    val clockState = rememberUseCaseState()

    TimePickerComposable(
        onTimePicked = { hour, minute, second ->
            taskDueTimeText = "$hour:$minute:$second"
        },
        clockState = clockState
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackground),
    ) {

        stickyHeader {
            AddUpdateTopBar(
                titleText = titleText,
                onClick = { navController.popBackStack() })
        }

        item { MySpacerHeight(height = 12.dp) }

        item {
            TaskTitleSection(
                textValue = taskTitleText,
                onTextValueChanged = {
                    taskTitleText = it
                },
                title = stringResource(R.string.task_title),
                onClick = {}
            )
        }

        val persianDate = DateHelper.splitWholeDate(
            DateHelper.gregorianToJalali(
                taskDueYearText,
                taskDueMonthText,
                taskDueDayText
            )
        )
        val persianYear = persianDate[0]
        val persianMonth = persianDate[1]
        val persianDay = persianDate[2]

        wholePersianDate = if (persianYear != 0 && persianMonth != 0 && persianDay != 0) {
            DigitHelper.digitByLang("$persianDay/$persianMonth/$persianYear")
        } else {
            ""
        }
        item {
            TaskTitleSection(
                textValue = wholePersianDate,
                onTextValueChanged = {},
                title = stringResource(R.string.due_date),
                icon = painterResource(R.drawable.calendar),
                onClick = {
                    datePicker(context) { year, month, day ->
                        taskDueYearText = year
                        taskDueMonthText = month
                        taskDueDayText = day
                    }
                }
            )
        }

        item {
            TaskTitleSection(
                textValue = DigitHelper.digitByLang(taskDueTimeText),
                onTextValueChanged = {},
                title = stringResource(R.string.due_time),
                icon = painterResource(R.drawable.clock),
                onClick = {
                    clockState.show()
                }
            )
        }

        item {
            PrioritySection(
                priorityNumber = if (taskId != 0) priorityNumber else -1,
                priorityCallback = {
                    priorityNumber = it
                }
            )
        }


        item {
            SubTaskSection(
                subtaskFromOutside = if (taskId != 0) subTasks else emptyList(),
                subtaskCallback = {
                    subTasks = it
                }
            )
        }

        item {
            DescriptionSection(
                textValue = taskDescriptionText,
                onTextValueChanged = {
                    taskDescriptionText = it
                },
                icon = null,
                title = stringResource(R.string.description),
                onClick = {}
            )
        }

        if (taskId == 0) {
            item {
                AddTaskButtonSection(
                    onAddClicked = {
                        if (taskTitleText.isEmpty() || taskDueTimeText.isEmpty() ||
                            priorityNumber == -1 || subTasks.isEmpty()
                        ) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_fill_out_everything),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {

                            val newTask = Task(
                                title = taskTitleText,
                                description = taskDescriptionText,
                                dueTime = taskDueTimeText,
                                subTask = subTasks,
                                priority = priorityNumber,
                                dueYear = taskDueYearText,
                                dueMonth = taskDueMonthText,
                                dueDay = taskDueDayText
                            )

                            scope.launch {
                                taskViewModel.insertTask(newTask)
                            }


                            val currentTimeForNotif = DateHelper.createCalendarWithDateTime(
                                year = taskDueYearText,
                                month = taskDueMonthText,
                                day = taskDueDayText,
                                timeString = taskDueTimeText
                            ).timeInMillis

                            val currentTime = Calendar.getInstance().timeInMillis

                            val isTaskComplete = !subTasks.any { !it.subtaskCompleted }


                            if (currentTimeForNotif > currentTime && !isTaskComplete) {
                                // Schedule the notification for the task
                                scope.launch {
                                    val calendarTime = DateHelper.createCalendarWithDateTime(
                                        year = newTask.dueYear,
                                        month = newTask.dueMonth,
                                        day = newTask.dueDay,
                                        timeString = newTask.dueTime
                                    )

                                    taskViewModel.scheduleNotification(
                                        context = context,
                                        triggerTime = calendarTime,
                                        taskId = lastTaskId + 1,
                                        task = newTask
                                    )
                                }
                            } else {
                                scope.launch {
                                    taskViewModel.cancelNotification(context, lastTaskId + 1)
                                }
                            }

                            scope.launch {
                                delay(300)
                                navController.popBackStack()
                            }


                        }

                    }
                )
            }
        } else {
            item {
                UpdateTaskButtonSection(
                    onEditClicked = {

                        if (taskTitleText.isEmpty() || taskDueTimeText.isEmpty() ||
                            priorityNumber == -1 || subTasks.isEmpty()
                        ) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_fill_out_everything),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {

                            val updatedTask = Task(
                                title = taskTitleText,
                                description = taskDescriptionText,
                                dueTime = taskDueTimeText,
                                subTask = subTasks,
                                priority = priorityNumber,
                                taskId = singleTask.taskId,
                                dueYear = taskDueYearText,
                                dueMonth = taskDueMonthText,
                                dueDay = taskDueDayText
                            )


                            scope.launch {
                                taskViewModel.updateTask(updatedTask)
                            }


                            val currentTimeForNotif = DateHelper.createCalendarWithDateTime(
                                year = taskDueYearText,
                                month = taskDueMonthText,
                                day = taskDueDayText,
                                timeString = taskDueTimeText
                            ).timeInMillis


                            val currentTime = Calendar.getInstance().timeInMillis

                            val isTaskComplete = !subTasks.any { !it.subtaskCompleted }

                            if (currentTimeForNotif > currentTime && !isTaskComplete) {
                                // Schedule the notification for the task
                                scope.launch {
                                    val calendarTime = DateHelper.createCalendarWithDateTime(
                                        year = updatedTask.dueYear,
                                        month = updatedTask.dueMonth,
                                        day = updatedTask.dueDay,
                                        timeString = updatedTask.dueTime
                                    )

                                    taskViewModel.scheduleNotification(
                                        context = context,
                                        triggerTime = calendarTime,
                                        taskId = updatedTask.taskId,
                                        task = updatedTask
                                    )
                                }
                            } else {
                                scope.launch {
                                    taskViewModel.cancelNotification(context, updatedTask.taskId)
                                }
                            }



                            scope.launch {
                                delay(300)
                                navController.popBackStack()
                            }


                        }


                    }
                )
            }
        }


    }


}