package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklisttodo.model.home.Task
import com.farzin.checklisttodo.navGraph.Screens
import com.farzin.checklisttodo.ui.components.EmptyTask
import com.farzin.checklisttodo.ui.components.noTaskPainterResource
import com.farzin.checklisttodo.ui.theme.darkText
import com.farzin.checklisttodo.ui.theme.highPriority
import com.farzin.checklisttodo.ui.theme.lowPriority
import com.farzin.checklisttodo.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InitLazyColumn(
    tasks: List<Task>,
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    if (tasks.isEmpty()){
        EmptyTask(pic = noTaskPainterResource())
    }else{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberLazyListState(),
        ) {
            items(tasks, key = { it.taskId }) { task ->

                val swipeToDismissState = rememberDismissState(
                    confirmValueChange = { it ->
                        if (it == DismissValue.DismissedToStart) {
                            val updatedTask = task.copy(subTask = task.subTask.map {
                                it.copy(subtaskCompleted = true)
                            })
                            taskViewModel.updateTask(updatedTask)
                            taskViewModel.cancelNotification(context,task.taskId)
                            taskViewModel.vibrate(context)

                            return@rememberDismissState false // Prevent dismissal

                        }

                        if (it == DismissValue.DismissedToEnd) {
                            taskViewModel.deleteTask(task)
                            taskViewModel.cancelNotification(context,task.taskId)
                        }
                        true
                    }
                )


                SwipeToDismiss(
                    state = swipeToDismissState,
                    background = {
                        val color = when (swipeToDismissState.dismissDirection) {
                            DismissDirection.EndToStart -> {
                                MaterialTheme.colorScheme.lowPriority
                            }

                            DismissDirection.StartToEnd -> {
                                MaterialTheme.colorScheme.highPriority
                            }

                            else -> {
                                Color.Transparent
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(vertical = 18.dp)
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(horizontal = 16.dp)
                                .shadow(
                                    6.dp,
                                    shape = Shapes().extraLarge,
                                    spotColor = MaterialTheme.colorScheme.darkText
                                )
                                .background(color),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 8.dp)
                            )


                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 8.dp)
                            )
                        }
                    },
                    dismissContent = {

                        TaskItem(
                            task = task,
                            onCardClicked = {
                                navController.navigate(Screens.AddUpdateScreen.route + "?taskId=${task.taskId}?lastTaskId=${0}")
                            },
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                )
            }
        }
    }




}