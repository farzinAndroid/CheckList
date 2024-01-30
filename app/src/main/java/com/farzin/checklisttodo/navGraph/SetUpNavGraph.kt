package com.farzin.checklisttodo.navGraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.farzin.checklisttodo.ui.screen.add_update.AddUpdateScreen
import com.farzin.checklisttodo.ui.screen.home.HomeScreen
import com.farzin.checklisttodo.ui.screen.search.SearchScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
) {


    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
    ) {

        composable(Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(
            Screens.AddUpdateScreen.route + "?taskId={taskId}?lastTaskId={lastTaskId}",
            arguments = listOf(
                navArgument("taskId"){
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("lastTaskId"){
                    type = NavType.IntType
                    defaultValue = 0
                },
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            }
            )
        {

            it.arguments?.getInt("taskId")?.let { taskId ->
                it.arguments?.getInt("lastTaskId")?.let { lastTaskId ->
                    AddUpdateScreen(
                        taskId = taskId,
                        lastTaskId = lastTaskId,
                        navController = navController,

                    )
                }
            }

        }


        composable(Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

    }

}