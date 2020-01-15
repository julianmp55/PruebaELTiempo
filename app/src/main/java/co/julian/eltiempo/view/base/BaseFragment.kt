package co.julian.eltiempo.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.julian.eltiempo.utils.InjectorUtils
import co.julian.eltiempo.viewmodel.NasaViewMoldel

abstract class BaseFragment<U:ViewDataBinding> : Fragment(){


    val nasaViewMoldel : NasaViewMoldel by viewModels {
        InjectorUtils.provideNasaViewModelFactory(requireContext())
    }



    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    abstract fun initView()

    protected lateinit var dataBinding: U

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        initView()

        return dataBinding.root
    }

}
