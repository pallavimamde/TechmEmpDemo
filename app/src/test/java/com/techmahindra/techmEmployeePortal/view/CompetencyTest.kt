package com.techmahindra.techmEmployeePortal.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techmahindra.techmEmployeePortal.data.repository.TechmEmployeeRepository
import com.techmahindra.techmEmployeePortal.data.response.CompetencyInfo
import com.techmahindra.techmEmployeePortal.view.viewmodel.CompetencyViewModel
import com.techmahindra.techmEmployeePortal.view.viewmodel.ProjectViewModel
import com.techmahindra.techmEmployeePortal.view.viewmodel.TechmEmployeeViewModel
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CompetencyTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModelCompetency: CompetencyViewModel
    lateinit var repositoryViewModel: TechmEmployeeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val application = Mockito.mock(Application::class.java)
        this.repositoryViewModel = TechmEmployeeRepository(application)
        this.viewModelCompetency = CompetencyViewModel(application)
    }
    // This test should be success because we will get success response from add competency API
    @Test
    suspend fun test_addCompetencyQuerySuccess() {
        Mockito.`when`(this.repositoryViewModel.getProjectList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<CompetencyViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<CompetencyInfo>>
        this.viewModelCompetency.competencyInfoLiveData.observeForever(observer)

        this.viewModelCompetency.competencyInfoLiveData
        Thread.sleep(10000)
        Assert.assertNotNull(this.viewModelCompetency.competencyInfoLiveData.value)
    }

    // This test should be fail because we will get success response from add competency API
    @Test
    suspend fun test_addCompetencyQueryError() {
        Mockito.`when`(this.repositoryViewModel.getCompetencyList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<CompetencyViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<CompetencyInfo>>
        this.viewModelCompetency.competencyInfoLiveData.observeForever(observer)

        this.viewModelCompetency.competencyInfoLiveData
        Thread.sleep(10000)
        Assert.assertNull(this.viewModelCompetency.competencyInfoLiveData.value)
    }

}