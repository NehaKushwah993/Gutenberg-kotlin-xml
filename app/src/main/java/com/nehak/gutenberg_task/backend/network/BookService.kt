package com.nehak.gutenberg_task.backend.network

import com.nehak.gutenberg_task.models.BookResponse
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Neha Kushwah on 3/29/21.
 *  Retrofit API Service
 */
interface BookService {

    @GET("/books")
    suspend fun getListOfBooks() : Response<BookResponse>

}