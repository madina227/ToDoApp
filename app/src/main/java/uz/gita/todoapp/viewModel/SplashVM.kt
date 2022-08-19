package uz.gita.todoapp.viewModel

import androidx.lifecycle.LiveData

interface SplashVM {
    val openMainScreen: LiveData<Unit>
    val openIntroScreen: LiveData<Unit>

}