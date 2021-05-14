package br.com.lbrsilva.nasa.ui.carousel.viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.databinding.FragmentVideoBinding
import br.com.lbrsilva.nasa.helper.identifier.BundleIdentifier
import br.com.lbrsilva.nasa.helper.transformer.YoutubeTransformer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private var youTubePlayer: YouTubePlayer? = null
    private var triedPlay = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentVideoBinding.inflate(inflater)

        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.arguments?.let {
            val media = it.getSerializable(BundleIdentifier.MEDIA) as Media

            YoutubeTransformer.extractId(media.url)?.let { id ->
                this.setupVideo(id)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        this.youTubePlayer?.pause()
    }

    override fun onResume() {
        super.onResume()

        this.youTubePlayer?.play()
        this.triedPlay = true
    }

    private fun setupVideo(videoId: String) {
        this.binding.video.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)

                this@VideoFragment.youTubePlayer = youTubePlayer

                youTubePlayer.cueVideo(videoId, 0.0F)

                if (this@VideoFragment.triedPlay) {
                    youTubePlayer.play()
                }
            }
        })
    }
}