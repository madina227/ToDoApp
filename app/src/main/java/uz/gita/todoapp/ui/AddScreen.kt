package uz.gita.todoapp.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.viewModel.AddViewModel

class AddScreen : Fragment(R.layout.screen_add) {

    private val viewModel: AddViewModel by viewModels()

    private val navController: NavController by lazy { findNavController() }

    private lateinit var inputTitle: EditText
    private lateinit var inputDescription: EditText
    private lateinit var inputDate: EditText
    private lateinit var iv_calendar: ImageView
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setSaveLivedata.observe(this) {
            navController.navigateUp()
        }
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputTitle = view.findViewById(R.id.input_title)
        inputDescription = view.findViewById(R.id.input_description)
        inputDate = view.findViewById(R.id.input_date)
        iv_calendar = view.findViewById(R.id.calendar)
        btnSave = view.findViewById(R.id.btn_save_todo)


//        val c = Calendar.getInstance()
//        val y = c.get(Calendar.YEAR)
//        val m = c.get(Calendar.MONTH)
//        val d = c.get(Calendar.DAY_OF_MONTH)


        iv_calendar.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    inputDate.setText("$mDay/$mMonth/$mYear")
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
        btnSave.setOnClickListener {
            val title = inputTitle.text.toString()
            val description = inputDescription.text.toString()
            val date = inputDate.text.toString()

            if (title.length in 6..20 && description.length > 20 && date.isNotEmpty()) {
                viewModel.insert(ToDoEntity(0, title, description, date, 0, false))
            } else Toast.makeText(requireContext(), "hammasini to'ldiring", Toast.LENGTH_SHORT)
                .show()
        }
    }
}


