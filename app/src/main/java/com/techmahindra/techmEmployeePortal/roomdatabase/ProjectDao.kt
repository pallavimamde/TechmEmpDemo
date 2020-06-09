package com.techmahindra.techmEmployeePortal.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.data.response.ProjectInfo

/**
 * ProjectDao - project roomdb to get project data
 */
@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    fun getProjectList(): LiveData<List<ProjectInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(projectInfo: ProjectInfo): Long

    @Delete
    suspend fun deleteProjectRecord(projectInfo: ProjectInfo): Int
}