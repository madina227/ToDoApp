package uz.gita.todoapp
/*
page or screen xml-> recycle view tepasda long click qganda paydo boladgan view

<RelativeLayout
        android:id="@+id/actionBar"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:elevation="10dp"
        android:visibility="gone"

        app:layout_constraintTop_toTopOf="parent">

        <ImageButton
            android:id="@+id/btnClearChecked"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginStart="16dp"
            android:background="?selectableItemBackgroundBorderless"
            android:padding="5dp"
            android:src="@drawable/ic_baseline_clear_24" />

        <TextView
            android:id="@+id/txtSelectedCount"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginStart="10dp"
            android:layout_toEndOf="@id/btnClearChecked"
            android:textColor="@color/black"
            android:textSize="18sp"
            android:textStyle="bold"
            tools:text="Selected: 2" />

        <ImageButton
            android:id="@+id/btnDeleteChecked"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentEnd="true"
            android:layout_centerVertical="true"
            android:layout_marginEnd="16dp"
            android:background="?selectableItemBackgroundBorderless"
            android:padding="5dp"
            android:src="@drawable/ic_baseline_delete_24" />


    </RelativeLayout>
----------------------------------------------------------------------------------------------------------
item xmlda checkbox

        <ImageButton
            android:id="@+id/checkbox"
            android:layout_width="0dp"
            android:layout_weight="1"
            android:layout_height="60dp"
            android:layout_alignParentEnd="true"
            android:layout_marginEnd="16dp"
            android:layout_gravity="center"
            android:background="?selectableItemBackgroundBorderless"
            android:src="@drawable/ic_baseline_radio_button_unchecked_24"
            android:visibility="visible" />

------------------------------------------------------------------------------------------------------------
dao da
    @Delete
    fun deleteAll(list: List<ToDoEntity>)
------------------------------------------------------------------------------------------------------------
repositoryda
    override fun deleteAll(list: List<ToDoEntity>) {
        todoDao.deleteAll(list)
    }
------------------------------------------------------------------------------------------------------------
viewModelda
    override fun deleteAll(list: List<ToDoEntity>) {
        repo.deleteAll(list)
    }

------------------------------------------------------------------------------------------------------------
adapterda

    private var itemSelectedStateChangeListener: ((ToDoEntity) -> Unit)? = null

    private var itemLongClickListener: ((Unit) -> Unit)? = null

    fun setItemSelectedStateChangeListener(block: (ToDoEntity) -> Unit) {
        itemSelectedStateChangeListener = block
    }

    fun setItemLongClickListener(block: (Unit) -> Unit) {
        itemLongClickListener = block
    }

var isSelected = false


        private val checkbox = view.findViewById<ImageButton>(R.id.checkbox)
        private val bosilish = view.findViewById<View>(R.id.bosilish)
bind ichida{
            if (isSelected) {
                checkbox.visibility = View.VISIBLE
                if (getItem(adapterPosition).isChecked) {
                    checkbox.setImageResource(R.drawable.ic_baseline_check_circle_24)
                } else {
                    checkbox.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
                }
            } else
                checkbox.visibility = View.GONE
}

  init ichida {
            bosilish.setOnClickListener {
                if (!isSelected)
                    itemClickListener?.invoke(getItem(adapterPosition))
            }

            bosilish.setOnLongClickListener {
                itemLongClickListener?.invoke(Unit)
                true
            }
            checkbox.setOnClickListener {
                if (getItem(adapterPosition).isChecked) {
                    checkbox.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
                } else {
                    checkbox.setImageResource(R.drawable.ic_baseline_check_circle_24)
                }
                getItem(adapterPosition).isChecked = !getItem(adapterPosition).isChecked
                itemSelectedStateChangeListener?.invoke(getItem(adapterPosition))
            }

        }
    }
    fun setSelectedState(state: Boolean) {
        isSelected = state
        notifyDataSetChanged()
    }
------------------------------------------------------------------------------------------------------------
screenda

    private lateinit var txtSelectedCount: TextView
    private lateinit var btnClearChecked: ImageButton
    private lateinit var actionBar: View
    private lateinit var btnDelete: ImageButton

     private val selectedItemList = mutableListOf<ToDoEntity>()// select qilingan itemlani listi

     txtSelectedCount = view.findViewById(R.id.txtSelectedCount)
        btnClearChecked = view.findViewById(R.id.btnClearChecked) //action bardagi x button
        actionBar = view.findViewById(R.id.actionBar)
        btnDelete = view.findViewById(R.id.btnDeleteChecked) //action bardagi delete button

         adapter.setItemLongClickListener {
            actionBar.visibility = View.VISIBLE
            txtSelectedCount.text = "Selected: 0"
            adapter.setSelectedState(true)
        }

        adapter.setItemSelectedStateChangeListener {
            if (it.isChecked) {
                selectedItemList.add(it)
            } else {
                selectedItemList.remove(it)
            }
            txtSelectedCount.text = "Selected: ${selectedItemList.size}"
        }

        btnClearChecked.setOnClickListener {
            adapter.setSelectedState(false)
            selectedItemList.forEach {
                it.isChecked = false
            }
            selectedItemList.clear()
            actionBar.visibility = View.GONE
        }

        btnDelete.setOnClickListener {
            viewModel.deleteAll(selectedItemList)
            adapter.setSelectedState(false)
            actionBar.visibility=View.GONE
        }




 */