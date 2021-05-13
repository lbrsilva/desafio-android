package br.com.lbrsilva.nasa.helper.extension

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ProgressRequestListener(val view: View) : RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
    ): Boolean {
        this.view.visibility = View.GONE

        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        this.view.visibility = View.GONE

        return false
    }
}