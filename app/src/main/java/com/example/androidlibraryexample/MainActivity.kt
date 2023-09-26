package com.example.androidlibraryexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidlibraryexample.data.ConfigManger
import com.example.androidlibraryexample.databinding.LayoutUpdateBinding
import com.sportzinteractive.baseprojectsetup.ui.common.CustomChromeTabIntent
import com.sportzinteractive.baseprojectsetup.ui.common.appupdate.AppUpdateDialog.isUpdatePopupActive
import com.sportzinteractive.baseprojectsetup.ui.common.appupdate.AppUpdateDialog.isVersionGreater
import com.sportzinteractive.baseprojectsetup.ui.common.appupdate.AppUpdateDialog.showUpdatePopup
import com.sportzinteractive.baseprojectsetup.ui.common.appupdate.UpdateDialogPopUpListener
import com.sportzinteractive.baseprojectsetup.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appName: String

    @Inject
    lateinit var chromeTabIntent: CustomChromeTabIntent

    private val loginViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var configManger: ConfigManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (isAvailable){
//            Toast.makeText(this@MainActivity,appName,Toast.LENGTH_LONG).show()
//        }
//        lifecycleScope.launch{
//            val configFetchDone = configManger.fetchConfig()
//            if (configFetchDone) {
//                println("url::->${configManger.getBaseUrl()}")
//            }
//        }
        //CaptchaDialog.show(this,supportFragmentManager)
        //getData()
        //chromeTabIntent.openCustomTab(this, "https://www.wplt20.com")
        loginViewModel.getCountries()
        showPopUp()
    }

    private fun showPopUp() {
        lifecycleScope.launch {
            val inflater = this@MainActivity.layoutInflater
            val dialogBinding = LayoutUpdateBinding.inflate(inflater)
            if (isVersionGreater(1,2)&& isUpdatePopupActive("2")){
                showUpdatePopup(
                    object : UpdateDialogPopUpListener{
                        override fun onDialogDismissed() {
                            showToast("Dismissed..")
                        }
                    },
                    "2",
                    dialogBinding,
                    dialogBinding.btnCancel,
                    dialogBinding.btnSubmit,
                    this@MainActivity,
                    "https://play.google.com/store/search?q=rajasthan+royals&c=apps"
                )
            }
        }
    }


//    override fun getCaptcha(captcha: String,) {
//        showToast(captcha)
//    }
}