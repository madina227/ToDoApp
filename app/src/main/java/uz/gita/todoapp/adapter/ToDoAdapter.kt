package uz.gita.todomockexam.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.mockexamtodo.ui.DoingPage
import uz.gita.mockexamtodo.ui.DonePage
import uz.gita.mockexamtodo.ui.ToDoPage


class ToDoAdapter(fa:Fragment):FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0-> ToDoPage()
            1-> DoingPage()
            else-> DonePage()
        }
    }
}