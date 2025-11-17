package com.example.guia_14_grupo_13_feriafind

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.guia_14_grupo_13_feriafind.data.model.Post // Importa tu modelo Post
import com.example.guia_14_grupo_13_feriafind.ui.screens.PostScreen // Importa tu Screen
import com.example.guia_14_grupo_13_feriafind.viewmodel.PostViewModel // Importa tu ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class PostScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun el_titulo_de_post_debe_aparecer_en_pantalla() {
        // Simulamos los datos que el ViewModel entregaria
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Titulo 1", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Titulo 2", body = "Contenido 2")
        )

        //Subclase falsa de PostViewModel con StateFlow simulado
        val fakeViewModel = object: PostViewModel() {
            // Hacemos 'override' de la variable pública, no de la protegida
            override val postList = MutableStateFlow(value = fakePosts)
            // Sobrescribimos la función open para que no haga nada y no interfiera
            override fun fetchPosts() {}
        }

        // Renderizamos el PostScreen con el ViewModel falso
        composeRule.setContent {
            PostScreen(viewModel = fakeViewModel)
        }

        // Validamos que los titulos se muestran correctamente en la UI
        composeRule.onNodeWithText(text = "Titulo: Titulo 1").assertIsDisplayed()
        composeRule.onNodeWithText(text = "Titulo: Titulo 2").assertIsDisplayed()
    }
}