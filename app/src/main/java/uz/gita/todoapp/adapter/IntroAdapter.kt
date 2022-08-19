package uz.gita.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.todoapp.R

import uz.gita.todoapp.model.IntroData

class IntroAdapter : ListAdapter<IntroData, IntroAdapter.ViewHolder>(IntroCallBack) {

    object IntroCallBack : DiffUtil.ItemCallback<IntroData>() {
        override fun areItemsTheSame(oldItem: IntroData, newItem: IntroData): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: IntroData, newItem: IntroData): Boolean {
            return oldItem.text == newItem.text && oldItem.image == newItem.image
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img: ImageView = view.findViewById(R.id.imageView2)
        private val text: TextView = view.findViewById(R.id.textView2)

        fun bind() {
            val item = getItem(adapterPosition)
            img.setImageResource(item.image)
            text.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_intro, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }


}