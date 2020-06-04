package com.techmahindra.techmEmployeePortal.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techmahindra.techmEmployeePortal.data.repository.TechmEmployeeRepository
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.SocketException

class EmployeeFeatureActivityTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var viewModelEmployeeInformation: TechmEmployeeViewModel

    lateinit var techmEmployeeRepository: TechmEmployeeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val application = Mockito.mock(Application::class.java)
        this.techmEmployeeRepository = TechmEmployeeRepository(application)
        this.viewModelEmployeeInformation = TechmEmployeeViewModel(application)
    }
    @Test
    fun test_getEmployeeInformationQuerySuccess() {
        Mockito.`when`(this.techmEmployeeRepository.getEployeeList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<TechmEmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<AddEmployeeInfo>>
        this.viewModelEmployeeInformation.addEmployeeInfoLiveData.observeForever(observer)

        this.viewModelEmployeeInformation.getEmployeeInfo()
        Thread.sleep(10000)
        assertNotNull(this.viewModelEmployeeInformation.addEmployeeInfoLiveData.value)
    }
    /**
     * This test should be fail because we will get success response from API
     * */
    @Test
    fun test_getEmployeeInformationQueryError() {
        Mockito.`when`(this.techmEmployeeRepository.getEployeeList()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<AddEmployeeInfo>>
        this.viewModelEmployeeInformation.addEmployeeInfoLiveData.observeForever(observer)

        this.viewModelEmployeeInformation.getEmployeeInfo()
        Thread.sleep(10000)
        assertNull(this.viewModelEmployeeInformation.addEmployeeInfoLiveData.value)
    }
}