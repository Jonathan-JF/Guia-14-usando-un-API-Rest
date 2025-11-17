package com.example.guia_14_grupo_13_feriafind.repository

import com.example.guia_14_grupo_13_feriafind.data.model.Post
import com.example.guia_14_grupo_13_feriafind.data.remote.RetrofitInstance

// Este repositorio se encarga de acceder a los datos usando Retrofit
open class PostRepository {
    // Función que obtiene los posts desde la API
    open suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
    // Función que crea un nuevo post en la API
    open suspend fun createPost(post: Post): Post {
        return RetrofitInstance.api.createPost(post)
    }
}