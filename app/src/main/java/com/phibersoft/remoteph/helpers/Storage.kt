package com.phibersoft.remoteph.helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.phibersoft.remoteph.R

class Storage {
    companion object {
        private fun _getStorage(context: Context): SharedPreferences {
            return context.getSharedPreferences(
                context.resources.getString(R.string.storage_file_name),
                MODE_PRIVATE
            )
        }

        fun getValue(context: Context, key: String, def: String): String {
            val sp = _getStorage(context)

            return sp.getString(key, def) as String
        }

        fun setValue(context: Context, key: String, value: String) {
            val sp = _getStorage(context)
            with(sp.edit()) {
                putString(key, value)
                apply()
            }
        }
    }
}