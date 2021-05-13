package br.com.lbrsilva.nasa.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.ortiz.touchview.TouchImageView

class ExtendedViewPager : ViewPager {
    private var loadMore: (() -> (Unit))? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun canScroll(v: View?, checkV: Boolean, dx: Int, x: Int, y: Int): Boolean {
        return if (v is TouchImageView) {
            v.canScrollHorizontally(-dx)
        } else {
            super.canScroll(v, checkV, dx, x, y)
        }
    }

    override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int) {
        super.onPageScrolled(position, offset, offsetPixels)

        val count = (this.adapter?.count ?: 0) - 1

        if (count > 0) {
            val loadMorePosition = count - 3

            if (position == loadMorePosition) {
                this.loadMore?.let {
                    it()
                }
            }
        }
    }

    fun addLoadMore(loadMore: () -> (Unit)) {
        this.loadMore = loadMore
    }

    fun removeLoadMore() {
        this.loadMore = null
    }
}