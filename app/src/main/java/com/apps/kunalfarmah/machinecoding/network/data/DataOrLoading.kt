package com.apps.kunalfarmah.machinecoding.network.data

data class DataOrLoading<T,Boolean>(
    val data: T?,
    val loading: Boolean
)