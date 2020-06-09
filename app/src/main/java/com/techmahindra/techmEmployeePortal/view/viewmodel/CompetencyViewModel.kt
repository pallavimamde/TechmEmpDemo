package com.techmahindra.techmEmployeePortal.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.core.TechmEmployeeApplication
import com.techmahindra.techmEmployeePortal.data.repository.TechmEmployeeRepository
import com.techmahindra.techmEmployeePortal.data.response.CompetencyInfo
import com.techmahindra.techmEmployeePortal.data.response.ResponseType
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull


/*
* CompetencyViewModel - Viewmodel class to responsible for preparing and managing data(response)
* */
class CompetencyViewModel(@NotNull application: Application) :
    AndroidViewModel(application) {
    private var repositoryViewModel: TechmEmployeeRepository = TechmEmployeeRepository(application)
    var responseType: MutableLiveData<ResponseType> = MutableLiveData<ResponseType>()

    lateinit var competencyInfo: CompetencyInfo
    var competencyInfoLiveData: LiveData<List<CompetencyInfo>> = repositoryViewModel.getCompetencyList()


    //add project to list and roomdb
    fun addCompetency(name: String) = viewModelScope.launch {
        competencyInfo =
            CompetencyInfo(
                0,
                name
            )
        if (repositoryViewModel.addCompetencies(competencyInfo) > 0)
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