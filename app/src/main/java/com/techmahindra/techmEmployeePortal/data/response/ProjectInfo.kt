package com.techmahindra.techmEmployeePortal.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* ProjectInfo -  project data object
* */
@Entity(tableName = "projects")
class ProjectInfo(
    @PrimaryKey(autoGenerate = true)
    var projectId:Int,
    var projectName:String
)