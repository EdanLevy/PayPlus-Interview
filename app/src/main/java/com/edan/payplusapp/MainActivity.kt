package com.edan.payplusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edan.payplusapp.Screens.BillingDetailScreen
import com.edan.payplusapp.Screens.BillingListScreen
import com.edan.payplusapp.ui.theme.PayPlusAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var billingViewModel: BillingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            billingViewModel = viewModel()
            PayPlusAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        Navigation(billingViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(viewModel: BillingViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "billingListScreen") {
        composable("billingListScreen") {
            BillingListScreen(viewModel) {
                viewModel.onHeaderClicked(it)
                navController.navigate("billingDetailScreen")
            }
        }
        composable("billingDetailScreen") {
            BillingDetailScreen(viewModel)
        }
    }
}
