package com.farzin.checklisttodo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.farzin.checklisttodo.navGraph.SetUpNavGraph
import com.farzin.checklisttodo.ui.components.ChangeStatusBarColor
import com.farzin.checklisttodo.ui.theme.CheckListTheme
import com.farzin.checklisttodo.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CheckListTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                LocaleUtils.setLocale(this, "fa")

                ChangeStatusBarColor()

                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Rtl)) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                            .statusBarsPadding(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            content = {
                                checkPermissions()

                                SetUpNavGraph(navController = navController)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun checkPermissions() {
        // Check for both notifications and alarms on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SCHEDULE_EXACT_ALARM
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS,
                        Manifest.permission.SCHEDULE_EXACT_ALARM
                    ),
                    1
                )
            }
        } else {
            // For Android 12 or below, only check for alarms
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SET_ALARM
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SET_ALARM), 1)
            }
        }
    }

}
