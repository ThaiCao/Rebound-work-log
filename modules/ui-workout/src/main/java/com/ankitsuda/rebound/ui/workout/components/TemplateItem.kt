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

package com.ankitsuda.rebound.ui.workout.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ankitsuda.rebound.domain.entities.TemplateWithWorkout
import com.ankitsuda.rebound.ui.components.TemplateItemCard
import com.ankitsuda.rebound.ui.components.dragdrop.DragDropListState
import com.ankitsuda.rebound.ui.workout.R
import com.ankitsuda.rebound.ui.workout.invisible

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TemplateItem(
    invisible: Boolean,
    templateWithWorkout: TemplateWithWorkout,
    dragDropListState: DragDropListState,
    onClickPlay: () -> Unit,
    onClick: () -> Unit
) {
    with(templateWithWorkout) {
        val isSelectedForDrag =
            "template_${template.id}" == dragDropListState.initiallyDraggedElement?.key
        val offsetY by animateFloatAsState(targetValue =
        dragDropListState.elementDisplacement.takeIf {
            isSelectedForDrag
        } ?: 0f)

        TemplateItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .invisible(invisible)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .offset { IntOffset(0, offsetY.toInt()) }
                .zIndex(if (isSelectedForDrag) 10f else 0f)
                .animateItemPlacement(),
            name = (workout.name ?: "").ifBlank { stringResource(R.string.unnamed_template) },
            italicName = (workout.name ?: "").isBlank(),
            totalExercises = exerciseWorkoutJunctions.size,
            onClickPlay = onClickPlay,
            onClick = onClick
        )
    }
}