package com.app.taiye.todocleanarchitecture.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.taiye.todocleanarchitecture.R
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.util.zip.Inflater


class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


      val view  = inflater.inflate(R.layout.fragment_list, container, false)
      view.floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
      }
      view.listLayout.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_updateFragment)
      }


        //set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }
}