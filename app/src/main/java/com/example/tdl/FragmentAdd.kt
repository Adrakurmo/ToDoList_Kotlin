package com.example.tdl

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tdl.data.Task
import com.example.tdl.data.TaskViewModel

class FragmentAdd : Fragment(R.layout.fragment_add) {
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val buttonTask = view.findViewById<Button>(R.id.ButtonTask)
        val textEdit = view.findViewById<EditText>(R.id.textEdit)
        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        val spinnerType = view.findViewById<Spinner>(R.id.spinnerType)
        val savePref = view.findViewById<Button>(R.id.ButtonSavePref)

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
                Toast.makeText(context, "Successfully added!", Toast.LENGTH_SHORT).show()
                textEdit.setText("")
            } else {
                Toast.makeText(context, "Wrong Data!", Toast.LENGTH_SHORT).show()
                textEdit.setText("")
            }
        }


        return view
    }

    private fun iniSpinner(spinner: Spinner) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mainActivityViewModel.spinnerValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val sharedPreferences = this.requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val selectedType = sharedPreferences.getString("type", mainActivityViewModel.spinnerValues[0])
        val selectedIndex = mainActivityViewModel.spinnerValues.indexOf(selectedType)
        spinner.setSelection(selectedIndex)
    }
}