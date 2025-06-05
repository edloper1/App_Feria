package com.edwin_lopez.appferia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(
                onNavigateToSecondActivity = {
                    // Iniciamos la segunda actividad cuando se presione el botón
                    startActivity(Intent(this, Activity2::class.java))
                },
                onNavigateToGallery = {
                    startActivity(Intent(this, GalleryActivity::class.java))
                }
            )
        }
    }
}

@Composable
private fun MainScreen(onNavigateToSecondActivity: () -> Unit, onNavigateToGallery: () -> Unit) {
    // Pantalla principal que contiene todos los elementos
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Lista de negocios con sus imágenes
            BusinessItem("Guayacan Nave 1"){
                val intent = Intent(context, Activity3::class.java)
                intent.putExtra("titulo", "Guayacan Nave 1")
                intent.putExtra("imagenResId", R.drawable.nave_1)
                intent.putExtra("ancho", 400)
                intent.putExtra("alto", 250)
                intent.putExtra("descripcion", "Sed dignissim fermentum dui in aliquam. Suspendisse a auctor sapien. " +
                        "Nunc lobortis nec metus quis efficitur. Curabitur mollis ornare eros, ut scelerisque nulla tristique quis. " +
                        "Phasellus tempus, ante at suscipit venenatis, nulla quam sodales risus, id vulputate tellus sem vitae ipsum. " +
                        "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. ")
                context.startActivity(intent)
            }
            BusinessItem("Macuilí Nave 2"){
                val intent = Intent(context, Activity3::class.java)
                intent.putExtra("titulo", "Macuilí Nave 2")
                intent.putExtra("imagenResId", R.drawable.nave_2)
                intent.putExtra("ancho", 400)
                intent.putExtra("alto", 250)
                intent.putExtra("descripcion", "Este es un restaurante ubicado en la nave 1, especializado en comida tradicional.")
                context.startActivity(intent)
            }
            BusinessItem("Framboyan Nave 3"){
                val intent = Intent(context, Activity3::class.java)
                intent.putExtra("titulo", "Framboyan Nave 3")
                intent.putExtra("imagenResId", R.drawable.nave_3)
                intent.putExtra("ancho", 400)
                intent.putExtra("alto", 250)
                intent.putExtra("descripcion", "Este es un restaurante ubicado en la nave 1, especializado en comida tradicional.")
                context.startActivity(intent)
            }
            BusinessItem("Artistas"){
                val intent = Intent(context, Activity3::class.java)
                intent.putExtra("titulo", "Artistas")
                intent.putExtra("imagenResId", R.drawable.cartelera_conciertos)
                intent.putExtra("ancho", 450)
                intent.putExtra("alto", 350)
                intent.putExtra("descripcion", "Este es un restaurante ubicado en la nave 1, especializado en comida tradicional.")
                context.startActivity(intent)
            }


            // Botón para navegar a la segunda actividad
            Button(
                onClick = onNavigateToSecondActivity,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Fechas importantes")
            }
            // Botón para ir a la galería de gatitos
            Button(
                onClick = onNavigateToGallery,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver galería de gatitos 🐱")
            }
        }
    }
}

@Composable
fun BusinessItem(text: String, onClick: () -> Unit) {
    // Componente reutilizable para mostrar negocio con imagen

    // Determina si el sistema está en modo oscuro

    val isDarkTheme = isSystemInDarkTheme()

    // Establece el color de fondo según el tema actual
    val backgroundColor = if (isDarkTheme) Purple80 else Purple40

    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de las cards
            Image(
                painter = painterResource(id = R.drawable.logo_feria_2025),
                contentDescription = "Logo feria",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )
            // Texto del negocio

            Text(
                // Usa una fuente sans-serif para el texto
                fontFamily = FontFamily.SansSerif,
                //aplica negritas al texto
                fontWeight = FontWeight.Bold,
                color = Color.White,
                text = text,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PantallaPrincipalPreview() {
    MainScreen(onNavigateToSecondActivity = {  }, onNavigateToGallery = {  })
}

//colores personalizados
val Purple80 = Color(0xFFD0BCFF)
val Purple40 = Color(0xFF6650a4)