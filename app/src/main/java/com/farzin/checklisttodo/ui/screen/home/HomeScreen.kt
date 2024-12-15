package com.farzin.checklisttodo.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.navGraph.Screens
import com.farzin.checklisttodo.ui.components.AddButton
import com.farzin.checklisttodo.ui.components.MyDividerHorizontal
import com.farzin.checklisttodo.ui.theme.mainBackground
import com.farzin.checklisttodo.viewModel.DataStoreViewModel
import com.farzin.checklisttodo.viewModel.TaskViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {

    Home(taskViewModel, navController, dataStoreViewModel)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(
    taskViewModel: TaskViewModel,
    navController: NavController,
    dataStoreViewModel: DataStoreViewModel,
) {

    val profileList = listOf(
        R.drawable.man_1,
        R.drawable.man_2,
        R.drawable.man_3,
        R.drawable.woman_1,
        R.drawable.woman_2,
        R.drawable.woman_3,
    )

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showProfileDialog by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }


    val tasks by taskViewModel.allTasks.collectAsState(emptyList())
    var lastInsertedTaskId by remember { mutableIntStateOf(0) }
    LaunchedEffect(tasks) {
        if (tasks.isNotEmpty()) {
            lastInsertedTaskId = tasks.first().taskId
        }
    }


    var priority by remember { mutableIntStateOf(0) }
    val tasksBasedOnPriorityDesc by taskViewModel.tasksBasedOnPriorityDesc.collectAsState(emptyList())
    val tasksBasedOnPriorityAsc by taskViewModel.tasksBasedOnPriorityAsc.collectAsState(emptyList())
    LaunchedEffect(dataStoreViewModel.getSort()) {
        priority = dataStoreViewModel.getSort()
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.mainBackground),
        ) {

            TopBarSection(
                onSearchClicked = {
                    navController.navigate(Screens.SearchScreen.route)
                },
                onImageClicked = {
                    showProfileDialog = true
                },
                profile = dataStoreViewModel.getProfile(),
                onFilterClicked = {
                    showSortDialog = true
                }
            )
            MyDividerHorizontal()

            if (showProfileDialog) {
                Dialog(onDismissRequest = { showProfileDialog = false }) {
                    ProfileDialogContent(
                        profileList = profileList,
                        onProfileClicked = { profile ->
                            scope.launch {
                                dataStoreViewModel.saveProfile(profile)
                                delay(50)
                                showProfileDialog = false
                            }

                        }
                    )
                }
            }

            if (showSortDialog) {
                Dialog(onDismissRequest = { showSortDialog = false }) {
                    SortDialogContent()
                }
            }

            when (priority) {
                1 -> {
                    InitLazyColumn(
                        tasks = tasksBasedOnPriorityAsc,
                        navController = navController,
                    )
                }

                2 -> {
                    InitLazyColumn(
                        tasks = tasksBasedOnPriorityDesc,
                        navController = navController,
                    )
                }

                3 -> {
                    InitLazyColumn(
                        tasks = tasks,
                        navController = navController,
                    )
                }

            }
        }



        AddButton(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .height(40.dp)
                .width(200.dp),
            onClick = {
                navController.navigate(Screens.AddUpdateScreen.route + "?taskId=${0}?lastTaskId=${lastInsertedTaskId}")
            }
        )

    }


}