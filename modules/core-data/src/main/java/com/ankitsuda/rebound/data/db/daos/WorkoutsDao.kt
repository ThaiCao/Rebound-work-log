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

package com.ankitsuda.rebound.data.db.daos

import androidx.room.*
import com.ankitsuda.rebound.domain.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutsDao {

    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun getWorkout(workoutId: Long): Flow<Workout?>

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): Flow<List<Workout>>

    //    @Query("SELECT * FROM workouts WHERE date(created_at) = date(:date)")
    @Query("SELECT * FROM workouts WHERE date(created_at / 1000,'unixepoch') = date(:date / 1000,'unixepoch')")
    fun getAllWorkoutsOnDate(date: Long): Flow<List<Workout>>

    @Query("SELECT * FROM exercise_workout_junctions WHERE workout_id = :workoutId")
    fun getExerciseWorkoutJunction(workoutId: Long): Flow<List<ExerciseWorkoutJunction>>

    @Query("SELECT * FROM exercise_workout_junctions WHERE workout_id = :workoutId")
    suspend fun getExerciseWorkoutJunctionsNonFlow(workoutId: Long): List<ExerciseWorkoutJunction>

    @Query("SELECT * FROM exercise_log_entries WHERE junction_id = :junctionId")
    suspend fun getExerciseLogEntriesNonFlow(junctionId: Long): List<ExerciseLogEntry>

    @Query("DELETE FROM exercise_log_entries WHERE junction_id IN (:junctionIds)")
    suspend fun deleteAllLogEntriesForJunctionIds(junctionIds: List<Long>)

    @Query("DELETE FROM exercise_logs WHERE workout_id = :workoutId")
    suspend fun deleteAllLogsForWorkoutId(workoutId: Long)

    @Transaction
    @Query("SELECT * FROM exercise_workout_junctions WHERE workout_id = :workoutId")
    fun getLogEntriesWithExerciseJunction(workoutId: Long): Flow<List<LogEntriesWithExerciseJunction>>

    @Delete
    suspend fun deleteExerciseLogEntry(exerciseLogEntry: ExerciseLogEntry)

    @Query("DELETE FROM exercise_log_entries WHERE entry_id IN (:ids)")
    suspend fun deleteExerciseLogEntries(ids: List<Long>)

    @Delete
    suspend fun deleteExerciseLog(exerciseLog: ExerciseLog)


    @Query("DELETE FROM exercise_logs WHERE id IN (:ids)")
    suspend fun deleteExerciseLogs(ids: List<Long>)

    @Delete
    suspend fun deleteExerciseWorkoutJunction(exerciseWorkoutJunction: ExerciseWorkoutJunction)

    @Query("DELETE FROM exercise_workout_junctions WHERE id IN (:ids)")
    suspend fun deleteExerciseWorkoutJunctions(ids: List<Long>)


    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("DELETE FROM workouts WHERE id = :workoutId")
    suspend fun deleteWorkoutById(workoutId: Long)

    @Update
    suspend fun updateExerciseLogEntry(exerciseLogEntry: ExerciseLogEntry)

    @Insert
    suspend fun insertWorkout(workout: Workout): Long

    @Update
    suspend fun updateWorkout(workout: Workout)

    @Insert
    suspend fun insertExerciseWorkoutJunction(exerciseWorkoutJunction: ExerciseWorkoutJunction): Long

    @Insert
    suspend fun insertExerciseLog(log: ExerciseLog): Long

    @Insert
    suspend fun insertExerciseLogEntry(logEntry: ExerciseLogEntry): Long
}