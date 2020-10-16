package com.app.taiye.todocleanarchitecture.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.taiye.todocleanarchitecture.R
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view  = inflater.inflate(R.layout.fragment_list, container, false)
      view.floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
      }

        return view
    }


}