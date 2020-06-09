package com.techmahindra.techmEmployeePortal

import androidx.lifecycle.LiveData
import androidx.room.*

import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo

/**
 * EmployeeDao - handle operations on employee_information roomdatabase, like select, insert, delete
 */
@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee_information")
    fun getTechmEmployeeList(): LiveData<List<AddEmployeeInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employee: AddEmployeeInfo): Long

    @Insert
    suspend fun addTechmEmployeeList(employeeList: ArrayList<AddEmployeeInfo>?)

    @Query("SELECT * FROM employee_information WHERE employeeId = :id")
    suspend fun findByEmployeeId(id: String?): AddEmployeeInfo

    @Update
    suspend fun updateTechmEmployee(todo: AddEmployeeInfo): Int

    @Delete
    suspend fun deleteTechmEmployee(employee: AddEmployeeInfo): Int

    @Query("DELETE FROM employee_information")
    suspend fun deleteTechmEmployeeList()



}