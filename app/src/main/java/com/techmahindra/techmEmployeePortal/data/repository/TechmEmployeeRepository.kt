package com.techmahindra.techmEmployeePortal.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.techmahindra.techmEmployeePortal.EmployeeDao
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.data.response.CompetencyInfo
import com.techmahindra.techmEmployeePortal.data.response.ProjectInfo
import com.techmahindra.techmEmployeePortal.roomdatabase.CompetencyDao
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectDao
import com.techmahindra.telstra.roomdatabase.TechmEmployeeDb

/*
* TechmEmployeeRepository - This class helps to maintain database call and handle the database response and
*  return response to the viewmodel class
* */
class TechmEmployeeRepository {
    var employeeDao: EmployeeDao
    var projectDao: ProjectDao
    var competencyDao: CompetencyDao
    var projectListLiveData: LiveData<List<ProjectInfo>>
    var employeeListLiveData: LiveData<List<AddEmployeeInfo>>
    var competencyListLiveData: LiveData<List<CompetencyInfo>>

    constructor(application: Application) {
        var techmEmployeeDb = TechmEmployeeDb.invoke(application)
        employeeDao = techmEmployeeDb.getEmployeeDao()
        projectDao = techmEmployeeDb.getProjectDao()
        competencyDao = techmEmployeeDb.getCompetencyDao()
        projectListLiveData = projectDao.getProjectList()
        competencyListLiveData = competencyDao.getCompetencyList()
        employeeListLiveData = employeeDao.getTechmEmployeeList()
    }

    //get project list
    fun getProjectList(): LiveData<List<ProjectInfo>> {
        return projectListLiveData
    }

    //get competency list
    fun getCompetencyList(): LiveData<List<CompetencyInfo>> {
        return competencyListLiveData
    }

    //add project to db
    suspend fun addProject(projectInfo: ProjectInfo): Long {
        return projectDao.insertProject(projectInfo)
    }

    //add competency to db
    suspend fun addCompetencies(competencyInfo: CompetencyInfo): Long {
        return competencyDao.insertCompetency(competencyInfo)
    }

    //add employee to employee_info db
    suspend fun addEmployeeInfo(addEmployeeInfo: AddEmployeeInfo): Long {
        return employeeDao.addEmployee(addEmployeeInfo)
    }

    // delete employee record
    suspend fun deleteEmployeeInfo(addEmployeeInfo: AddEmployeeInfo): Int {
        return employeeDao.deleteTechmEmployee(addEmployeeInfo)
    }

    // delete project
    suspend fun deleteProject(projectInfo: ProjectInfo): Int {
        return projectDao.deleteProjectRecord(projectInfo)
    }


    // update employee info
    suspend fun updateEmployeeInfo(addEmployeeInfo: AddEmployeeInfo): Int {
        return employeeDao.updateTechmEmployee(addEmployeeInfo)
    }

    // get employee list
    fun getEployeeList(): LiveData<List<AddEmployeeInfo>> {
        return employeeListLiveData
    }

    //get employee info
    suspend fun getEmployeeInfo(id: String): AddEmployeeInfo {
        return employeeDao.findByEmployeeId(id)
    }

}
