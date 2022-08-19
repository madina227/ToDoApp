package uz.gita.todoapp.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.viewModel.EditViewModel

class EditScreen : Fragment(R.layout.screen_update) {
    private val viewModel: EditViewModel by viewModels()

    private val navController: NavController by lazy { findNavController() }

    private lateinit var editTitle: EditText
    private lateinit var editDescription: EditText
    private lateinit var editDate: EditText
    private lateinit var iv_calendar: ImageView
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setUpdateLiveData.observe(this) {
            navController.navigateUp()
        }
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//          OnBackPressedCallback
//        }
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTitle = view.findViewById(R.id.edit_title)
        editDescription = view.findViewById(R.id.edit_description)
        editDate = view.findViewById(R.id.edit_date)
        iv_calendar = view.findViewById(R.id.calendar_update)
        btnUpdate = view.findViewById(R.id.btn_update_todo)
        val id = arguments?.getInt("ID")!!
        val item = viewModel.getItem(id)

        editTitle.setText(item.title)
        editDescription.setText(item.content)
        editDate.setText(item.date)



        btnUpdate.setOnClickListener {
            val title = editTitle.text.toString()
            val description = editDescription.text.toString()
            val date = editDate.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty()) {
                viewModel.update(ToDoEntity(id, title, description, date, item.state, false, false))
            }
        }
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)


        iv_calendar.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, myear, mMonth, mDay ->
                    editDate.setText("" + mDay + "/" + mMonth + "/" + myear)
                },
                y,
                m,
                d
            )
            dpd.show()

        }
    }
}