package com.farzin.checklisttodo.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.model.home.Task
import com.farzin.checklisttodo.navGraph.Screens
import com.farzin.checklisttodo.ui.screen.home.TaskItem
import com.farzin.checklisttodo.ui.theme.mainBackground
import com.farzin.checklisttodo.ui.theme.searchBackground
import com.farzin.checklisttodo.ui.theme.softgray
import com.farzin.checklisttodo.viewModel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
) {


    val scope = rememberCoroutineScope()

    //Collecting states from ViewModel
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val isSearching by taskViewModel.isSearching.collectAsState()
    var searchedTasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    LaunchedEffect(true) {
        taskViewModel.searchedTasks.collectLatest {
            searchedTasks = it
        }
    }


    if (!isSearching) {
        searchedTasks = emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SearchBar(
            query = searchQuery,//text showed on SearchBar
            onQueryChange = {
                searchQuery = it
                if (it.isNotEmpty() && it.isNotBlank()) {
                    taskViewModel.searchTasks(searchQuery)
                }else{
                    searchedTasks = emptyList()
                }
            },//update the value of searchText
            onSearch = { taskViewModel.searchTasks(it) },//the callback to be invoked when the input service triggers the ImeAction.Search action
            active = isSearching,//whether the user is searching or not
            onActiveChange = { taskViewModel.onToggleSearch() },//the callback to be invoked when this search bar's active state is changed
            shape = Shapes().large,
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 1.dp,
            colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.searchBackground),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.softgray
                )
            },
            trailingIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(searchedTasks) {
                    TaskItem(task = it, modifier = Modifier.animateItemPlacement()) {
                        taskViewModel.onToggleSearch()
                        navController.navigate(Screens.AddUpdateScreen.route + "?taskId=${it.taskId}?lastTaskId=${0}")
                    }
                }
            }
        }
    }
}