package com.techmahindra.techmEmployeePortal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.view.adapter.AdapterEmployeeInformation
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication.Companion.context
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.utils.*
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import kotlinx.android.synthetic.main.activity_techm_employee_list.*

class TechmEmployeeListActivity : AppCompatActivity(), View.OnClickListener,
    AdapterEmployeeInformation.ItemClickListener {
    private lateinit var techmEmployeeViewModel: TechmEmployeeViewModel
    private lateinit var mAdapter: AdapterEmployeeInformation
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_techm_employee_list)
        initView()
        supportActionBar?.title = getString(R.string.employee_information)

    }

    // initialize the view
    private fun initView() {
        searchFilter()
        updateEmployeeInfoAdpater()
    }

    // create adapter and set data to adpater
    private fun updateEmployeeInfoAdpater() {
        techmEmployeeViewModel = ViewModelProvider(this).get(TechmEmployeeViewModel::class.java)
        mAdapter = AdapterEmployeeInformation(ArrayList(), context, this)
        linearLayoutManager = LinearLayoutManager(this)
        employeeList.layoutManager = linearLayoutManager
        employeeList.adapter = mAdapter
        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var mModelEmployeeInformation =
                    mAdapter.getItemAtPosition(viewHolder.adapterPosition)
                mAdapter.delete(viewHolder.adapterPosition)
                techmEmployeeViewModel.deleteEmployeeInfo(mModelEmployeeInformation)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(employeeList)

        techmEmployeeViewModel.addEmployeeInfoLiveData.observe(this, Observer {

            mAdapter.loadTechmEmployeeInfo(it)
        })
    }

    // search list item
    private fun searchFilter() {
        searchView.queryHint = getString(R.string.search)

        swipeToRefresh.setOnRefreshListener {
            techmEmployeeViewModel.getEmployeeInfo()
            swipeToRefresh.isRefreshing = false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newText.let { mAdapter.filter(it.trimStart()) }
                return true
            }
        })
    }

    override fun onClick(v: View?) {

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

    override fun onItemClick(mAddEmployeeInfo: AddEmployeeInfo, position: Int) {
        var intent = Intent(this, AddTechmEmployeeActivity::class.java)
        intent.putExtra(AppConstant.readyToUpdate, 1)
        intent.putExtra(AppConstant.data, mAddEmployeeInfo.id)
        startActivity(intent)
    }


}
