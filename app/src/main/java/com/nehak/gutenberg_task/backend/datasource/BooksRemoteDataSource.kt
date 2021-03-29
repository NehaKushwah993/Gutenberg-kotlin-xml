package com.nehak.gutenberg_task.backend.datasource

import com.nehak.gutenberg_task.backend.network.BookService
import com.nehak.gutenberg_task.models.BookResponse
import com.nehak.gutenberg_task.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import com.nehak.gutenberg_task.models.*

/**
 * Created by Neha Kushwah on 3/29/21.
 *  fetches data from remote source
 */
class BooksRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchListOfBooks(): Result<BookResponse> {
        val bookService = retrofit.create(BookService::class.java);
        return getResponse(
            request = { bookService.getListOfBooks() },
            defaultErrorMessage = "Error fetching Books list")

    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}