package com.techmahindra.techmEmployeePortal.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * ProjectDao - project roomdb to get project data
 */
@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    fun getProjectList(): LiveData<List<ProjectInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(projectInfo: ProjectInfo): Long

}