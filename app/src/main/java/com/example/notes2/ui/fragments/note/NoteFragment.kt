package com.example.notes2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes2.R
import com.example.notes2.data.extension.getBackStackData
import com.example.notes2.data.models.NoteModel
import com.example.notes2.databinding.FragmentNoteBinding
import com.example.notes2.ui.adapters.NoteAdapter


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter
    private var list: ArrayList<NoteModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAdapter = NoteAdapter()
        initialize()
        setUpListeners()
        getData()
    }

    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setUpListeners() = with(binding) {
        addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        getBackStackData<String>("key") { data ->
            val noteModel = NoteModel(data)
            list.add(noteModel)
            noteAdapter.submitList(list)
        }
    }
}