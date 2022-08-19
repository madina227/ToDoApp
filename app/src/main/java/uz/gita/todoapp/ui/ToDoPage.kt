package uz.gita.mockexamtodo.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todomockexam.ui.adapters.ToDoPageAdapter
import uz.gita.todomockexam.viewModel.impl.ToDoViewModelImpl

class ToDoPage : Fragment(R.layout.page_todo) {
    private lateinit var btnAdd: Button
    private val viewModel: ToDoViewModelImpl by viewModels()
    private val adapter: ToDoPageAdapter by lazy { ToDoPageAdapter() }
    private lateinit var container: RecyclerView
    private lateinit var emptyPlace: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.addScreen.observe(this) {
            findNavController().navigate(R.id.action_mainScreen_to_addScreen)
        }
        viewModel.editScreen.observe(this) {
            val bundle = Bundle()
            bundle.putInt("ID", it)
            findNavController().navigate(R.id.action_mainScreen_to_editScreen, bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.container_todo)
        container.adapter = adapter

        emptyPlace = view.findViewById(R.id.emptyPlaceholder)

        viewModel.getAllTodos(0)

        btnAdd = view.findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            viewModel.triggerAddScreen()
        }

        viewModel.allToDoes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                emptyPlace.visibility = View.VISIBLE
            } else {
                emptyPlace.visibility = View.GONE
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


            /*
                Snackbar.make(conteiner, data.course, Snackbar.LENGTH_SHORT)
                    .setAction("UNDO") {
                        presenter.undoRemove()
                    }
                    .show()


*/
            //    viewModel.delete(it)
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
                            1,
                            false
                        )
                    )
                }
            })
        touchHelper.attachToRecyclerView(container)

    }
}