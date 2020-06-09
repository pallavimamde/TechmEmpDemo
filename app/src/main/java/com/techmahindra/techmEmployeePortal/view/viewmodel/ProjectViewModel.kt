package com.techmahindra.techmEmployeePortal.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.data.repository.TechmEmployeeRepository
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.data.response.CompetencyInfo
import com.techmahindra.techmEmployeePortal.data.response.ProjectInfo
import com.techmahindra.techmEmployeePortal.data.response.ResponseType
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

/*
* ProjectViewModel - Viewmodel class to responsible for preparing and managing data(response)
* */
class ProjectViewModel(@NotNull application: Application) :
    AndroidViewModel(application) {
    private var repositoryViewModel: TechmEmployeeRepository = TechmEmployeeRepository(application)
    var responseType: MutableLiveData<ResponseType> = MutableLiveData<ResponseType>()

    lateinit var projectInfo: ProjectInfo
    var projectInfoLiveData: LiveData<List<ProjectInfo>> = repositoryViewModel.getProjectList()

    // delete project
    fun deleteEmployeeInfo(projectInfo: ProjectInfo) =
        viewModelScope.launch {
            repositoryViewModel.deleteProject(projectInfo)
        }

    //add project to list and roomdb
    fun addProject(projectName: String) = viewModelScope.launch {
        projectInfo =
            ProjectInfo(
                0,
                projectName
            )
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