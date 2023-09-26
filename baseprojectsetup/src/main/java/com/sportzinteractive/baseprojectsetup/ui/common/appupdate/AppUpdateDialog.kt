package com.sportzinteractive.baseprojectsetup.ui.common.appupdate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding

interface UpdateDialogPopUpListener {
    fun onDialogDismissed()
}

object AppUpdateDialog{

    fun isVersionGreater(currentBuildConfigVersion:Int,liveVersionFromConfig:Int): Boolean {
        return currentBuildConfigVersion < liveVersionFromConfig
    }

    fun isUpdatePopupActive(updateType:String): Boolean {
        return updateType == "1" || updateType == "2"
    }

    fun showUpdatePopup(
        dismissListener: UpdateDialogPopUpListener,
        updateType: String,
        view: ViewBinding,
        btnCancel: View,
        btnSubmit: View,
        activity: Activity,
        playStoreUrl: String
    ) {

        val showDismissButton = updateType == "1"

        buildPopUp(
            showNegativeButton = showDismissButton,
            dismissListener = dismissListener,
            view = view,
            btnCancel = btnCancel,
            btnSubmit = btnSubmit,
            activity = activity,
            playStoreUrl = playStoreUrl
        )

    }
    private fun buildPopUp(
        showNegativeButton: Boolean = false,
        dismissListener: UpdateDialogPopUpListener? = null,
        view: ViewBinding,
        btnCancel: View,
        btnSubmit: View,
        activity: Activity,
        playStoreUrl: String
    ) {

        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setCancelable(false)
        view.apply {
            dialogBuilder.setView(view.root)
            val alertDialog = dialogBuilder.create()


            btnCancel.isVisible = showNegativeButton

            btnCancel.setOnClickListener {
                alertDialog.dismiss()
                dismissListener?.onDialogDismissed()
            }
            btnSubmit.setOnClickListener {
                alertDialog.dismiss()
                takeToPlayStore(playStoreUrl,activity)
            }
            if (!activity.isFinishing) {
                alertDialog.show()
            }
        }
    }

    private fun takeToPlayStore(url:String,activity: Activity) {
        try {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            activity.finish()
        } catch (ex: Exception) {
            activity.finish()
        }
    }
}