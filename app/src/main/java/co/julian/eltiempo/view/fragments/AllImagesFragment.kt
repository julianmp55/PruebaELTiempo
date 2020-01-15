package co.julian.eltiempo.view.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.observe
import co.julian.eltiempo.R
import co.julian.eltiempo.adapters.ImageAdapter
import co.julian.eltiempo.data.entity.NasaEntity
import co.julian.eltiempo.databinding.FragmentAllImagesBinding
import co.julian.eltiempo.view.base.BaseFragment


class AllImagesFragment : BaseFragment<FragmentAllImagesBinding>(),ImageAdapter.Listener{

    val adapter: ImageAdapter by lazy {
        ImageAdapter(this)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_all_images

    override fun initView() {
        dataBinding.nasaList.adapter = adapter
        nasaViewMoldel.getNasaDataLocal().observe(viewLifecycleOwner){
            dataBinding.hasImages = !it.isEmpty()
            dataBinding.executePendingBindings()
            if (it.isEmpty())
                nasaViewMoldel.getNasaData()
            adapter.submitList(it)
        }
        setHasOptionsMenu(true)
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                nasaViewMoldel.getNasaData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
