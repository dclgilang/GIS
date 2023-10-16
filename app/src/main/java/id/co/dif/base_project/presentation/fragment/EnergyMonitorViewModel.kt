package id.co.dif.base_project.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.co.dif.base_project.base.BaseResponse
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.NotificationUnreadStatus
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class EnergyMonitorViewModel: BaseViewModel() {

    var responseNotificationUnreadStatus = MutableLiveData<BaseResponse<NotificationUnreadStatus>>()

    fun getNotificationUnreadStatus() {
        viewModelJob?.cancel()
        viewModelJob = viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            viewModelJob?.cancel()
        }) {
            val response = apiServices.getNotificationUnreadStatus(
                bearerToken = "Bearer ${session?.token_access}",
            )
            responseNotificationUnreadStatus.postValue(response)
        }
    }


}
