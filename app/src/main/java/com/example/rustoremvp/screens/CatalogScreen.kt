package com.example.rustoremvp.screens

import androidx.annotation.experimental.Experimental
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rustoremvp.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavController, viewModel: AppViewModel = AppViewModel()) {
    Scaffold(topBar = { TopAppBar(title = { Text("RuStore Витрина") }) }) { padding ->
        LazyColumn(contentPadding = padding) {
            items(viewModel.apps.size) { index ->
                val app = viewModel.apps[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("details/${app.id}") }
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(app.iconRes),
                        contentDescription = app.name,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(app.name, style = MaterialTheme.typography.titleMedium)
                        Text(app.shortDescription, style = MaterialTheme.typography.bodyMedium)
                        Text(app.category, style = MaterialTheme.typography.bodySmall)
                    }
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(0.12f)
                )
            }
        }
    }
}