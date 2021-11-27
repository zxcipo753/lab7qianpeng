package edu.temple.lab7__001

import androidx.lifecycle.ViewModel
import java.io.Serializable

class BookList : ViewModel(), Serializable{
    companion object {
        val BOOKLIST_KEY = "bookList"
    }


    private val bookList : ArrayList<Book> by lazy {
        ArrayList()
    }

    fun getBook(int: Int) : Book{
        return bookList[int]
    }

    fun add(book: Book) {
        bookList.add(book)
    }

    fun remove(book: Book){
        bookList.remove(book)
    }

    fun clear(){
        bookList.clear()
    }

    fun addBooks (newBookList: BookList){
        bookList.clear()
        bookList.addAll(newBookList.bookList)
    }


    operator fun get(index: Int) = bookList.get(index)

    fun size() = bookList.size

}