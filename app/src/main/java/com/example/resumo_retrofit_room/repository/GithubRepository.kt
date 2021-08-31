package com.example.resumo_retrofit_room.repository

import com.example.resumo_retrofit_room.model.GithubRepositoryResponse
import com.example.resumo_retrofit_room.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository() {

    //Buscamos nossa Interface implementada do retrofit
    private val githubServices = RetrofitService.getGithubServices()



    //Vamos expor o serviço de fetchRepositories para as outras camadas.
    fun fetchRepositories(
        language: String,
        sort: String = "stars",
        page: Int = 1,
        onComplete: (GithubRepositoryResponse?, String?) -> Unit
    ) {
        val call = githubServices.fetchRepositories(
            language = "language:$language",
            sort = sort,
            page = page
        )
        call.enqueue(object : Callback<GithubRepositoryResponse> {
            override fun onResponse(
                call: Call<GithubRepositoryResponse>,
                response: Response<GithubRepositoryResponse>
            ) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Erro diferente")
                }
            }

            override fun onFailure(call: Call<GithubRepositoryResponse>, t: Throwable) {
                onComplete(null, t.message)
            }
        })

    }

    fun insertIntoDb() {

    }

    fun fetchRepositoriesFromDb(language: String) {

    }



}