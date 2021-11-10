package work.monkey.kotlintemplate.common;

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

object DataBindingAdapters {

    @BindingAdapter("android:layout_width")
    @JvmStatic
    fun setViewWidthWithDimensionType(view: View, dimensionType: DimensionType?) {
        dimensionType?.also {
            val width = view.context.px(dimensionType.dimen)
            val height = view.layoutParams.height
            if (view.layoutParams is ConstraintLayout.LayoutParams) {
                view.layoutParams = ConstraintLayout.LayoutParams(width, height)
            }
        }
    }

    @BindingAdapter("android:layout_height")
    @JvmStatic
    fun setViewHeightWithDimensionType(view: View, dimensionType: DimensionType?) {
        dimensionType?.also {
            val width = view.layoutParams.width
            val height = view.context.px(dimensionType.dimen)
            if (view.layoutParams is ConstraintLayout.LayoutParams) {
                view.layoutParams = ConstraintLayout.LayoutParams(width, height)
            }
        }
    }

    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageUri(view: ImageView, imageUri: Uri?) {
        view.setImageURI(imageUri)
    }

    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }

    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

    @BindingAdapter("srcCompat")
    @JvmStatic
    fun bindImageDrawableSrcCompat(view: ImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }
}