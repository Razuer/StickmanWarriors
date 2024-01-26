package com.rejzerek.stickmanwarriors.feature_stickman.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.rejzerek.stickmanwarriors.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedImage = mutableIntStateOf(R.drawable.menu_img1)
    val selectedImage: State<Int> = _selectedImage

    init {
        println("Tworzę sobie go")
    }

    fun setSelectedImage(id: Int) {
        _selectedImage.intValue = id
    }
}
