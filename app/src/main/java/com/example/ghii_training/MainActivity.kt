package com.example.ghii_training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ghii_training.domain.model.user.User
import com.example.ghii_training.ui.Screen
import com.example.ghii_training.ui.fragments.DetailsFragment
import com.example.ghii_training.ui.fragments.MainFragment
import com.example.ghii_training.ui.fragments.MainViewModel
import com.example.ghii_training.ui.theme.GhiiTrainingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        mainViewModel.viewModelScope.launch {
//            mainViewModel.query.collect {
//                it.forEach { user ->
//                    println(user)
//                }
//            }
//        }
        setContent {
            GhiiTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost()
                }
            }
        }
    }
}
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.MainScreen.route,
    //users: List<User>
) {
   NavHost(
       navController = navController,
       startDestination = startDestination,
       modifier = modifier
   ) {

       composable(route = Screen.MainScreen.route) {
           MainFragment(navController)
       }
       composable(
           route = Screen.DetailScreen.route + "/{id}",
           arguments = listOf(
               navArgument("id"){
                    defaultValue = 1
                    type = NavType.IntType
                    nullable = false
               }
           )
       ) {
           DetailsFragment(it.arguments?.getInt("id")!!)
       }
   }
}
