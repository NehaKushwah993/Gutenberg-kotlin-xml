package com.nehak.gutenberg_task.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nehak.gutenberg_task.databinding.ActivityListBinding
import com.nehak.gutenberg_task.models.Book
import com.nehak.gutenberg_task.models.Result
import com.nehak.gutenberg_task.ui.adapters.BooksAdapter
import com.nehak.gutenberg_task.ui.viewModels.ListingViewModel
import com.nehak.gutenberg_task.util.PhotoSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Neha Kushwah on 3/29/21.
 *  Shows list of books
 */
@AndroidEntryPoint
class ListingActivity : AppCompatActivity() {

    companion object {
        private const val COLUMN_COUNT: Int = 3;
        private const val SPACE: Int = 5;
    }

    private lateinit var binding: ActivityListBinding
    private val list = ArrayList<Book>()
    private val viewModel by viewModels<ListingViewModel>()
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater);
        setContentView(binding.root)

        init()
        subscribeUi()
    }

    private fun init() {
        title = "List of Books"
        val layoutManager = GridLayoutManager(this, Companion.COLUMN_COUNT)
        binding.rvBooks.layoutManager = layoutManager

        val dividerItemDecoration = PhotoSpaceDecoration(
                binding.rvBooks.context,
                SPACE
        )

        binding.rvBooks.addItemDecoration(dividerItemDecoration)
        booksAdapter = BooksAdapter(this, list)
        binding.rvBooks.adapter = booksAdapter
    }

    private fun subscribeUi() {
        viewModel.booksList.observe(this, Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.results?.let { list ->
                        booksAdapter.updateData(list)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun showError(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

}