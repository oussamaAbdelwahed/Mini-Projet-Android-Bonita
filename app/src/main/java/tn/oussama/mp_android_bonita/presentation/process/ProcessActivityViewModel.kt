package tn.oussama.mp_android_bonita.presentation.process

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tn.oussama.core.data.DataState
import tn.oussama.core.data.ProcessRepository
import tn.oussama.core.domain.Process
import tn.oussama.core.domain.ProcessContract
import tn.oussama.core.domain.ProcessInstanceSubmitResponse
import tn.oussama.core.interactors.GetSingleProcess
import tn.oussama.core.interactors.PostProcessInstance
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkProcessDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import javax.inject.Inject
@HiltViewModel
class ProcessActivityViewModel @Inject constructor(
    private val processRetrofit: ProcessRetrofit,
    private val processNetworkMapper: ProcessNetworkMapper
): ViewModel(){

    private val processContract: MutableLiveData<DataState<ProcessContract>> = MutableLiveData()
    private val submitProcessInstanceResponse: MutableLiveData<DataState<ProcessInstanceSubmitResponse>> = MutableLiveData()

    val inputNames = mutableListOf<String>()
    val requiredFields = mutableListOf<String>()
    var processName=""

    fun observableProcess(): LiveData<DataState<ProcessContract>>  = processContract
    fun observableProcessInstanceSubmitResponse(): LiveData<DataState<ProcessInstanceSubmitResponse>>  = submitProcessInstanceResponse


    fun getProcessById(id: Long) {
        viewModelScope.launch {
            val getSingleProcess = GetSingleProcess(ProcessRepository(NetworkProcessDataSource(processRetrofit,processNetworkMapper)))
            val res = getSingleProcess(id)
            delay(1000)
            processContract.value = res
        }
    }

    fun postProcessInstance(processID:Long,body: HashMap<String,HashMap<String,Any>>) {
        viewModelScope.launch {
            val postProcessInstance = PostProcessInstance(ProcessRepository(NetworkProcessDataSource(processRetrofit,processNetworkMapper)))
            val res    = postProcessInstance(processID,body)
            submitProcessInstanceResponse.value = res
        }
    }
}