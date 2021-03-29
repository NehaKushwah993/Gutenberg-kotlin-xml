package com.nehak.gutenberg_task.ui.adapters


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nehak.gutenberg_task.BuildConfig
import com.nehak.gutenberg_task.databinding.ListItemBookBinding
import com.nehak.gutenberg_task.models.Book


/**
 * Created by Neha Kushwah on 3/29/21.
 *
 */
class BooksAdapter(private val context: Context, private val list: ArrayList<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(
        private val context: Context,
        private val listItemBookBinding: ListItemBookBinding
    ) : RecyclerView.ViewHolder(listItemBookBinding.root) {
        fun bind(book: Book) {
            itemView.setOnClickListener {
                openWebBrowserApp(context, book)
            }
            listItemBookBinding.book = book
            listItemBookBinding.executePendingBindings()
        }

        private fun openWebBrowserApp(context: Context, book: Book) {
            val urlString = BuildConfig.BOOK_WEB_URL + book.id
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            try {
                context.startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                ex.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            context,
            ListItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
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