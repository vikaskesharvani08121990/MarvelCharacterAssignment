package com.example.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.common.R
import com.example.common.utils.CONNECT_EXCEPTION_CODE
import com.example.common.utils.SOCKET_TIME_OUT_EXCEPTION_CODE
import com.example.common.utils.UNKNOWN_HOST_EXCEPTION_CODE
import com.example.common.utils.UNKNOWN_NETWORK_EXCEPTION_CODE

abstract class BaseFragment : Fragment() {

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            this.baseActivity = activity
        }

    }

    fun showMessage(message: String, useToast: Boolean) {
        baseActivity.showMessage(message, useToast)
    }

    fun getErrorMessage(errorCode: Int): String {
        return when (errorCode) {
            SOCKET_TIME_OUT_EXCEPTION_CODE -> getString(R.string.socket_time_out_exception)
            UNKNOWN_NETWORK_EXCEPTION_CODE -> getString(R.string.unknown_network_exception)
            CONNECT_EXCEPTION_CODE -> getString(R.string.connect_exception)
            UNKNOWN_HOST_EXCEPTION_CODE -> getString(R.string.unknown_host_exception)
            else -> getString(R.string.connect_exception)
        }
    }

    abstract fun showLoading()

    abstract fun hideLoading()

}