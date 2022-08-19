package uz.gita.todoapp.viewModel

import androidx.lifecycle.LiveData

interface IntroVm {
    val setNextLiveData: LiveData<Unit>

    fun openNextScreen(pos: Int)

}