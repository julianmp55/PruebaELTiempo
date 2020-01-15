package co.julian.eltiempo.view.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import co.julian.eltiempo.R
import co.julian.eltiempo.adapters.ALL_PAGE_INDEX
import co.julian.eltiempo.adapters.FAVORITES_PAGE_INDEX
import co.julian.eltiempo.adapters.PagerAdapter
import co.julian.eltiempo.databinding.FragmentDetailBinding
import co.julian.eltiempo.databinding.FragmentMainBinding
import co.julian.eltiempo.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : BaseFragment<FragmentDetailBinding>(){



    private val args: DetailFragmentArgs by navArgs()

    override fun getLayoutRes(): Int = R.layout.fragment_detail

    override fun initView() {
        nasaViewMoldel.getNasaImage(args.imageId).observe(viewLifecycleOwner){
            dataBinding.apply {
                image = it
                executePendingBindings()
            }
        }

        dataBinding.apply {
            materialCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                image?.let {
                    it.is_favorite = isChecked
                    nasaViewMoldel.updateImage(it)
                }
            }
        }

    }
}