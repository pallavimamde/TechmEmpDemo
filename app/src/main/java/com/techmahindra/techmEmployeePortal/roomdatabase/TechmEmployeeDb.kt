package com.techmahindra.telstra.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techmahindra.techmEmployeePortal.EmployeeDao
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectInfo
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectDao

/**
 * TechmEmployeeDb -  Create the roomdb for employeeinfo and project
 */
@Database(entities = [AddEmployeeInfo::class, ProjectInfo::class], version = 4)
abstract class TechmEmployeeDb : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
    abstract fun getProjectDao(): ProjectDao

    companion object {
        @Volatile
        private var instance: TechmEmployeeDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        // build employee_database room database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            TechmEmployeeDb::class.java, "employee_database.db"
        )
            .fallbackToDestructiveMigration()// update version while changing schema
            .build()
    }
}