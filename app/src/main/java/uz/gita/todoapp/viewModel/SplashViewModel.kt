package uz.gita.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.todoapp.appDatabase.ShP

class SplashViewModel : ViewModel(), SplashVM {
    val openNextScreen = MutableLiveData<Unit>()
    override val openMainScreen = MutableLiveData<Unit>()
    override val openIntroScreen = MutableLiveData<Unit>()
    private val shP = ShP.getInstance()

    init {
        viewModelScope.launch {
            delay(2000)
            if (shP.getState()) {
                openIntroScreen.value = Unit
                shP.setState(false)
                shP.getState()
            } else
                openMainScreen.value = Unit
        }
    }


}
