package com.ankitsuda.rebound.ui.screens.personalization.top_bar

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankitsuda.rebound.data.datastore.PrefStorage
import com.ankitsuda.rebound.utils.TopBarAlignment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarPersonalizationScreenViewModel @Inject constructor(val prefStorage: PrefStorage): ViewModel() {
    val titleAlignment = prefStorage.topBarTitleAlignment
    val allTitleAlignments = TopBarAlignment.values()

    fun setTitleAlignment(value: String) {
        viewModelScope.launch {
            prefStorage.setTopBarTitleAlignment(value)
        }
    }

}