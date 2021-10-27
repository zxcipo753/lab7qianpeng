package edu.temple.lab7__001

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val listBooks: BookList):RecyclerView.Adapter<BookAdapter.ViewHolder>(){
    private lateinit var myListener: OnItemClickListener
    interface  OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        myListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView = inflater.inflate(R.layout.rcv_itemcontainer,parent,false)
        return ViewHolder(bookView,myListener)
    }
    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        val book: Book = listBooks.get(position)
        val titleView = holder.titleView
        val authorView = holder.authorView
        titleView.text = book.title
        authorView.text = book.author
    }
    override fun getItemCount(): Int {
        return listBooks.size()
    }

    inner class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById(R.id.rcvTitleView)
        val authorView: TextView = itemView.findViewById(R.id.rcvAuthorView)
        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
