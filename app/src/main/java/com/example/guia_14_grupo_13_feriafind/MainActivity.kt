package com.example.guia_14_grupo_13_feriafind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat // Necesario para edge-to-edge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guia_14_grupo_13_feriafind.ui.screens.PostScreen
import com.example.guia_14_grupo_13_feriafind.ui.theme.Guia_14_Grupo_13_FeriaFindTheme
import com.example.guia_14_grupo_13_feriafind.viewmodel.PostViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que la app dibuje contenido debajo de las barras del sistema
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Aqui inicia Jetpack Compose
        setContent {
            // Aplicamos el tema Material 3
            Guia_14_Grupo_13_FeriaFindTheme {
                // Inyectamos el ViewModel
                val postViewModel: PostViewModel = viewModel()

                // Llamamos a la pantalla principal y pasamos el ViewModel
                PostScreen(viewModel = postViewModel)
            }
        }
    }
}