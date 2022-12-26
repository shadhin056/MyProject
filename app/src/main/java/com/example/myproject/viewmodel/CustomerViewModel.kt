package com.example.examshadhin.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examshadhin.view.model.api_config.ApiService
import com.example.examshadhin.view.util.Constant
import com.example.myproject.model.CustomerRegResponse
import com.example.myproject.model.RegistrationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CustomerViewModel : ViewModel(){
    private val apiService = ApiService()
    private val disposable = CompositeDisposable()

    var customerRegistrationResponse = MutableLiveData<CustomerRegResponse>()
    var error_response = MutableLiveData<Boolean>();
    var userDeleteResponse = MutableLiveData<Boolean>();

    fun reqForUserRegistration(model: RegistrationModel) {

        disposable.add(apiService.reqForRegistration(model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CustomerRegResponse>() {
                override fun onSuccess(model: CustomerRegResponse) {
                    model?.let {
                        customerRegistrationResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    if(Constant.logCheck){
                        Log.e("error_reqForUserReg", e.message.toString())
                    }
                    e.printStackTrace()
                    error_response.value = true
                }
            })
        )
    }
}