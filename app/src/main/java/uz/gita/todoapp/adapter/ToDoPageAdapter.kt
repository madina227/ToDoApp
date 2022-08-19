package uz.gita.todomockexam.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity


class ToDoPageAdapter : RecyclerView.Adapter<ToDoPageAdapter.VH>() {
    private val oldList: ArrayList<ToDoEntity> = ArrayList()
    private var itemClickListener: ((ToDoEntity) -> Unit)? = null
//    private var itemLongClickListener: ((Int) -> Unit)? = null
    private var deleteClickListener: ((ToDoEntity) -> Unit)? = null
    private var editClickListener: ((ToDoEntity) -> Unit)? = null

//    private var itemSelectedStateChangeListener: ((ToDoEntity) -> Unit)? = null
//
//    fun setItemSelectedStateChangeListener(block: (ToDoEntity) -> Unit) {
//        itemSelectedStateChangeListener = block
//    }
//
//    fun setItemClickListener(block: (ToDoEntity) -> Unit) {
//        itemClickListener = block
//    }
//
//
//    private var itemLongClickListener: ((Unit) -> Unit)? = null
//
//
//    fun setItemLongClickListener(block: (Unit) -> Unit) {
//        itemLongClickListener = block
//    }

    var isSelected = false


    fun submitList(newList: List<ToDoEntity>) {
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    fun getItem(id: Int): ToDoEntity {
        return oldList[id]
    }


    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.tvTitlepageTodo)
        private val time = view.findViewById<TextView>(R.id.tvTimePageTodo)
        private val delete = view.findViewById<ImageView>(R.id.delete)
        private val edit = view.findViewById<ImageView>(R.id.edit)
//        private val checkbox = view.findViewById<ImageButton>(R.id.checkbox)
//        private val bosilish = view.findViewById<View>(R.id.bosilish)

        fun bind() {
            val item = oldList[adapterPosition]
            title.text = item.title
            time.text = item.date
//            if (isSelected) {
//                checkbox.visibility = View.VISIBLE
//                if (getItem(adapterPosition).isChecked) {
//                    checkbox.setImageResource(R.drawable.ic_baseline_check_circle_24)
//                } else {
//                    checkbox.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
//                }
//            } else
//                checkbox.visibility = View.GONE
        }

        init {
//            bosilish.setOnClickListener {
//                if (!isSelected)
//                    itemClickListener?.invoke(getItem(adapterPosition))
//            }
//
//            bosilish.setOnLongClickListener {
////                itemLongClickListener?.invoke(Unit)
//                true
//            }

            delete.setOnClickListener {
                deleteClickListener?.invoke(oldList[adapterPosition])
            }

            edit.setOnClickListener {
                editClickListener?.invoke(oldList[adapterPosition])
            }

//            checkbox.setOnClickListener {
//                if (getItem(adapterPosition).isChecked) {
//                    checkbox.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
//                } else {
//                    checkbox.setImageResource(R.drawable.ic_baseline_check_circle_24)
//                }
//                getItem(adapterPosition).isChecked = !getItem(adapterPosition).isChecked
//                itemSelectedStateChangeListener?.invoke(getItem(adapterPosition))
//            }

        }
    }
    fun setSelectedState(state: Boolean) {
        isSelected = state
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_page_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun deleteItemClickListenere(block: (ToDoEntity) -> Unit) {
        deleteClickListener = block
    }

    fun editClickListener(block: (ToDoEntity) -> Unit) {
        editClickListener = block
    }
}