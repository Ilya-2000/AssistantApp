package com.impact.assistantapp.ui.finance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.finance.Wallet

class FinanceViewModel : ViewModel() {

    private val _walletList = MutableLiveData<Wallet>()
    val walletList: LiveData<Wallet> = _walletList
}