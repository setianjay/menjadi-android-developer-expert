package com.setianjay.watchme.search.presentation

import android.text.Editable
import android.text.TextWatcher

class TextViewTextChangedListener(private val textChangedCallback: TextViewTextChangedCallback) :
    TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        /* not implemented */
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        /* not implemented */
    }

    override fun afterTextChanged(p0: Editable?) {
        textChangedCallback.afterTextChanged(p0.toString())
    }
}