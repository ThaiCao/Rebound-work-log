package com.ankitsuda.rebound.ui.settings.personalization.main_colors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ankitsuda.base.ui.ThemeState
import com.ankitsuda.common.compose.LocalDialog
import com.ankitsuda.common.compose.rememberFlowWithLifecycle
import com.ankitsuda.rebound.ui.ThemeViewModel
import com.ankitsuda.rebound.ui.settings.personalization.charts.ChartsPersonalizationScreen



import com.ankitsuda.rebound.ui.components.TopBar
import com.ankitsuda.rebound.ui.components.TopBarBackIconButton
import com.ankitsuda.rebound.ui.components.TopBarIconButton
import com.ankitsuda.rebound.ui.components.collapsing_toolbar.CollapsingToolbarScaffold
import com.ankitsuda.rebound.ui.components.collapsing_toolbar.rememberCollapsingToolbarScaffoldState
import com.ankitsuda.rebound.ui.components.settings.ColorPickerCardItem
import com.ankitsuda.rebound.ui.components.settings.SliderCardItem
import com.ankitsuda.rebound.ui.components.settings.SwitchCardItem
import com.ankitsuda.rebound.ui.theme.ReboundTheme

@Composable
fun MainColorsPersonalizationScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel = hiltViewModel(),
) {
    val themeState by rememberFlowWithLifecycle(themeViewModel.themeState).collectAsState(initial = null)

    themeState?.let { theme ->
        MainColorsPersonalizationScreen(
            navController = navController,
            themeState = theme,
            setThemeState = themeViewModel::applyThemeState,
        )

    }
}

@Composable
fun MainColorsPersonalizationScreen(
    navController: NavController,
    themeState: ThemeState,
    setThemeState: (ThemeState) -> Unit,
) {
    val collapsingState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        state = collapsingState,
        toolbar = {
            TopBar(title = "Main Colors", strictLeftIconAlignToStart = true, leftIconBtn = {
                TopBarBackIconButton {
                    navController.popBackStack()
                }
            }, rightIconBtn = {
                TopBarIconButton(icon = Icons.Outlined.Restore, title = "Reset to defaults") {

                }
            })
        },
        modifier = Modifier.background(ReboundTheme.colors.background)
    ) {

        val itemModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)

        with(LocalDialog.current) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ReboundTheme.colors.background),
                contentPadding = PaddingValues(16.dp)
            ) {

                item {

                    ColorPickerCardItem(
                        modifier = itemModifier,
                        text = "Primary Color",
                        selectedColor = themeState.primaryColor,
                        onNewColorSelected = {
                            setThemeState(themeState.copy(primaryColor = it))
                        },
                    )

                }

                item {

                    ColorPickerCardItem(
                        modifier = itemModifier,
                        text = "Background Color",
                        selectedColor = themeState.backgroundColor,
                        onNewColorSelected = {
                            setThemeState(themeState.copy(backgroundColor = it))
                        }
                    )

                }

                item {
                    ColorPickerCardItem(
                        modifier = itemModifier,
                        text = "On Primary Color",
                        selectedColor = themeState.onPrimaryColor,
                        useAltColorPicker = true,
                        onNewColorSelected = {
                            setThemeState(themeState.copy(onPrimaryColor = it))
                        })
                }
                item {
                    ColorPickerCardItem(
                        modifier = itemModifier,
                        text = "On Background Color",
                        selectedColor = themeState.onBackgroundColor,
                        onNewColorSelected = {
                            setThemeState(themeState.copy(onBackgroundColor = it))
                        })
                }

                item {

                    SwitchCardItem(
                        modifier = itemModifier,
                        text = "Is Light Theme",
                        checked = themeState.isLightTheme,
                        onChange = {
                            setThemeState(themeState.copy(isLightTheme = it))
                        }
                    )

                }

                item {

                    SwitchCardItem(
                        modifier = itemModifier,
                        text = "Dark status bar icons",
                        checked = themeState.isDarkStatusBarIcons,
                        onChange = {
                            setThemeState(themeState.copy(isDarkStatusBarIcons = it))
                        }
                    )

                }
                item {

                    SwitchCardItem(
                        modifier = itemModifier,
                        text = "Dark navigation bar icons",
                        checked = themeState.isDarkNavigationBarIcons,
                        onChange = {
                            setThemeState(themeState.copy(isDarkNavigationBarIcons = it))
                        }
                    )

                }
            }
        }
    }

}

