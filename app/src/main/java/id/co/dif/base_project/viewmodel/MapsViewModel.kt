package id.co.dif.base_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.co.dif.base_project.base.BaseResponse
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.MarkerTripleE
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/***
 * Created by kikiprayudi
 * on Monday, 27/02/23 04:00
 *
 */

class MapsViewModel : BaseViewModel() {

    var responseListMarker = MutableLiveData<BaseResponse<MarkerTripleE>>()

    fun getListLocation() {
        viewModelJob?.cancel()
        viewModelJob = viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            viewModelJob?.cancel()
        }) {
            delay(5000)
            val response = apiServices.getListLocation()
//            responseListMarker.postValue(response)
        }
    }

}