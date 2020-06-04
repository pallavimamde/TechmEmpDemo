package com.techmahindra.techmEmployeePortal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.view.adapter.AdapterProject
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : AppCompatActivity() {
    private lateinit var techmEmployeeViewModel: TechmEmployeeViewModel
    private lateinit var mAdapter: AdapterProject
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        supportActionBar?.title = getString(R.string.add_project)
        initView()

    }

    // initialize the views
    private fun initView() {
        loadProjectAdpter()
        buttonAdd.setOnClickListener {
            if (projectName.text.toString().isNotEmpty()) {
                techmEmployeeViewModel.addProject(projectName.text.toString())
                projectName.setText("")
            }
        }


    }

    // set observer viewmodel input data to adapter
    private fun loadProjectAdpter() {
        techmEmployeeViewModel = ViewModelProvider(this).get(TechmEmployeeViewModel::class.java)
        mAdapter = AdapterProject(ArrayList(), TechmEmployeeApplication.context)
        linearLayoutManager = LinearLayoutManager(this)
        projectInfoList.layoutManager = linearLayoutManager
        projectInfoList.adapter = mAdapter
        techmEmployeeViewModel.responseType.observe(this, Observer {

            when (it.status) {

            }
        })
        techmEmployeeViewModel.projectInfoLiveData.observe(this, Observer {
            mAdapter.loadProductlistInfo(it)
        })
    }


}
