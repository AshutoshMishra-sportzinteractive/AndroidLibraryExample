package com.sportzinteractive.baseprojectsetup.utils
import android.app.Activity
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.StyleRes
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.viewbinding.ViewBinding
import coil.load
import coil.request.Disposable
import coil.request.ImageRequest
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.tabs.TabLayout
import com.sportzinteractive.baseprojectsetup.R
import com.sportzinteractive.baseprojectsetup.ui.common.Inflate

inline fun <VB : ViewBinding> createBinding(
    parent: ViewGroup,
    inflate: Inflate<VB>,
    attachToRoot: Boolean = false
): VB {
    return inflate.invoke(
        LayoutInflater.from(parent.context),
        parent,
        attachToRoot
    )
}

fun Context.getPixels(@DimenRes id: Int): Int {
    return resources.getDimensionPixelOffset(id)
}

fun ImageView.loadWithShimmer(
    url: String?,
    builder: ImageRequest.Builder.() -> Unit = {
        val shimmer =
            Shimmer.ColorHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.6f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setBaseColor(ContextCompat.getColor(context, R.color.white))
                .setAutoStart(true)
                .build()
        // This is the placeholder for the imageView
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        placeholder(shimmerDrawable)
        crossfade(true)
    },
): Disposable {
    return load(url, builder = builder)
}

fun View.setWidth(widthInPixel: Int) {
    val tempLayoutParams = layoutParams as ViewGroup.LayoutParams
    tempLayoutParams.width = widthInPixel
    layoutParams = tempLayoutParams
}

fun changeSelectedTabAppearance(
    tabLayout: TabLayout,
    tabPosition: Int,
    @StyleRes textAppearanceRes: Int
) {
    try {
        val linearLayout =
            (tabLayout.getChildAt(0) as? ViewGroup)?.getChildAt(tabPosition) as? LinearLayout
        val tabTextView = linearLayout?.getChildAt(1) as? TextView
        tabTextView?.let {
            TextViewCompat.setTextAppearance(it, textAppearanceRes)
        }
    } catch (_: Exception) {
    }
}

private val defaultOrientation: GradientDrawable.Orientation =
    GradientDrawable.Orientation.LEFT_RIGHT

fun linearGradient(
    width: Int, height: Int, colors: IntArray,
    positions: FloatArray?,
    widthRatio: Float? = null,
    orientation: GradientDrawable.Orientation = defaultOrientation
): LinearGradient {

    var ratio = 1f
    if (widthRatio == null && positions != null) {
        ratio = positions.maxBy { it }
    } else if (widthRatio == null && positions == null) {
        ratio = 1f
    } else if (widthRatio != null) {
        ratio = widthRatio
    }

    val bounds = Rect()
    val inset = 0f
    val r = RectF()

    r.set(
        bounds.left + inset, bounds.top + inset,
        (width * ratio) - inset, height * ratio - inset
    )
    val x0: Float
    val x1: Float
    val y0: Float
    val y1: Float


    when (orientation) {
        GradientDrawable.Orientation.TOP_BOTTOM -> {
            x0 = r.left
            y0 = r.top
            x1 = x0
            y1 = r.bottom
        }
        GradientDrawable.Orientation.TR_BL -> {
            x0 = r.right
            y0 = r.top
            x1 = r.left
            y1 = r.bottom
        }
        GradientDrawable.Orientation.RIGHT_LEFT -> {
            x0 = r.right
            y0 = r.top
            x1 = r.left
            y1 = y0
        }
        GradientDrawable.Orientation.BR_TL -> {
            x0 = r.right
            y0 = r.bottom
            x1 = r.left
            y1 = r.top
        }
        GradientDrawable.Orientation.BOTTOM_TOP -> {
            x0 = r.left
            y0 = r.bottom
            x1 = x0
            y1 = r.top
        }
        GradientDrawable.Orientation.BL_TR -> {
            x0 = r.left
            y0 = r.bottom
            x1 = r.right
            y1 = r.top
        }
        GradientDrawable.Orientation.LEFT_RIGHT -> {
            x0 = r.left
            y0 = r.top
            x1 = r.right
            y1 = y0
        }
        else -> {
            x0 = r.left
            y0 = r.top
            x1 = r.right
            y1 = r.bottom
        }
    }

    return LinearGradient(
        x0, y0, x1, y1, colors, positions, Shader.TileMode.CLAMP
    )
}

fun Activity.shareUrl(chooserTitle: String = "Share to", url: String?) {

    if(url.isNullOrBlank())
        return

    ShareCompat.IntentBuilder(this)
        .setType("text/plain")
        .setChooserTitle(chooserTitle)
        .setText(url)
        .startChooser()

}

fun View.visibility(flag: Boolean){
    visibility = if(flag)
        View.VISIBLE
    else
        View.GONE
}

fun View.switchVisibility(){
    visibility = if(visibility == View.VISIBLE) 
        View.GONE
    else
        View.VISIBLE
}

