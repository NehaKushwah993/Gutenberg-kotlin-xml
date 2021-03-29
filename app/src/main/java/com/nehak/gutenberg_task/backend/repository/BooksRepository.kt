package com.nehak.gutenberg_task.backend.repository

import com.nehak.gutenberg_task.backend.datasource.BooksRemoteDataSource
import com.nehak.gutenberg_task.models.BookResponse
import com.nehak.gutenberg_task.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Neha Kushwah on 3/29/21.
 * Repository which fetches data from Remote or Local data sources
 * TODO : LocalDB implementation is pending
 */
class BooksRepository @Inject constructor(private val booksDataSource: BooksRemoteDataSource){

    suspend fun fetchBooks() : Flow<Result<BookResponse?>?>{
        return flow{
            emit(Result.loading())
            val result = booksDataSource.fetchListOfBooks()
            emit(result)

        }.flowOn(Dispatchers.IO)

    }

}