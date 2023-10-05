package id.co.dif.base_project.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import id.co.dif.base_project.base.BaseResponse
import id.co.dif.base_project.base.BaseResponseList
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.BasicInfo
import id.co.dif.base_project.data.DirectionsResponse
import id.co.dif.base_project.data.Location
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class MapsTicketViewModel : BaseViewModel() {
    var responseNearestTechnician = MutableLiveData<BaseResponseList<Location>>()
    var responseDetailedProfile = MutableLiveData<BaseResponse<BasicInfo>>()

    fun getNearestTechnician(idSite: Int? = 0) {
        viewModelJob?.cancel()
        viewModelJob = viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            viewModelJob?.cancel()
        }) {
            val response = apiServices.getNearestTechnician(
                "Bearer ${session?.token_access}",
                idSite = idSite
            )
            responseNearestTechnician.postValue(response)
        }
    }

    fun getDetailProfile(id: Int?) {
        viewModelJob?.cancel()
        viewModelJob = viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            handleApiError(e)
            e.printStackTrace()
            viewModelJob?.cancel()
        }) {
            val response = apiServices.getDetailProfile(
                "Bearer ${session?.token_access}",
                id = id
            )
            responseDetailedProfile.postValue(response)
        }
    }

    var responseDirection = MutableLiveData<DirectionsResponse>()

    fun getDirection(site: LatLng, technician: LatLng) {

        viewModelJob = viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            e.printStackTrace()
            Toast.makeText(context, "Cannot create direction. There is something wrong!", Toast.LENGTH_SHORT).show()
            viewModelJob?.cancel()
            dissmissLoading()
        }) {
            showLoading()
            val response = apiServices.getDirection(
                mutableMapOf(
                    "origin" to "${technician.latitude}, ${technician.longitude}",
                    "destination" to "${site.latitude}, ${site.longitude}"
                ),
                bearerToken = "Bearer ${session?.token_access}"
            )
            responseDirection.postValue(response)
            dissmissLoading()
        }
    }

}