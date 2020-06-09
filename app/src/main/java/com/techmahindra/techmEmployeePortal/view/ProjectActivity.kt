package com.techmahindra.techmEmployeePortal.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.view.adapter.AdapterProject
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.utils.SwipeToDeleteCallback
import com.techmahindra.techmEmployeePortal.view.viewmodel.ProjectViewModel
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : AppCompatActivity() {
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var adapterProject: AdapterProject
    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        var deleteProject: Int = 0
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        supportActionBar?.title = getString(R.string.add_project)
        initView()

    }

    // initialize the views
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {
        loadProjectAdpter()
        buttonProjectAdd.setOnClickListener {
            if (editTextProjectNam.text.toString().isNotEmpty()) {
                projectViewModel.addProject(editTextProjectNam.text.toString())
                editTextProjectNam.setText("")
            }
        }
        swipeDelete()
    }

    // Delete recyclerview item on swipe LEFT or RIGHT
    @RequiresApi(Build.VERSION_CODES.M)
    private fun swipeDelete() {
        val swipeHandler = object : SwipeToDeleteCallback(TechmEmployeeApplication.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (adapterProject as AdapterProject).removeAt(viewHolder.adapterPosition)
                deleteEmployee()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(projectInfoList)

    }

    // Delete api will call when nwe delete item by swipe
    private fun deleteEmployee() {
     /*   var addEmployeeInfo = adapterProject.getItemAtPosition(viewHolder.adapterPosition)
        adapterProject.delete(viewHolder.adapterPosition)
        techmEmployeeViewModel.deleteEmployeeInfo(addEmployeeInfo)*/
    }

    // set observer viewmodel input data to adapter
    private fun loadProjectAdpter() {
        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        adapterProject = AdapterProject(ArrayList(), TechmEmployeeApplication.context)
        linearLayoutManager = LinearLayoutManager(this)
        projectInfoList.layoutManager = linearLayoutManager
        projectInfoList.adapter = adapterProject
        projectViewModel.responseType.observe(this, Observer {
            if (it.status.equals(TechmEmployeeApplication.context.resources.getString(R.string.success))) {
                Toast.makeText(TechmEmployeeApplication.context, it.status, Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(TechmEmployeeApplication.context, it.error, Toast.LENGTH_SHORT)
                    .show()
            }

        })
        projectViewModel.projectInfoLiveData.observe(this, Observer {
            adapterProject.loadProductlistInfo(it)
        })
    }


}
