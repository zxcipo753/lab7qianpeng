

package edu.temple.lab7__001

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class BookDetailsFragment : Fragment() {

    lateinit var layout: View
    lateinit var bookName: TextView
    lateinit var bookAuthor : TextView
    lateinit var bookImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_book_details, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookName = view.findViewById(R.id.detailBookTitle)
        bookAuthor = view.findViewById(R.id.detailBookAuthor)
        bookImage = view.findViewById(R.id.coverImageView)

        ViewModelProvider(requireActivity())
            .get(BookViewModel::class.java)
            .getBook()
            .observe(viewLifecycleOwner, {updateDetails()})
    }

    private fun updateDetails (){
        val book = ViewModelProvider(requireActivity())
            .get(BookViewModel::class.java)
            .getBook()
        bookName.text = book.value?.title
        bookAuthor.text = book.value?.author
        Picasso.get()
            .load(book.value?.cover_url)
            .into(bookImage)
    }

    companion object{
        fun newInstance() = BookDetailsFragment()
    }


}