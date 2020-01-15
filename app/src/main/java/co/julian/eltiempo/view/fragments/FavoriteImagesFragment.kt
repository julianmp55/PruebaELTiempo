package co.julian.eltiempo.view.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import co.julian.eltiempo.R
import co.julian.eltiempo.adapters.ALL_PAGE_INDEX
import co.julian.eltiempo.adapters.FAVORITES_PAGE_INDEX
import co.julian.eltiempo.adapters.ImageAdapter
import co.julian.eltiempo.adapters.PagerAdapter
import co.julian.eltiempo.data.entity.NasaEntity
import co.julian.eltiempo.databinding.FragmentFavoriteImagesBinding
import co.julian.eltiempo.databinding.FragmentMainBinding
import co.julian.eltiempo.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteImagesFragment : BaseFragment<FragmentFavoriteImagesBinding>(),ImageAdapter.Listener{

    val adapter: ImageAdapter by lazy {
        ImageAdapter(this)
    }


    override fun getLayoutRes(): Int = R.layout.fragment_favorite_images

    override fun initView() {
        dataBinding.favoriteList.adapter = adapter
        nasaViewMoldel.getFavoriteNasaDataLocal().observe(viewLifecycleOwner){
            dataBinding.hasImages = !it.isEmpty()
            dataBinding.executePendingBindings()
            adapter.submitList(it)
        }
    }

    override fun onAddFavorite(
        image: NasaEntity,
        adapterPosition: Int
    ) {
        image.is_favorite = true
        nasaViewMoldel.updateImage(image)
        adapter.notifyItemChanged(adapterPosition)
    }

    override fun onDelete(image: NasaEntity) {
        nasaViewMoldel.deleteImage(image)
    }
}