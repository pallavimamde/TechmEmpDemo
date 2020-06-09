package com.techmahindra.techmEmployeePortal.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techmahindra.techmEmployeePortal.data.response.CompetencyInfo
import com.techmahindra.techmEmployeePortal.data.response.ProjectInfo

/**
 * CompetencyDao - competency roomdb to get project data
 */
@Dao
interface CompetencyDao {
    @Query("SELECT * FROM competencies")
    fun getCompetencyList(): LiveData<List<CompetencyInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetency(competencyInfo: CompetencyInfo): Long

}