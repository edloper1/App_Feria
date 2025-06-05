package com.edwin_lopez.appferia.ui.theme

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.edwin_lopez.appferia.adapter.CatApiService
import com.edwin_lopez.appferia.model.CatImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen() {

    val context = LocalContext.current
    val activity = context as? Activity

    val scope = rememberCoroutineScope()
    var cats by remember { mutableStateOf<List<CatImage>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var hasError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoading = true
        scope.launch {
            try {
                cats = CatApiService.api.getCatImages()
                hasError = false
            } catch (e: Exception) {
                e.printStackTrace()
                hasError = true
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Galería de Gatitos") },
                navigationIcon = {
                    IconButton(onClick = { activity?.finish() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            hasError -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error al cargar los gatitos 🐱. Verifica tu conexión.")
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.padding(padding)
                ) {
                    items(cats) { cat ->
                        Image(
                            painter = rememberAsyncImagePainter(cat.url),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}
