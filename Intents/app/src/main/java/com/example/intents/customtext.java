package com.example.intents;

import android.text.Editable;
import android.text.TextWatcher;

interface customtext extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) { }

}
