package com.farzin.checklisttodo.navGraph

sealed class Screens(val route:String){

    data object HomeScreen : Screens(route = "home_screen")
    data object AddUpdateScreen : Screens(route = "add_update_screen")
    data object SearchScreen : Screens(route = "search_screen")

}
