package uz.gita.todoapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val state: Int,
    @ColumnInfo(name = "is_removed")
    val isRemoved: Boolean,
    @ColumnInfo(name = "is_checked")
    var isChecked: Boolean
)
