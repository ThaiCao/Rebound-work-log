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

package com.ankitsuda.rebound.data.repositories

import com.ankitsuda.base.utils.toEpochMillis
import com.ankitsuda.rebound.data.db.daos.WorkoutsDao
import com.ankitsuda.rebound.data.datastore.PrefStorage
import com.ankitsuda.rebound.domain.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class WorkoutsRepository @Inject constructor(
    private val workoutsDao: WorkoutsDao,
    private val prefStorage: PrefStorage
) {
    fun getCurrentWorkoutId() = prefStorage.currentWorkoutId


    fun getWorkout(workoutId: Long) = workoutsDao.getWorkout(workoutId)

    fun getAllWorkoutsOnDate(date: LocalDate): Flow<List<Workout>> {
        val epoch = date.toEpochMillis()
        Timber.d("epoch $epoch")
        Timber.d("date $date")
        return workoutsDao.getAllWorkoutsOnDate(epoch).map {
            Timber.d("list $it")
            it
        }
    }

    fun getExerciseWorkoutJunctions(workoutId: Long) =
        workoutsDao.getExerciseWorkoutJunction(workoutId)

    fun getLogEntriesWithExerciseJunction(workoutId: Long) =
        workoutsDao.getLogEntriesWithExerciseJunction(workoutId)

    suspend fun updateWorkout(workout: Workout) {
        workoutsDao.updateWorkout(workout.copy(updatedAt = LocalDateTime.now()))
    }

    suspend fun createWorkout(workout: Workout): Long {
        return workoutsDao.insertWorkout(workout.copy(createdAt = LocalDateTime.now()))
    }

    suspend fun setCurrentWorkoutId(value: Long) {
        prefStorage.setCurrentWorkoutId(value)
    }

    /**
     * Deletes everything related to workout
     */
    suspend fun deleteWorkoutWithEverything(workout: Workout) {
        // Get all ExerciseWorkoutJunctions related to workout
        val junctions = workoutsDao.getExerciseWorkoutJunctionsNonFlow(workout.id)
        // Delete all ExerciseLogEntries for workout using junction ids
        workoutsDao.deleteAllLogEntriesForJunctionIds(junctionIds = junctions.map { it.id })
        // Delete all ExerciseLogs for workout
        workoutsDao.deleteAllLogsForWorkoutId(workoutId = workout.id)
        // Delete all junctions related to workout
        workoutsDao.deleteExerciseWorkoutJunctions(junctions.map { it.id })
        // Delete workout
        workoutsDao.deleteWorkout(workout)
    }

    suspend fun addExerciseToWorkout(workoutId: Long, exerciseId: Long) {
        workoutsDao.insertExerciseWorkoutJunction(
            ExerciseWorkoutJunction(
                workoutId = workoutId,
                exerciseId = exerciseId
            )
        )
    }

    suspend fun addEmptySetToExercise(
        setNumber: Int,
        exerciseWorkoutJunction: ExerciseWorkoutJunction
    ): ExerciseLogEntry {
        val logId = workoutsDao.insertExerciseLog(
            ExerciseLog(
                workoutId = exerciseWorkoutJunction.workoutId,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        )

        val entry = ExerciseLogEntry(
            logId = logId,
            junctionId = exerciseWorkoutJunction.id,
            setNumber = setNumber,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val entryId = workoutsDao.insertExerciseLogEntry(entry)

        return entry.copy(entryId = entryId)
    }

    suspend fun updateExerciseLogEntry(entry: ExerciseLogEntry) {
        workoutsDao.updateExerciseLogEntry(entry.copy(updatedAt = LocalDateTime.now()))
    }

    suspend fun deleteExerciseLogEntry(entry: ExerciseLogEntry) {
        workoutsDao.deleteExerciseLogEntry(entry)
    }

    suspend fun deleteExerciseFromWorkout(logEntriesWithJunctionItem: LogEntriesWithExerciseJunction) {
        with(logEntriesWithJunctionItem) {
            workoutsDao.deleteExerciseWorkoutJunction(junction)
            workoutsDao.deleteExerciseLogEntries(logEntries.map { it.entryId })
            workoutsDao.deleteExerciseLogs(logEntries.map { it.logId!! })
        }
    }
}