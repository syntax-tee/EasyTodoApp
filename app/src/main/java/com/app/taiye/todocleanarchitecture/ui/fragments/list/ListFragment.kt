package com.app.taiye.todocleanarchitecture.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.taiye.todocleanarchitecture.R
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import com.app.taiye.todocleanarchitecture.databinding.FragmentListBinding
import com.app.taiye.todocleanarchitecture.ui.viewmodel.SharedViewModel
import com.app.taiye.todocleanarchitecture.ui.viewmodel.ToDoViewModel
import com.app.taiye.todocleanarchitecture.util.SwipeToDelete
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {

    private val adapter: ListAdapter by lazy { ListAdapter() }
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private var _binding :FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //data binding
        _binding = FragmentListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = sharedViewModel

        setUpRecyclerView()

        displayView()

        //set menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> confirmDeleteAllNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayView() {
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            sharedViewModel.checkIfEmptyDatabase(data)
            adapter.setData(data)
        })
    }

    private fun setUpRecyclerView(){
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        swipeToDelete(recyclerView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallBack = object: SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                mToDoViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeleteData(viewHolder.itemView,deletedItem,viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private fun restoreDeleteData(view: View,deletedItem: ToDoData,position:Int){
      val snackBar = Snackbar.make(view,"Deleted '${deletedItem.title}',",Snackbar.LENGTH_LONG)

        snackBar.setAction("Undo"){mToDoViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }
        snackBar.show()
    }



    //Show alert dialog to remove items
    private fun confirmDeleteAllNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteAllNote()
            Toast.makeText(requireContext(), "Notes Successfully Deleted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete all items?")
        builder.setMessage("Are you sure you want to remove all notes?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}