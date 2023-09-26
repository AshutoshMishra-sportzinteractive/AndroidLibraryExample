package com.sportzinteractive.baseprojectsetup.ui.common.captcha

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.FragmentManager
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.databinding.CaptchaDialogBinding
import com.sportzinteractive.baseprojectsetup.ui.common.BaseVBDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

interface CaptchaListener{
    fun getCaptcha(captcha:String,typeOfRequest:Int)
}
@AndroidEntryPoint
class CaptchaDialog : BaseVBDialogFragment<CaptchaDialogBinding>(CaptchaDialogBinding::inflate) {

    @Inject
    lateinit var baseInfo: BaseInfo



    companion object {
        private lateinit var listener: CaptchaListener
        private var typeOfRequest: Int = 0
        fun show(captchaListener: CaptchaListener,typeOfRequest:Int, fragmentManager: FragmentManager) {
            this.listener = captchaListener
            this.typeOfRequest = typeOfRequest
            CaptchaDialog()
                .show(
                    fragmentManager,
                    CaptchaDialog::class.simpleName
                )

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.setLayout(
                (Resources.getSystem().displayMetrics.widthPixels * 0.6f).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWebViewClient()
        webViewSettings()
        val data = "<html>\n" +
                "  <head>\n" +
                "    <title>reCAPTCHA demo: Simple page</title>\n" +
                "    <script src=\"https://www.google.com/recaptcha/api.js?render=${baseInfo.getCaptchaSiteKey()}\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "    grecaptcha.ready(function() {\n" +
                "    // do request for recaptcha token\n" +
                "    // response is promise with passed token\n" +
                "        grecaptcha.execute('${baseInfo.getCaptchaSiteKey()}', {action:'validate_captcha'})\n" +
                "                  .then(function(token) {\n" +
                "            // add token value to form\n" +
                "             CaptchaDialog.reCaptchaCallbackInAndroid(token);\n" +
                "           // document.getElementById('g-recaptcha-response').value = token;\n" +
                "        });\n" +
                "    });\n" +
                "</script>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <form action=\"?\" method=\"POST\">\n" +
                "      <input type=\"hidden\" id=\"g-recaptcha-response\" name=\"g-recaptcha-response\">\n" +
                "    </form>\n" +
                "  </body>\n" +
                "</html>"
        binding?.captchaWebView?.loadDataWithBaseURL(baseInfo.getBaseUrl(), data, "text/html", "UTF-8", null)
    }

    private fun setWebViewClient() {
        binding?.captchaWebView?.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding?.progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding?.progress?.visibility = View.GONE
            }
        }
    }

    @JavascriptInterface
    fun reCaptchaCallbackInAndroid(g_response: String) {
        println("captcha::->>$g_response")
        dismiss()
        listener.getCaptcha(g_response, typeOfRequest)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSettings() {
        binding?.let { binding->
            binding.captchaWebView.settings.javaScriptEnabled = true
            binding.captchaWebView.settings.domStorageEnabled = true
            binding.captchaWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
            binding.captchaWebView.isClickable = true
            binding.captchaWebView.addJavascriptInterface(this, "CaptchaDialog");
            binding.captchaWebView.webChromeClient = WebChromeClient()
            binding.captchaWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
            binding.captchaWebView.setBackgroundColor(Color.argb(1, 255, 255, 255));
        }
    }
}