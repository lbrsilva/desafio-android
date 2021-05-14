package br.com.lbrsilva.nasa.ui.gallery.viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.databinding.FragmentPictureBinding
import br.com.lbrsilva.nasa.helper.extension.ProgressRequestListener
import br.com.lbrsilva.nasa.helper.identifier.BundleIdentifier
import com.bumptech.glide.Glide

class PictureFragment : Fragment() {
    private lateinit var binding: FragmentPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentPictureBinding.inflate(inflater)

        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.arguments?.let {
            val media = it.getSerializable(BundleIdentifier.MEDIA) as Media

            media.url?.let { url ->
                Glide.with(view.context).load(url)
                    .listener(ProgressRequestListener(this.binding.load)).into(this.binding.image)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        this.binding.image.resetZoomAnimated()
    }
}