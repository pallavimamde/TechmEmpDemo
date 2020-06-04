package com.techmahindra.techmEmployeePortal.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techmahindra.techmEmployeePortal.data.response.ResponseType
import com.techmahindra.techmEmployeePortal.data.repository.TechmEmployeeRepository
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectInfo
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AddEmployeeTest
{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var viewModelAddEmployee: TechmEmployeeViewModel

    lateinit var repositoryViewModel: TechmEmployeeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val application = Mockito.mock(Application::class.java)
        this.repositoryViewModel = TechmEmployeeRepository(application)
        this.viewModelAddEmployee = TechmEmployeeViewModel(application)
    }
    @Test
    suspend fun test_getSingleEmployeeQuerySuccess() {
        Mockito.`when`(this.repositoryViewModel.getEmployeeInfo("1")).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<TechmEmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<ResponseType>
        this.viewModelAddEmployee.responseType.observeForever(observer)

        this.viewModelAddEmployee.getEmployeeInfo("1")
        Thread.sleep(10000)
        assertNotNull(this.viewModelAddEmployee.responseType.value)
    }
    /**
     * This test should be fail because we will get success response from API
     * */

    @Test
    suspend fun test_getSingleEmployeeQueryError() {
        Mockito.`when`(this.repositoryViewModel.getEmployeeInfo("1")).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<TechmEmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<ResponseType>
        this.viewModelAddEmployee.responseType.observeForever(observer)

        this.viewModelAddEmployee.getEmployeeInfo("1")
        Thread.sleep(10000)

        assertNull(this.viewModelAddEmployee.responseType.value)
    }

    @Test
    suspend fun test_getProjectQuerySuccess() {
        Mockito.`when`(this.repositoryViewModel.getProjectList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<TechmEmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<ProjectInfo>>
        this.viewModelAddEmployee.projectInfoLiveData.observeForever(observer)

        this.viewModelAddEmployee.projectInfoLiveData
        Thread.sleep(10000)
        assertNotNull(this.viewModelAddEmployee.projectInfoLiveData.value)
    }
    /**
     * This test should be fail because we will get success response from API
     * */

    @Test
    suspend fun test_getProjectQueryError() {
        Mockito.`when`(this.repositoryViewModel.getProjectList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<TechmEmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<ProjectInfo>>
        this.viewModelAddEmployee.projectInfoLiveData.observeForever(observer)

        this.viewModelAddEmployee.projectInfoLiveData
        Thread.sleep(10000)
        assertNull(this.viewModelAddEmployee.projectInfoLiveData.value)
    }


}