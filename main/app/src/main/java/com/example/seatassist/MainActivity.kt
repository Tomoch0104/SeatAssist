package com.example.seatassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seatassist.ui.main.MainScreen
import com.example.seatassist.ui.main.MainViewModel
import com.example.seatassist.ui.members.MembersScreen
import com.example.seatassist.ui.size.SizeScreen
import com.example.seatassist.ui.theme.SeatAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeatAssistApp()
        }
    }
}

@Composable
fun SeatAssistApp() {
    val navController = rememberNavController()
    SeatAssistTheme {
        SeatAssistNavHost(
            navController = navController,
            modifier = Modifier
        )
    }
}

@Composable
fun SeatAssistNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SeatAssistScreen.Main.name,
        modifier = modifier
    ) {
        composable(route = SeatAssistScreen.Main.name) {
            // Main Compose
            MainScreen(
                onMembersClick = { navController.navigate(SeatAssistScreen.Members.name) },
                onSizeClick = { navController.navigate(SeatAssistScreen.Size.name) }
            )
        }
        composable(route = SeatAssistScreen.Members.name) {
            // Members Compose
            MembersScreen()
        }
        composable(route = SeatAssistScreen.Size.name) {
            // Size Compose
            SizeScreen()
        }
    }
}