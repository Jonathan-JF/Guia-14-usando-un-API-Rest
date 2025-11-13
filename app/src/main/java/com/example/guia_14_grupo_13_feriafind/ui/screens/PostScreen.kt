package com.example.guia_14_grupo_13_feriafind.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.guia_14_grupo_13_feriafind.data.model.Post
import com.example.guia_14_grupo_13_feriafind.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel) {
    // Observamos el flujo de datos del ViewModel
    val posts = viewModel.postList.collectAsState().value

    // Estados para los campos de texto
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    // Scaffold con TopAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Listado de Posts") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                // --- Formulario para crear Post (Esto faltaba) ---
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Contenido") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val newPost = Post(userId = 1, id = 0, title = title, body = body)
                        viewModel.createPost(newPost)
                        title = ""
                        body = ""
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Crear Post")
                }
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = posts) { post ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(all = 16.dp)) {
                                Text(
                                    text = "Titulo: ${post.title}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(height = 4.dp))
                                Text(
                                    text = post.body,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}