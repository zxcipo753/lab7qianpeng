package edu.temple.lab7__001

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(_library :BookList, _onClick: (Book) -> Unit): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    private val library = _library
    private val onClick = _onClick

    class BookViewHolder(itemView: View, onClick: (Book) -> Unit):RecyclerView.ViewHolder(itemView){
        val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        lateinit var book: Book
        init {
            titleTextView.setOnClickListener {
                onClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.library, parent, false),
            onClick
        )
    }

    override fun getItemCount(): Int {
        return library.size()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.titleTextView.text = library[position].title
        holder.authorTextView.text = library[position].author
        holder.book = library[position]
    }
}