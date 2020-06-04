package com.techmahindra.techmEmployeePortal.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.view.adapter.ProjectSpinnerAdapter
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectInfo
import com.techmahindra.techmEmployeePortal.utils.AppConstant
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import kotlinx.android.synthetic.main.activity_add_employee.*

class AddTechmEmployeeActivity : AppCompatActivity() {
    private lateinit var builder: AlertDialog.Builder
    private lateinit var techmEmployeeViewModel: TechmEmployeeViewModel
    private lateinit var projectSpinnerAdapter: ProjectSpinnerAdapter
    private lateinit var dialog: AlertDialog
    private var intentExtra: Int = 0
    var editProjectName = ""
    private var project = ""
    var employeeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        supportActionBar?.title = getString(R.string.add_employee)

        initView()

    }

    //initialize the params
    private fun initView() {
        techmEmployeeViewModel = ViewModelProvider(this).get(TechmEmployeeViewModel::class.java)
        intentExtra = intent.getIntExtra(AppConstant.readyToUpdate, 0)
        etv_add_id.keyListener = null
        etv_add_id.isEnabled = false
        if (intentExtra == 0)
            btn_emp_edit.visibility = View.GONE
        else {
            employeeId = intent.getIntExtra(AppConstant.data, 0)
            btn_emp_edit.visibility = View.VISIBLE
            btn_add_emp_submit.isEnabled = false
            fillFeatureData(employeeId)
            disableViews()
        }
        img_btn_add_project.setOnClickListener {
            var intent = Intent(this, ProjectActivity::class.java)
            startActivity(intent)
        }
        btn_emp_edit.setOnClickListener {
            enableView()
            btn_emp_edit.isEnabled = false
        }
        btn_add_emp_submit.setOnClickListener {
            saveEmployeeInfo()
        }
        setupProgressDialog()
        loadProjectAdpater()

        techmEmployeeViewModel.addEmployeeInfoMutableLiveData.observe(this, Observer {
            displayEmployeeInfo(it)
        })

        techmEmployeeViewModel.responseType.observe(this, Observer {
            hideProgressDialog()
            when (it.status) {

            }
        })
    }

    // set adapter
    private fun loadProjectAdpater() {
        projectSpinnerAdapter = ProjectSpinnerAdapter(this, ArrayList())
        spinner_emp_project.adapter = projectSpinnerAdapter

        techmEmployeeViewModel.projectInfoLiveData.observe(this, Observer {
            projectSpinnerAdapter.setLIst(it as ArrayList<ProjectInfo>)
            if (intentExtra != 0) {
                var position = projectSpinnerAdapter.getPosition(editProjectName)
                spinner_emp_project.setSelection(position)
            }
        })

        spinner_emp_project.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                project = projectSpinnerAdapter.getItem(position).projectName
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun fillFeatureData(employeeId: Int) {
        techmEmployeeViewModel.getEmployeeInfo("" + employeeId)
    }

    // clear fields
    private fun clearText() {
        etv_add_emp_name.setText("")
        etv_add_emp_band.setText("")
        etv_add_emp_designation.setText("")
        etv_add_emp_id.setText("")
        etv_add_id.setText("")
        rg_emp_competency.clearCheck()
    }

    // display employee info
    private fun displayEmployeeInfo(employee: AddEmployeeInfo) {
        etv_add_emp_name.setText(employee.name)
        etv_add_emp_band.setText(employee.band)
        etv_add_emp_designation.setText(employee.designation)
        etv_add_emp_id.setText(employee.employeeId)
        etv_add_id.setText("" + employee.id)
        when (employee.competency) {
            getString(R.string.android) ->
                radio_android.isSelected = true
            getString(R.string.ios) ->
                radio_ios.isSelected = true
            getString(R.string.ux) ->
                radio_ux.isSelected = true
            getString(R.string.tester) ->
                radio_ux.isSelected = true
        }
        editProjectName = employee.project


    }

    // Save employee info to room db
    private fun saveEmployeeInfo() {
        val employeeName = etv_add_emp_name.text.toString()
        val employeeBand = etv_add_emp_band.text.toString()
        val employeeDesignation = etv_add_emp_designation.text.toString()
        val textFieldEmployeeID = etv_add_emp_id.text.toString()

        var employeeCompetency: String =
            findViewById<RadioButton>(rg_emp_competency.checkedRadioButtonId).text.toString()

        when {
            TextUtils.isEmpty(employeeName) -> {
                tv_add_emp_error.error = getString(R.string.input_required)
                tv_add_emp_error.visibility = View.VISIBLE
            }
            TextUtils.isEmpty(employeeBand) -> {
                tv_add_emp_error.error = getString(R.string.input_required)
                tv_add_emp_error.visibility = View.VISIBLE
            }
            TextUtils.isEmpty(employeeDesignation) -> {
                tv_add_emp_error.error = getString(R.string.input_required)
                tv_add_emp_error.visibility = View.VISIBLE
            }
            TextUtils.isEmpty(textFieldEmployeeID) -> {
                tv_add_emp_error.error = getString(R.string.input_required)
                tv_add_emp_error.visibility = View.VISIBLE
            }

            (project == "" || project == getString(R.string.select_project)) -> {
                Toast.makeText(
                    TechmEmployeeApplication.context,
                    getString(R.string.please_select_project),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                var mModelEmployeeRegistration =
                    AddEmployeeInfo(
                        0,
                        employeeName,
                        employeeBand,
                        employeeDesignation,
                        textFieldEmployeeID,
                        employeeCompetency,
                        project
                    )
                if (intentExtra == 0)
                    techmEmployeeViewModel.addEmployeeInfo(mModelEmployeeRegistration)
                else {
                    mModelEmployeeRegistration.id = etv_add_id.text.toString().toInt()
                    techmEmployeeViewModel.updateEmployeeInfo(mModelEmployeeRegistration)
                }
                clearText()
                project = ""
            }
        }
    }

    // enable view to update info
    private fun enableView() {
        etv_add_emp_name.isEnabled = true
        etv_add_emp_band.isEnabled = true
        etv_add_emp_id.isEnabled = true
        etv_add_emp_designation.isEnabled = true
        rg_emp_competency.isEnabled = true
        spinner_emp_project.isEnabled = true
        img_btn_add_project.isEnabled = true
        btn_add_emp_submit.isEnabled = true
    }

    // disable view
    private fun disableViews() {
        etv_add_emp_name.isEnabled = false
        etv_add_emp_band.isEnabled = false
        etv_add_emp_id.isEnabled = false
        etv_add_emp_designation.isEnabled = false
        rg_emp_competency.isEnabled = false
        spinner_emp_project.isEnabled = false
        img_btn_add_project.isEnabled = false
        btn_add_emp_submit.isEnabled = true
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupProgressDialog() {
        builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    private fun showProgressDialog() {
        if (dialog != null && !dialog.isShowing) {
            dialog.show()
        }
    }

    private fun hideProgressDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.hide()
            dialog.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog.isShowing) {
            dialog.hide()
            dialog.dismiss()
        }
    }


}
