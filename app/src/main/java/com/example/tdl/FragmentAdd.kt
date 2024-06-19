package com.example.tdl

import MyViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tdl.data.Task
import com.example.tdl.data.TaskViewModel
import com.example.tdl.databinding.FragmentAddBinding

class FragmentAdd : Fragment(R.layout.fragment_add) {
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var myViewModel: MyViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        myViewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        val buttonTask = binding.ButtonTask
        val textEdit = binding.textEdit
        val datePicker = binding.datePicker
        val spinnerType = binding.spinnerType
        val savePref = binding.ButtonSavePref

        iniSpinner(spinnerType)

        val sharedPreferences = this.requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        savePref.setOnClickListener {
            val type = spinnerType.selectedItem.toString()
            editor.apply{
                putString("type", type)
                apply()
            }
        }

        buttonTask.setOnClickListener {
            if (!textEdit.text.isEmpty() && textEdit.length() < 20) {
                var day: String = datePicker.dayOfMonth.toString()
                var month: String = (datePicker.month + 1).toString()
                if(datePicker.dayOfMonth < 10) day = "0" + datePicker.dayOfMonth.toString()
                if(datePicker.month < 10) month = "0" + (datePicker.month + 1)
                mTaskViewModel.addUser(
                    Task(
                        0,
                        textEdit.text.toString(),
                        (day + "." + month + "." + datePicker.year),
                        spinnerType.selectedItem.toString()
                    )
                )
                Toast.makeText(context, R.string.SA, Toast.LENGTH_SHORT).show()
                textEdit.setText("")
            } else {
                Toast.makeText(context, R.string.WD, Toast.LENGTH_SHORT).show()
                textEdit.setText("")
            }
        }


        return binding.root
    }

    private fun iniSpinner(spinner: Spinner) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, myViewModel.spinnerValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val selectedIndex = myViewModel.getSelectedIndex(requireContext())
        spinner.setSelection(selectedIndex)
    }
}