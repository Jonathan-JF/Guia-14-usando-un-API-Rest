package com.example.guia_14_grupo_13_feriafind.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guia_14_grupo_13_feriafind.data.model.Post
import com.example.guia_14_grupo_13_feriafind.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel que mantiene el estado de los datos obtenidos
class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    // Flujo mutable que contiene la lista de posts
    private val _postList = MutableStateFlow<List<Post>>(emptyList())

    // Flujo público de solo lectura
    val postList: StateFlow<List<Post>> = _postList

    // Se Llama automáticamente al iniciar
    init {
        fetchPosts()
    }

    // Función que obtiene los datos en segundo plano
    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _postList.value = repository.getPosts()
            } catch (e: Exception) {
                println("Error al obtener datos: ${e.localizedMessage}")
            }
        }
    }
    // Función que crea un nuevo post
    fun createPost(post: Post) {
        viewModelScope.launch {
            try {
                // Llama al repositorio para crear el post
                val newPost = repository.createPost(post)
                // Agrega el nuevo post al inicio de la lista actual en el StateFlow
                _postList.value = listOf(newPost) + _postList.value
            } catch (e: Exception) {
                println("Error al crear el post: ${e.localizedMessage}")
            }
        }
    }
}