package tn.oussama.mp_android_bonita.presentation.process

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tn.oussama.core.data.CategoryRepository
import tn.oussama.core.data.DataState
import tn.oussama.core.data.ProcessRepository
import tn.oussama.core.domain.Category
import tn.oussama.core.domain.Process
import tn.oussama.core.interactors.GetListCategories
import tn.oussama.core.interactors.GetListProcesses
import tn.oussama.core.interactors.GetListProcessesByCategory
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkCategoryDataSource
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkProcessDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.CategoryNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.CategoryRetrofit
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit

import javax.inject.Inject

@HiltViewModel
class ListProcessesActivityViewModel @Inject constructor(
    private val processRetrofit: ProcessRetrofit ,
    private val processNetworkMapper: ProcessNetworkMapper,
    private val categoryRetrofit: CategoryRetrofit,
    private val categoryNetworkMapper: CategoryNetworkMapper
): ViewModel()
{
    private val listOfProcesses: MutableLiveData<DataState<List<Process>>> = MutableLiveData()
    private val listOfCategories: MutableLiveData<DataState<List<Category>>> = MutableLiveData()

    fun getListOfProcesses(): LiveData<DataState<List<Process>>> = listOfProcesses
    fun getListOfCategories(): LiveData<DataState<List<Category>>> = listOfCategories
    //fun getListOfProcessesByCategory(): LiveData<DataState<List<Process>>> = listOfProcesses


    fun setListProcessStateEvent(e:ListProcessStateEvent,userId:Long,pageIndex:Int=0,perPage:Int=20, categID: Long=0) {
        viewModelScope.launch {
            when(e) {
                is ListProcessStateEvent.GetProcessesEvent -> {
                    val getListProcess = GetListProcesses(ProcessRepository(NetworkProcessDataSource(processRetrofit,processNetworkMapper)))
                    getListProcess(userId,pageIndex=pageIndex,perPage=perPage).onEach {
                        dataState ->  listOfProcesses.value = dataState
                    }.launchIn(viewModelScope)
                }
                is ListProcessStateEvent.GetCategoriesEvent -> {
                    val getListCategories = GetListCategories(CategoryRepository(NetworkCategoryDataSource(categoryRetrofit,categoryNetworkMapper)))
                    getListCategories().onEach {
                            dataState ->  listOfCategories.value = dataState
                    }.launchIn(viewModelScope)
                }
                is ListProcessStateEvent.GetProcessesByCategoryEvent -> {
                    val getListProcessesByCateg = GetListProcessesByCategory(ProcessRepository(NetworkProcessDataSource(processRetrofit,processNetworkMapper)))
                    getListProcessesByCateg(userId,categoryID = categID,pageIndex=pageIndex,perPage=perPage).onEach {
                            dataState ->  listOfProcesses.value = dataState
                    }.launchIn(viewModelScope)
                }
                is ListProcessStateEvent.None -> println("NOTHING TO DO")
            }
        }
    }

}

sealed class ListProcessStateEvent {
    //definition of events that can be triggered from the associated activity
    object GetProcessesEvent: ListProcessStateEvent()
    object GetProcessesByCategoryEvent: ListProcessStateEvent()
    object GetCategoriesEvent: ListProcessStateEvent()
    object None: ListProcessStateEvent()
}