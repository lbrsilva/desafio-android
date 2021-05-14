package br.com.lbrsilva.nasa.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.helper.identifier.BundleIdentifier
import br.com.lbrsilva.nasa.ui.gallery.viewer.PictureFragment
import br.com.lbrsilva.nasa.ui.gallery.viewer.VideoFragment

class GalleryAdapter(
    fragmentManager: FragmentManager,
    var list: ArrayList<Media>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    fun add(list: List<Media>) {
        this.list.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun getItem(position: Int): Fragment {
        val mediaType = this.list[position].mediaType ?: "image"
        val fragment = if (mediaType == "image") PictureFragment() else VideoFragment()

        fragment.arguments = this.bundle(position)

        return fragment
    }

    private fun bundle(position: Int): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(BundleIdentifier.MEDIA, this.list[position])

        return bundle
    }
}