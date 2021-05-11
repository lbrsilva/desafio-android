package br.com.lbrsilva.nasa.ui.custom

import android.content.Context
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.ortiz.touchview.TouchImageView

class ExtendedViewPager(context: Context): ViewPager(context) {
    override fun canScroll(v: View?, checkV: Boolean, dx: Int, x: Int, y: Int): Boolean {
        return if (v is TouchImageView) {
            v.canScrollHorizontally(-dx)
        } else {
            super.canScroll(v, checkV, dx, x, y)
        }
    }
}