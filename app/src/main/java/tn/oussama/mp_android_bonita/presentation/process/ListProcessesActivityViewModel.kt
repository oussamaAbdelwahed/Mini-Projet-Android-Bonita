package tn.oussama.mp_android_bonita.presentation.process

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tn.oussama.core.data.DataState
import tn.oussama.core.data.ProcessRepository
import tn.oussama.core.domain.Process
import tn.oussama.core.interactors.GetListProcesses
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkProcessDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit

import javax.inject.Inject

@HiltViewModel
class ListProcessesActivityViewModel @Inject constructor(
                                                          private val processRetrofit: ProcessRetrofit ,
                                                          private val processNetworkMapper: ProcessNetworkMapper
                                                        ): ViewModel()
{
    private val listOfProcesses: MutableLiveData<DataState<List<Process>>> = MutableLiveData()
    fun getListOfProcesses(): LiveData<DataState<List<Process>>> = listOfProcesses

    fun setListProcessStateEvent(e:ListProcessStateEvent,userId:Long,pageIndex:Int=0,perPage:Int=20) {
        viewModelScope.launch {
            when(e) {
                is ListProcessStateEvent.GetProcessesEvent -> {
                    val getListProcess = GetListProcesses(ProcessRepository(NetworkProcessDataSource(processRetrofit,processNetworkMapper)))
                    getListProcess(userId,pageIndex=pageIndex,perPage=perPage).onEach {
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
    object None: ListProcessStateEvent()
}