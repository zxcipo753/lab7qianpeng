package edu.temple.lab7__001

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class DisplayFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?){
        super .onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_display,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model= ViewModelProvider(requireActivity()).get(BookViewModel::class.java)


        val titleView = view.findViewById<TextView>(R.id.frgTitleView)
        val authorView = view.findViewById<TextView>(R.id.frgAuthorView)
        titleView.text = "Example Text"
        model.setTitle.observe(
            viewLifecycleOwner,
            { o -> titleView.text = o!!.toString() }
        )
        model.setAuthor.observe(
            viewLifecycleOwner,
            { o -> authorView.text = o!!.toString() }
        )
    }

}