package com.app.taiye.todocleanarchitecture.fragments.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.app.taiye.todocleanarchitecture.R

class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        return inflater.inflate(R.menu.add_fragment_menu,menu)
    }
}