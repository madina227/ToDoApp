package uz.gita.mockexamtodo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tapadoo.alerter.Alerter
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todomockexam.ui.adapters.DoingPageAdapter
import uz.gita.todomockexam.viewModel.impl.ToDoViewModelImpl

class DoingPage : Fragment(R.layout.page_doing) {
    private val viewModel: ToDoViewModelImpl by viewModels()
    private val adapter: DoingPageAdapter by lazy { DoingPageAdapter() }
    private lateinit var container: RecyclerView
    private lateinit var emptyHolder: View
    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.editScreen.observe(this) {
            val bundle = Bundle()
            bundle.putInt("ID", it)
            navController.navigate(R.id.action_mainScreen_to_editScreen, bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.container_doing)
        container.adapter = adapter

        emptyHolder = view.findViewById(R.id.emptyPlaceholderDoing)

        viewModel.getAllTodos(1)

        viewModel.allToDoes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                emptyHolder.visibility = View.VISIBLE
            } else {
                emptyHolder.visibility = View.GONE
            }
            adapter.submitList(it)
        }

        adapter.editClickListener {
            viewModel.triggerEditScreen(it.id)
        }

        adapter.deleteItemClickListenere { data ->
            viewModel.update(
                ToDoEntity(
                    data.id,
                    data.title,
                    data.content,
                    data.date,
                    data.state,
                    true,false
                )
            )
            Snackbar.make(container, data.title, Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    viewModel.update(
                        ToDoEntity(
                            data.id,
                            data.title,
                            data.content,
                            data.date,
                            data.state,
                            false, false
                        )
                    )
                }.show()
        }

        val touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPosition = viewHolder.adapterPosition
                    val data = adapter.getItem(swipedPosition)
                    viewModel.update(
                        ToDoEntity(
                            data.id,
                            data.title,
                            data.content,
                            data.date,
                            2,
                            false, false
                        )
                    )

                    Alerter.create(requireActivity())
                        .setTitle("${data.title}")
                        .setText("${data.content}")
                        .show()
                }
            })
        touchHelper.attachToRecyclerView(container)


    }
}