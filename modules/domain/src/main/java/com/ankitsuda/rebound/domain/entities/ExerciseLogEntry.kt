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

package com.ankitsuda.rebound.domain.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ankitsuda.rebound.domain.LogSetType
import com.ankitsuda.rebound.domain.PersonalRecord
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
@Entity(tableName = "exercise_log_entries")
data class ExerciseLogEntry(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "entry_id")
    val entryId: String,

    @ColumnInfo(name = "log_id")
    var logId: String? = null,
    @ColumnInfo(name = "junction_id")
    var junctionId: String? = null,

    // Number of set
    @ColumnInfo(name = "set_number")
    var setNumber: Int? = null,
    @ColumnInfo(name = "set_type")
    var setType: LogSetType? = null,

    @ColumnInfo(name = "weight")
    var weight: Double? = null,
    @ColumnInfo(name = "reps")
    var reps: Int? = null,
    @ColumnInfo(name = "rpe")
    var rpe: Float? = null,

    @ColumnInfo(name = "completed")
    var completed: Boolean = false,

    // Time in milliseconds
    @ColumnInfo(name = "time_recorded")
    var timeRecorded: Long? = null,

    @ColumnInfo(name = "distance")
    var distance: Double? = null,

    @ColumnInfo(name = "weight_unit")
    var weight_unit: String? = null,
    @ColumnInfo(name = "distance_unit")
    var distance_unit: String? = null,

    @ColumnInfo(name = "personal_records")
    var personalRecords: List<PersonalRecord>? = null,

    @ColumnInfo(name = "created_at")
    var createdAt: LocalDateTime? = null,
    @ColumnInfo(name = "update_at")
    var updatedAt: LocalDateTime? = null,
) : Parcelable

fun List<ExerciseLogEntry>.calculateTotalVolume(): Double {
    var volume = 0.0
    for (entry in this) {
        volume += ((entry.weight ?: 0.0) * (entry.reps ?: 0).toDouble())
    }
    return volume
}

fun List<ExerciseLogEntry>.getTotalPRs(workoutPrs: Int? = null): Int {
    return sumOf { it.personalRecords?.size ?: 0 } + (workoutPrs ?: 0)
}