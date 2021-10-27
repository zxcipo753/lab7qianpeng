package edu.temple.lab7__001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val booksList = BookList()
        val booksAuthor = resources.getStringArray(R.array.authors)
        val booksName = resources.getStringArray(R.array.book_titles)

        //Add bookname and author to the booklist array
        for(i in booksAuthor.indices){
            booksList.add(Book(booksName[i],booksAuthor[i]))
        }

        val d=this.resources.displayMetrics.densityDpi
        Log.d("bookTag", d.toString())
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.FragContainer1, SelectionFragment.newInstance(booksName,booksAuthor))
                .commit()

    }


}