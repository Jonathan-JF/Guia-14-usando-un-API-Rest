package com.example.guia_14_grupo_13_feriafind.data.remote

import com.example.guia_14_grupo_13_feriafind.data.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Esta interfaz define los endpoints HTTP
interface ApiService {

    // Define una solicitud GET al endpoint /posts
    @GET(value = "/posts")
    suspend fun getPosts(): List<Post>

    @POST(value = "/posts")
    suspend fun createPost(@Body post: Post): Post
}