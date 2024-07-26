package com.example.notes2.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes2.App
import com.example.notes2.Interface.OnClickItem
import com.example.notes2.R
import com.example.notes2.data.extension.getBackStackData
import com.example.notes2.data.models.NoteModel
import com.example.notes2.databinding.FragmentNoteBinding
import com.example.notes2.ui.adapters.NoteAdapter
import com.example.notes2.utils.PreferenceHelper


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter
    private var isGridLayout: Boolean = false
    private lateinit var sharedPreferenceHelper: PreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferenceHelper = PreferenceHelper(requireContext())
        isGridLayout = sharedPreferenceHelper.getIsGridLayout()
        noteAdapter = NoteAdapter(onLongClick = this, onClick = this)
        initialize()
        setUpListeners()
        getData()
        updateLayoutButtonIcon()
    }

    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = if (isGridLayout) {
                GridLayoutManager(requireContext(), 2)
            } else {
                LinearLayoutManager(requireContext())
            }
            adapter = noteAdapter
        }
    }

    private fun setUpListeners() = with(binding) {
        addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_NoteDetailFragment)
        }
        isGridLayout.setOnClickListener {
            isGridLayout = true
            binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            sharedPreferenceHelper.setIsGridLayout(isGridLayout)
            updateLayoutButtonIcon()
        }
        linerLayout.setOnClickListener {
            isGridLayout = false
            binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
            sharedPreferenceHelper.setIsGridLayout(isGridLayout)
            updateLayoutButtonIcon()
        }
    }

    private fun updateLayoutButtonIcon() = with(binding) {
        if (isGridLayout) {
            gridLayout.visibility = View.GONE
            linerLayout.visibility = View.VISIBLE
        } else {
            gridLayout.visibility = View.VISIBLE
            linerLayout.visibility = View.GONE
        }
    }

    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить эту заметку?")
            setPositiveButton("Да") { dialog, which ->
                App().getInstance()?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}