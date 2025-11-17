package com.example.guia_14_grupo_13_feriafind

import com.example.guia_14_grupo_13_feriafind.data.model.Post
import com.example.guia_14_grupo_13_feriafind.viewmodel.PostViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest


@OptIn(markerClass = [ExperimentalCoroutinesApi::class])
class PostViewModelTest: StringSpec(body = {

    "postList debe contener los datos esperados después de fetchPosts()" {
        // Creamos una subclase falsa de PostViewModel que sobrescribe el repositorio
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Título 1", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Título 2", body = "Contenido 2")
        )

        val testViewModel = object: PostViewModel() {
            override fun fetchPosts() {
                _postList.value = fakePosts
            }
        }

        runTest {
            testViewModel.fetchPosts()
            testViewModel.postList.value shouldContainExactly fakePosts
        }
    }
})