package uz.gita.todomockexam.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.todoapp.R
import uz.gita.todoapp.entity.ToDoEntity


class DoingPageAdapter : RecyclerView.Adapter<DoingPageAdapter.VH>() {
    private val oldList: ArrayList<ToDoEntity> = ArrayList()
    private var itemClickListener: ((Int) -> Unit)? = null
    private var deleteClickListener: ((ToDoEntity) -> Unit)? = null
    private var editClickListener: ((ToDoEntity) -> Unit)? = null

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

        fun bind() {
            val item = oldList[adapterPosition]
            title.text = item.title
            time.text = item.date
        }

        init {
            view.setOnClickListener {
                itemClickListener?.invoke(oldList[adapterPosition].id)
            }
            delete.setOnClickListener {
                deleteClickListener?.invoke(oldList[adapterPosition])
            }

            edit.setOnClickListener {
                editClickListener?.invoke(oldList[adapterPosition])
            }
        }
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

    fun triggerItemClickListener(block: (Int) -> Unit) {
        itemClickListener = block
    }

    fun deleteItemClickListenere(block: (ToDoEntity) -> Unit) {
        deleteClickListener = block
    }

    fun editClickListener(block: (ToDoEntity) -> Unit) {
        editClickListener = block
    }

}