package tn.oussama.mp_android_bonita.presentation.process

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tn.oussama.core.data.DataState
import tn.oussama.core.data.ProcessRepository
import tn.oussama.core.domain.Process
import tn.oussama.core.interactors.GetSingleProcess
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkProcessDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import javax.inject.Inject

@HiltViewModel
class ProcessActivityViewModel @Inject constructor(
    private val processRetrofit: ProcessRetrofit,
    private val processNetworkMapper: ProcessNetworkMapper
): ViewModel(){


    private val process: MutableLiveData<DataState<Process>> = MutableLiveData()
    fun observableProcess(): LiveData<DataState<Process>>  = process


    fun getProcessById(id: Long) {
        viewModelScope.launch {
            val getSingleProcess = GetSingleProcess(ProcessRepository(NetworkProcessDataSource(processRetrofit,processNetworkMapper)))
            val res = getSingleProcess(id)
            process.value = res
        }
    }

}