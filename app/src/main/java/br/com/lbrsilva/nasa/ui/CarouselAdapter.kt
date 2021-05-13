package br.com.lbrsilva.nasa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.lbrsilva.nasa.data.model.Picture
import br.com.lbrsilva.nasa.helper.BundleIdentifier

class CarouselAdapter(
    fragmentManager: FragmentManager,
    private var list: ArrayList<Picture>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    fun add(list: List<Picture>) {
        this.list.addAll(list)
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun getItem(position: Int): Fragment {
        val fragment = PictureFragment()

        fragment.arguments = this.bundle(position)

        return fragment
    }

    private fun bundle(position: Int): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(BundleIdentifier.PICTURE, this.list[position])

        return bundle
    }
}