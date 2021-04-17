package com.mishok.polygo.utils

import android.text.Editable
import android.text.TextWatcher

abstract class TextWatcherAdapter : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {}
}