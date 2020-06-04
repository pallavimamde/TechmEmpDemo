package com.techmahindra.techmEmployeePortal.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AddEmployeeInfo - add employee info db
 * */

@Entity(tableName = "employee_information")
class AddEmployeeInfo(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var band: String,
    var designation: String,
    var employeeId: String,
    var competency: String,
    var project:String
)