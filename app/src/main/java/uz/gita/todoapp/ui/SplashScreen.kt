package uz.gita.todoapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.gita.todoapp.R
import uz.gita.todoapp.viewModel.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openIntroScreen.observe(this) {
            navController.navigate(R.id.action_splashScreen_to_introScreen)
        }

        viewModel.openMainScreen.observe(this){
            navController.navigate(R.id.action_splashScreen_to_mainScreen)
        }
    }

}