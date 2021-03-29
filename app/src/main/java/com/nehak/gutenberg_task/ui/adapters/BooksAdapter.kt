package com.nehak.gutenberg_task.ui.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nehak.gutenberg_task.R
import com.nehak.gutenberg_task.models.Book

/**
 * Created by Neha Kushwah on 3/29/21.
 *
 */
class BooksAdapter(private val context: Context, private val list: ArrayList<Book>) :
        RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Book) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
        return BookViewHolder(context, view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<Book>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}