package br.com.lbrsilva.nasa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lbrsilva.nasa.data.model.Picture
import br.com.lbrsilva.nasa.databinding.FragmentPictureBinding
import br.com.lbrsilva.nasa.helper.BundleIdentifier
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
            val picture = it.getSerializable(BundleIdentifier.PICTURE) as Picture

            picture.hdurl?.let { url ->
                Glide.with(view.context).load(url).into(this.binding.image)
            }
        }
    }
}