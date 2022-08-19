package uz.gita.todoapp.appDatabase

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class ShP {


    companion object {
        private lateinit var sharedPreference:SharedPreferences
        private lateinit var editor: SharedPreferences.Editor
        private var shpIns: ShP? = null

        fun init(context: Context) {
            shpIns = ShP()
            sharedPreference = context.getSharedPreferences("todoShp", Activity.MODE_PRIVATE)
            editor = sharedPreference.edit()
        }

        fun getInstance() = shpIns!!
    }

    fun setState(state: Boolean) {
        editor.putBoolean("STATE", state)
        editor.commit()
    }

    fun getState(): Boolean {
        return sharedPreference.getBoolean("STATE", true)
    }
}