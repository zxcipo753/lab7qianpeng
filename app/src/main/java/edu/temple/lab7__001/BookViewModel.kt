package edu.temple.lab7__001

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class BookViewModel : ViewModel() {

    private var empty: Boolean = true

    val setTitle: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun setTitle(book: String) {
        setTitle.value = book
    }

    val setAuthor: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    fun setAuthor(item:String){
        setAuthor.value=item
    }

    private val bookList : MutableLiveData<BookList> by lazy {
        MutableLiveData<BookList>()
    }

    fun getBookList() : LiveData<BookList> {
        return bookList
    }

    private val book: MutableLiveData<Book> by lazy {
        MutableLiveData()
    }

    fun getSelectedBook(): LiveData<Book> {
        return book
    }

    fun setSelectedBook(selectedBook: Book?) {
        this.book.value = selectedBook
    }



}