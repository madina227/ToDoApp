package uz.gita.todoapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.todoapp.R
import uz.gita.todomockexam.ui.adapters.ToDoAdapter


class MainScreen : Fragment(R.layout.screen_todo) {
    private lateinit var tabLayout: TabLayout
    private lateinit var pager2: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tabLayout = view.findViewById(R.id.tabLay)
        pager2 = view.findViewById(R.id.pagerToDo)

//adapteri shu joyda elon qipketish kerekan xatoli bermasligi uchun
        pager2.adapter = ToDoAdapter(this)
        TabLayoutMediator(tabLayout, pager2) { tab, pos ->
            when (pos) {
                0 -> tab.text = "To Do"
                1 -> tab.text = "Doing"
                2 -> tab.text = "Done"
            }
        }.attach()
    }
}