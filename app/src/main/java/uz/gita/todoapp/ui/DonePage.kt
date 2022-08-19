package uz.gita.mockexamtodo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todomockexam.ui.adapters.DonePageAdapter
import uz.gita.todomockexam.viewModel.impl.ToDoViewModelImpl

class DonePage : Fragment(R.layout.page_done) {
    private lateinit var container: RecyclerView
    private lateinit var emptyPlace: View
    private val viewModel: ToDoViewModelImpl by viewModels()
    private val adapter: DonePageAdapter by lazy { DonePageAdapter() }
    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.editScreen.observe(this) {
            val bundle = Bundle()
            bundle.putInt("ID", id)
            navController.navigate(R.id.action_mainScreen_to_editScreen, bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.container_done)
        container.adapter = adapter

        emptyPlace = view.findViewById(R.id.emptyPlaceholderDone)

        viewModel.allToDoes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                emptyPlace.visibility = View.VISIBLE
            } else {
                emptyPlace.visibility = View.GONE
            }
            adapter.submitList(it)
        }

        viewModel.getAllTodos(2)

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
                    true
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
                            false
                        )
                    )
                }.show()
        }
    }

}