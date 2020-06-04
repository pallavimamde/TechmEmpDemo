package com.techmahindra.techmEmployeePortal.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.data.response.ResponseType
import com.techmahindra.techmEmployeePortal.data.repository.TechmEmployeeRepository
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectInfo
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

/*
* TechmEmployeeViewModel - Viewmodel class to responsible for preparing and managing data(response)
* */
class TechmEmployeeViewModel(@NotNull application: Application) :
    AndroidViewModel(application) {
    private var repositoryViewModel: TechmEmployeeRepository = TechmEmployeeRepository(application)
    var responseType: MutableLiveData<ResponseType> = MutableLiveData<ResponseType>()

    lateinit var projectInfo: ProjectInfo
    var projectInfoLiveData: LiveData<List<ProjectInfo>> = repositoryViewModel.getProjectList()

    var addEmployeeInfoLiveData: LiveData<List<AddEmployeeInfo>> =
        repositoryViewModel.getEployeeList()
    var addEmployeeInfoMutableLiveData: MutableLiveData<AddEmployeeInfo> =
        MutableLiveData<AddEmployeeInfo>()

    // add employee and check response
    fun addEmployeeInfo(employeeInformation: AddEmployeeInfo) = viewModelScope.launch {
        if (repositoryViewModel.addEmployeeInfo(employeeInformation) > 0)
            responseType.value =
                ResponseType(
                    TechmEmployeeApplication.context.resources.getString(R.string.fail),
                    TechmEmployeeApplication.context.resources.getString(R.string.success)
                )
        else
            responseType.value =
                ResponseType(
                    TechmEmployeeApplication.context.resources.getString(R.string.fail),
                    TechmEmployeeApplication.context.resources.getString(R.string.fail)
                )
    }

    // get employee info
    fun getEmployeeInfo() {
        repositoryViewModel.getEployeeList()

    }

    // get employeeinfo by id
    fun getEmployeeInfo(employeeId: String) = viewModelScope.launch {
        addEmployeeInfoMutableLiveData.value = repositoryViewModel.getEmployeeInfo(employeeId)
    }

    fun updateEmployeeInfo(employeeInformation: AddEmployeeInfo) = viewModelScope.launch {
        if (repositoryViewModel.updateEmployeeInfo(employeeInformation) > 0)
            responseType.value =
                ResponseType(
                    TechmEmployeeApplication.context.resources.getString(R.string.fail),
                    TechmEmployeeApplication.context.resources.getString(R.string.success)
                )
        else
            responseType.value =
                ResponseType(
                    TechmEmployeeApplication.context.resources.getString(R.string.fail),
                    TechmEmployeeApplication.context.resources.getString(R.string.fail)
                )
    }

    // delete employee
    fun deleteEmployeeInfo(mAddEmployeeInfo: AddEmployeeInfo) =
        viewModelScope.launch {
            repositoryViewModel.deleteEmployeeInfo(mAddEmployeeInfo)
        }

    //add project to list and roomdb
    fun addProject(projectName: String) = viewModelScope.launch {
        projectInfo = ProjectInfo(0, projectName)
        if (repositoryViewModel.addProject(projectInfo) > 0)
            responseType.value =
                ResponseType(
                    TechmEmployeeApplication.context.resources.getString(R.string.fail),
                    TechmEmployeeApplication.context.resources.getString(R.string.success)
                )
        else
            responseType.value =
                ResponseType(
                    TechmEmployeeApplication.context.resources.getString(R.string.fail),
                    TechmEmployeeApplication.context.resources.getString(R.string.fail)
                )
    }
}