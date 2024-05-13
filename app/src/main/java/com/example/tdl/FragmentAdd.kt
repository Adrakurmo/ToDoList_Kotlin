package com.example.tdl

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
import com.example.tdl.data.User
import com.example.tdl.data.UserViewModel
import com.example.tdl.databinding.ActivityMainBinding
import com.example.tdl.databinding.FragmentAddBinding

class FragmentAdd : Fragment(R.layout.fragment_add) {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        val buttonTask = view.findViewById<Button>(R.id.ButtonTask)
        val textEdit = view.findViewById<EditText>(R.id.textEdit)
        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        val spinnerType = view.findViewById<Spinner>(R.id.spinnerType)

        iniSPinner(spinnerType)

        buttonTask.setOnClickListener {
            if (!textEdit.text.isEmpty() && textEdit.length() < 20) {
                var day: String = datePicker.dayOfMonth.toString()
                var month: String = (datePicker.month + 1).toString()
                if(datePicker.dayOfMonth < 10) day = "0" + datePicker.dayOfMonth.toString()
                if(datePicker.month < 10) month = "0" + (datePicker.month + 1)
                mUserViewModel.addUser(
                    User(
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

    private fun iniSPinner(spinner: Spinner) {
        val spinnerValues = arrayOf("Task", "Sport", "Work", "School", "Doctor")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}