package com.edwin_lopez.appferia


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


class Activity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recibe los datos enviados desde la actividad principal
        val titulo = intent.getStringExtra("titulo") ?: "Negocio"
        val imagenResId = intent.getIntExtra("imagenResId", R.drawable.logo_feria_2025) // valor por defecto

        // Aquí obtienes el tamaño personalizado de la imagen
        var ancho = intent.getIntExtra("ancho", 200) // en dp
        val alto = intent.getIntExtra("alto", 200)

        val descripcion = intent.getStringExtra("descripcion") ?: "Sin descripción"

        setContent {
            DetalleScreen(
                titulo = titulo,
                imagenResId = imagenResId,
                ancho = ancho,
                alto = alto,
                descripcion = descripcion)
        }
    }
}

@Composable
fun DetalleScreen(
    titulo: String,
    imagenResId: Int,
    ancho: Int,
    alto: Int,
    descripcion: String
) {
    var scale by remember { mutableStateOf(1f) }
    var isZooming by remember { mutableStateOf(false) }

    val transformableState = rememberTransformableState { zoomChange, _, _ ->
        scale *= zoomChange
        isZooming = true
    }

    val context = LocalContext.current

    // Detecta cuando se deja de hacer zoom y regresa la imagen a su tamaño original
    LaunchedEffect(isZooming) {
        if (isZooming) {
            kotlinx.coroutines.delay(350)
            scale = 1f
            isZooming = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Detecta si el dispositivo está en modo horizontal o vertical
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isLandscape = maxWidth > maxHeight

            val contentModifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            // Diseño horizontal
            if (isLandscape) {
                Row(
                    modifier = contentModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ZoomableImage(imagenResId, ancho, alto, scale, transformableState)

                    if (scale == 1f) {
                        InfoContent(titulo, descripcion)
                    }
                }
            } else {
                // Diseño vertical
                Column(

                    modifier = contentModifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (scale == 1f) {
                        Text(
                            text = titulo,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    ZoomableImage(imagenResId, ancho, alto, scale, transformableState)

                    if (scale == 1f) {
                        Text(
                            text = descripcion,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Button(
                            onClick = { (context as? ComponentActivity)?.finish() },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text(text = "Regresar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
//imagen con zoom
fun ZoomableImage(
    imagenResId: Int,
    ancho: Int,
    alto: Int,
    scale: Float,
    transformableState: androidx.compose.foundation.gestures.TransformableState
) {
    Box(
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .transformable(state = transformableState)
    ) {
        Image(
            painter = painterResource(id = imagenResId),
            contentDescription = null,
            modifier = Modifier
                .width(ancho.dp)
                .height(alto.dp)
                .padding(16.dp)
        )
    }
}

@Composable
fun InfoContent(titulo: String, descripcion: String) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = titulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = descripcion,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { (context as? ComponentActivity)?.finish() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Regresar")
        }
    }
}

