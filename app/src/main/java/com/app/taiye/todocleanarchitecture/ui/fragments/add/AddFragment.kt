package com.app.taiye.todocleanarchitecture.ui.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.taiye.todocleanarchitecture.R
import com.app.taiye.todocleanarchitecture.data.Priority
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import com.app.taiye.todocleanarchitecture.ui.viewmodel.SharedViewModel
import com.app.taiye.todocleanarchitecture.ui.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment() : Fragment() {

      private val todoViewModel:ToDoViewModel by viewModels()
      private val sharedViewModel:SharedViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        setHasOptionsMenu(true)
        view.priority_spinner.onItemSelectedListener =  sharedViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        return inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
          insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priority_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle, mDescription)

        if(validation){
            val newData = ToDoData(0,mTitle,sharedViewModel.parsePriority(mPriority),mDescription)
            todoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully Added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Something Went Wrong...",Toast.LENGTH_LONG).show()
        }
    }



}