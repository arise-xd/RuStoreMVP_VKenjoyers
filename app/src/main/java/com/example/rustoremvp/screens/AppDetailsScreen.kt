package com.example.rustoremvp.screens

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rustoremvp.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(navController: NavController, appId: Int?, viewModel: AppViewModel = AppViewModel()){
    val app = viewModel.apps.find { it.id == appId } ?: return
    Scaffold(topBar = {
        TopAppBar(title = { Text(app.name)}, navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    painterResource(id = android.R.drawable.ic_media_previous),
                    contentDescription = "Назад"
                )
            }
        })
    }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding((16.dp))) {
            Spacer(modifier = Modifier.size(100.dp))
            Image(
                painter = painterResource(app.iconRes),
                contentDescription = app.name,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Издатель: ${app.company}", style = MaterialTheme.typography.bodyMedium)
            Text("Категория: ${app.category}", style = MaterialTheme.typography.bodyMedium)
            Text("Возрастной рейтинг: ${app.rating}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                itemsIndexed(app.screenshots) { index, screenshot ->
                    Image(
                        painter = painterResource(screenshot),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 200.dp, height = 400.dp)
                            .padding(end = 8.dp)
                            .clickable{ navController.navigate("screenshot/${app.id}/$index")}
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(app.fullDescription, style = MaterialTheme.typography.bodyLarge)
        }
    }
}