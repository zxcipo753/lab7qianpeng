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


class BookListFragment : Fragment() {
    private val bookList: BookList by lazy{
        ViewModelProvider(requireActivity()).get(BookList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookViewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
        val onClick : (Book) -> Unit ={
                book: Book -> bookViewModel.setBook(book)
            (activity as EventInterface).selectionMade(book)
        }
        with (view as RecyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BookAdapter(bookList, onClick)
        }
    }

    companion object {
        fun newInstance(_bookList: BookList): BookListFragment {
            val frag = BookListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("bookList", _bookList)
                }
            }
            return frag
        }
    }

    interface EventInterface{
        fun selectionMade(book: Book)
    }
    interface Search{
        fun doSearch()
    }

    fun bookListUpdate(){
        view?.apply{
            (this as RecyclerView).adapter?.notifyDataSetChanged()
        }
    }


}