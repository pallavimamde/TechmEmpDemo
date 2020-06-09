package com.techmahindra.techmEmployeePortal.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.view.adapter.AdapterEmployeeInformation
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication.Companion.context
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.utils.*
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import kotlinx.android.synthetic.main.activity_techm_employee_list.*

class TechmEmployeeListActivity : AppCompatActivity(),
    AdapterEmployeeInformation.ItemClickListener {
    private lateinit var techmEmployeeViewModel: TechmEmployeeViewModel
    private lateinit var adapterEmployeeInformation: AdapterEmployeeInformation
    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        var modifiedEmployeeList: MutableList<AddEmployeeInfo> = ArrayList()
        var deleteEmp: Int = -1
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_techm_employee_list)
        initView()
        supportActionBar?.title = getString(R.string.employee_information)

    }

    // initialize the view
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {
        swipeToRefresh.setOnRefreshListener {
            updateEmployeeInfoAdpater()
            swipeToRefresh.isRefreshing = false
        }
        swipeDeleteEmployee()
        searchFilter()
        updateEmployeeInfoAdpater()
    }

    // Delete recyclerview item on swipe LEFT or RIGHT
    @RequiresApi(Build.VERSION_CODES.M)
    private fun swipeDeleteEmployee() {
        val swipeHandler = object : SwipeToDeleteCallback(TechmEmployeeApplication.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (adapterEmployeeInformation as AdapterEmployeeInformation).removeAt(viewHolder.adapterPosition)
                deleteEmployee()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(employeeList)

    }

    // Delete api will call when nwe delete item by swipe
    @RequiresApi(Build.VERSION_CODES.M)
    private fun deleteEmployee() {

        techmEmployeeViewModel.deleteEmployeeInfo(modifiedEmployeeList[deleteEmp])
    }

    // create adapter and set data to adpater
    private fun updateEmployeeInfoAdpater() {
        techmEmployeeViewModel = ViewModelProvider(this).get(TechmEmployeeViewModel::class.java)
        adapterEmployeeInformation = AdapterEmployeeInformation(ArrayList(), context, this)
        linearLayoutManager = LinearLayoutManager(this)
        employeeList.layoutManager = linearLayoutManager
        employeeList.adapter = adapterEmployeeInformation

        techmEmployeeViewModel.addEmployeeInfoLiveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                modifiedEmployeeList = it.toMutableList()
                adapterEmployeeInformation.loadTechmEmployeeInfo(modifiedEmployeeList)
            }
        })
    }


    // Search employee by employee name. Filter checks list as we type each and every char
    private fun searchFilter() {
        searchViewEmployee.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (adapterEmployeeInformation as AdapterEmployeeInformation).filter.filter(newText)
                return false
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_add -> {
                var intent = Intent(this, AddTechmEmployeeActivity::class.java)
                intent.putExtra(AppConstant.readyToUpdate, 0)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(addEmployeeInfo: AddEmployeeInfo, position: Int) {
        var intent = Intent(this, AddTechmEmployeeActivity::class.java)
        intent.putExtra(AppConstant.readyToUpdate, 1)
        intent.putExtra(AppConstant.data, addEmployeeInfo.employeeId)
        startActivity(intent)
    }


}
