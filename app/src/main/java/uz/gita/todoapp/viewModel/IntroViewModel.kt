package uz.gita.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroViewModel : IntroVm, ViewModel() {

    override val setNextLiveData = MutableLiveData<Unit>()

    override fun openNextScreen(pos: Int) {
        if (pos < 2){
            pos+1
        }else setNextLiveData.value = Unit
    }
}