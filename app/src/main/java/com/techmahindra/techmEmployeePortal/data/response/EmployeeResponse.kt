package com.techmahindra.techmEmployeePortal.data.response


/**
 * EmployeeResponse - response type while performing operation on employee data
 * */
class EmployeeResponse(
    var data: ArrayList<AddEmployeeInfo>,
    var error: String = "",
    var responseType: ResponseType
)