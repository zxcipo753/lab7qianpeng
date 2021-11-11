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
import androidx.fragment.app.activityViewModels


private const val BOOKS = "BOOKS"
class BookListFragment : Fragment() {

    private val viewModel: BookViewModel by activityViewModels()

    interface BookSelectedInterface {
        fun bookSelected()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.book_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onClick : (Book) -> Unit = {
            //Updating view model on book selection
                book: Book -> viewModel.setSelectedBook(book)
            //Informing activity to prevent replay of event when it restarts
            (activity as BookSelectedInterface).bookSelected()
        }

        val manager = LinearLayoutManager(activity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView?.layoutManager = manager

        recyclerView?.adapter = BookListAdapter(BookList, onClick)

    }
}
