package com.example.cityweather.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.example.cityweather.base.BaseViewModel;
import com.example.cityweather.model.AddressModel;

import java.util.ArrayList;

public class AddressViewModel extends BaseViewModel {
    private MutableLiveData<AddressModel> addressData;

    public AddressViewModel() {
        addressData = new MutableLiveData<>();
    }

    public void setAddressData(AddressModel addressModel) {
        this.addressData.postValue(addressModel);
    }

    public MutableLiveData<AddressModel> getAddressData() {
        return addressData;
    }
}
