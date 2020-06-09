package com.techmahindra.techmEmployeePortal.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* CompetencyInfo -  competency data object
* */
@Entity(tableName = "competencies")
class CompetencyInfo(
    @PrimaryKey(autoGenerate = true)
    var competencyId:Int,
    var competencyName:String
)