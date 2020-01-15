package co.julian.eltiempo.view.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import co.julian.eltiempo.R
import co.julian.eltiempo.adapters.ALL_PAGE_INDEX
import co.julian.eltiempo.adapters.FAVORITES_PAGE_INDEX
import co.julian.eltiempo.adapters.PagerAdapter
import co.julian.eltiempo.databinding.FragmentMainBinding
import co.julian.eltiempo.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment<FragmentMainBinding>(){


    override fun getLayoutRes(): Int = R.layout.fragment_main

    override fun initView() {







        val tabLayout = dataBinding.tabs




        val viewPager = dataBinding.viewPager

        viewPager.isUserInputEnabled = false

        viewPager.adapter = PagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(dataBinding.toolbar)


    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ALL_PAGE_INDEX -> getString(R.string.all_images_title)
            FAVORITES_PAGE_INDEX -> getString(R.string.favorite_images_title)
            else -> null
        }
    }
}