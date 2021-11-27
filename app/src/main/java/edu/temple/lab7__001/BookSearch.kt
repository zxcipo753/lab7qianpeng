package edu.temple.lab7__001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException


class BookSearchActivity : AppCompatActivity() {
    var bookList = BookList()

    val volleyQueue : RequestQueue by lazy {
        Volley.newRequestQueue(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchButton = findViewById<Button>(R.id.mainSearchButton)

        searchButton.setOnClickListener {
            fetchBook(searchEditText.text.toString())
        }
    }

    private fun fetchBook(bookSearch : String){
        val url = "https://kamorris.com/lab/cis3515/search.php?term=$bookSearch"

        volleyQueue.add(
            JsonArrayRequest(
                Request.Method.GET, url, null,
                {
                    Log.d("Response", it.toString())
                    try{
                        for(i in 0 until it.length()){
                            val jsonObject = it.getJSONObject(i)
                            val book = Book(jsonObject.getInt("id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("author"),
                                jsonObject.getString("cover_url"),
                                jsonObject.getInt("duration"))
                            bookList.add(book)
                        }
                        setResult(RESULT_OK, Intent().putExtra(BookList.BOOKLIST_KEY, bookList)
                        )
                        finish()
                    }
                    catch (e : JSONException){
                        e.printStackTrace()
                        finish()
                    }

                },{
                    Toast.makeText(this, "Network connection Error!", Toast.LENGTH_SHORT).show()
                })
        )

    }
}