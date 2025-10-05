package com.example.rustoremvp.screens

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rustoremvp.viewmodel.AppViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.jetbrains.annotations.ApiStatus

@Composable
fun FullScreenScreenshotScreen(
    appId: Int,
    startIndex: Int,
    onBack: () -> Unit,
    viewModel: AppViewModel = AppViewModel()
) {
    val app = viewModel.apps.find { it.id == appId } ?: return

    var currentIndex by remember { mutableStateOf(startIndex) }
    Scaffold (
        topBar = {
            TopAppBar (
                title = {androidx.compose.material3.Text(text = app.name)},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_media_previous),
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) {padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount> 40 && currentIndex > 0) {
                            currentIndex--
                        } else if (dragAmount < -40 && currentIndex < app.screenshots.size - 1) {
                            currentIndex++
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(app.screenshots[currentIndex]),
                contentDescription = "Скриншот ${currentIndex+1}",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}