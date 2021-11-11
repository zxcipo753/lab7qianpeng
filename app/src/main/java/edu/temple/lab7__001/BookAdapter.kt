package edu.temple.lab7__001

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(_items:BookList, _ocl:View.OnClickListener):RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private val bookList = _items
    private val ocl = _ocl

    class ViewHolder(_view: View, ocl:View.OnClickListener):RecyclerView.ViewHolder(_view){
        val titleTextView:TextView = _view.findViewById(R.id.titleTextView)
        val authorTextView:TextView = _view.findViewById(R.id.authorTextView)
        val view =_view.apply{setOnClickListener(ocl)}
        lateinit var book:Book

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        var vh = ViewHolder(v, ocl)
        return vh
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.library_item, parent, false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = library[position].title
        holder.authorTextView.text = library[position].author
        holder.book = library[position]
        var book = bookList[position]
        holder.authorTextView.text = book.author
        holder.titleTextView.text = book.title


    }

    override fun getItemCount(): Int {
        return bookList.size()
    }
}