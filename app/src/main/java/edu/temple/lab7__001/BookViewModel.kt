package edu.temple.lab7__001

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

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

}