package id.co.dif.base_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.co.dif.base_project.base.BaseResponse
import id.co.dif.base_project.base.BaseResponseList
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.Location
import id.co.dif.base_project.data.NotificationUnreadStatus
import id.co.dif.base_project.utils.isDeviceOnline
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    var periodicOfflineConnectivityIsRunning: Boolean = false
    var responseNotificationUnreadStatus = MutableLiveData<BaseResponse<NotificationUnreadStatus>>()
    var responseSiteLocation = MutableLiveData<BaseResponseList<Location>>()
    var mapLoading = MutableLiveData<Boolean>()

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

    fun getListSite(search: String? = null) {
        viewModelJob = viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            dismissMapLoading()
            viewModelJob?.cancel()
            throwable.printStackTrace()
            handleApiError(throwable)
        }) {
            showMapLoading()
            val response = apiServices.site(
                bearerToken = "Bearer ${session?.token_access}",
                search = search,
            )
            println(response)
            responseSiteLocation.postValue(response)
            dismissMapLoading()
        }
    }

    fun dismissMapLoading() {
        mapLoading.postValue(false)
    }

    fun showMapLoading() {
        mapLoading.postValue(true)
    }


}