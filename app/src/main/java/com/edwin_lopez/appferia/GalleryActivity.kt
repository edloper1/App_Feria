package com.edwin_lopez.appferia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.edwin_lopez.appferia.ui.theme.GalleryScreen
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryScreen()
        }
    }
}

@Composable
fun GalleryScreen() {
    LazyColumn {
        items(10) { index ->
            Text("Imagen de gato $index")
        }
    }
}

