package com.nehak.gutenberg_task.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nehak.gutenberg_task.backend.repository.BooksRepository
import com.nehak.gutenberg_task.models.BookResponse
import com.nehak.gutenberg_task.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Neha Kushwah on 3/29/21.
 * ViewModel for ListingActivity
 */
@HiltViewModel
class ListingViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel() {

    private val _bookList = MutableLiveData<Result<BookResponse?>>()
    val booksList = _bookList

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            booksRepository.fetchBooks().collect {
                _bookList.value = it
            }
        }
    }
}