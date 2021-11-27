package edu.temple.lab7__001

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class AudioButton : Fragment() {

    lateinit var playClick: Button
    lateinit var stopClick: Button
    lateinit var pauseClick: Button
    lateinit var durationBar: SeekBar
    lateinit var durationText: TextView
    lateinit var titleText: TextView
    var durationInitial: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_audio, container, false)
        playClick = layout.findViewById(R.id.playButton)
        pauseClick = layout.findViewById(R.id.pauseButton)
        stopClick = layout.findViewById(R.id.stopButton)
        titleText = layout.findViewById(R.id.titleText)
        durationBar = layout.findViewById(R.id.durationBar)
        durationText = layout.findViewById(R.id.durationText)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookViewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
        playClick.setOnClickListener {
            val selectedBook = bookViewModel.getBook().value

            if(selectedBook != null){
                titleText.text = "Now playing -- " + selectedBook.title
                durationBar.max = selectedBook.duration
            }
            (activity as ControlButtons).playClick(durationInitial)
        }
        stopClick.setOnClickListener {
            durationInitial = 0
            durationBar.progress = 0
            (activity as ControlButtons).stopClick()
        }

        pauseClick.setOnClickListener {
            val selectedBook = bookViewModel.getBook().value
            if (selectedBook != null){
                durationInitial = durationBar.progress
            }
            (activity as ControlButtons).pauseClick()
        }

        durationBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(bar: SeekBar, progress: Int, p2: Boolean) {
                val selectedBook = bookViewModel.getBook().value
                if(selectedBook != null){
                    durationText.text = progress.toString()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                (activity as ControlButtons).pauseClick()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val selectedBook = bookViewModel.getBook().value
                if(selectedBook != null){
                    durationInitial = durationBar.progress
                    durationText.text = durationInitial.toString()
                    (activity as ControlButtons).playClick(durationInitial)
                }
            }
        })
    }

    interface ControlButtons{
        fun playClick(durationTime: Int)
        fun pauseClick()
        fun stopClick()
    }

}