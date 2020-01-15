package co.julian.eltiempo.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.julian.eltiempo.view.fragments.AllImagesFragment
import co.julian.eltiempo.view.fragments.FavoriteImagesFragment


const val ALL_PAGE_INDEX = 0
const val FAVORITES_PAGE_INDEX = 1

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ALL_PAGE_INDEX to { AllImagesFragment() },
        FAVORITES_PAGE_INDEX to { FavoriteImagesFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}
