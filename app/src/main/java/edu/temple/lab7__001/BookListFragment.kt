package edu.temple.lab7__001

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var items:BookList
class BookListFragment : Fragment() {
    private lateinit var layout:View
    private lateinit var recyclerView:RecyclerView
    private lateinit var viewModel:SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity.let{
            viewModel = ViewModelProvider(it!!).get(SharedViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(R.layout.fragment_book_list, container, false)
        recyclerView = layout.findViewById(R.id.fragmentRecyclerView)
        recyclerView.adapter = BookListAdapter(items, onClickListener)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return layout
    }

    val onClickListener = View.OnClickListener {
        //grab the book that was clicked
        val itemPosition = recyclerView.getChildAdapterPosition(it)
        val book = items.get(itemPosition)

        Log.d("Fragment1", "${book.title}")
        //place book in viewModel
        viewModel.setBook(book)

        (activity as EventInterface).selectionMade()
    }

    companion object {
        fun newInstance(_items:BookList) =
            BookListFragment().apply {
                items = _items
            }

    }

    interface EventInterface{
        fun selectionMade()
    }


}
