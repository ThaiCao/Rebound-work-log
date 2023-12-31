/*
 * Copyright (c) 2022 Ankit Suda.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.ankitsuda.rebound.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankitsuda.base.utils.extensions.lazyAsync
import com.ankitsuda.base.utils.extensions.shareWhileObserved
import com.ankitsuda.rebound.data.datastore.PrefStorage
import com.ankitsuda.rebound.domain.DistanceUnit
import com.ankitsuda.rebound.domain.WeightUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val prefs: PrefStorage
) : ViewModel() {
    val weightUnit = prefs.weightUnit.shareWhileObserved(viewModelScope)
    val distanceUnit = prefs.distanceUnit.shareWhileObserved(viewModelScope)
    val firstDayOfWeek = prefs.firstDayOfWeek.shareWhileObserved(viewModelScope)

    fun setWeightUnit(value: WeightUnit) {
        viewModelScope.launch {
            prefs.setWeightUnit(value)
        }
    }

    fun setDistanceUnit(value: DistanceUnit) {
        viewModelScope.launch {
            prefs.setDistanceUnit(value)
        }
    }

    fun setFirstDayOfWeek(value: Int) {
        viewModelScope.launch {
            prefs.setFirstDayOfWeek(value)
        }
    }
}