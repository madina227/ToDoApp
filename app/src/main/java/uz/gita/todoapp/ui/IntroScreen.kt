package uz.gita.todoapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import uz.gita.todoapp.R
import uz.gita.todoapp.adapter.IntroAdapter
import uz.gita.todoapp.model.IntroData
import uz.gita.todoapp.viewModel.IntroViewModel

class IntroScreen : Fragment(R.layout.screen_intro) {
    private val viewModel: IntroViewModel by viewModels()

    private val adapter: IntroAdapter by lazy { IntroAdapter() }

    private val navController: NavController by lazy { findNavController() }

    private val dataList = ArrayList<IntroData>()

    private lateinit var pager: ViewPager2
    private lateinit var btnNext: Button
    private lateinit var dots: DotsIndicator

//    private var _binding: ScreenIntroBinding? = null
//    private val binding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNextLiveData.observe(this) {
            navController.navigate(R.id.action_introScreen_to_mainScreen)
        }

    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = ScreenIntroBinding.inflate(inflater)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList.addAll(
            arrayListOf(
                IntroData("Make a clear your to-do list", R.drawable.clock),
                IntroData("Make a clear your to-do list", R.drawable.clock),
                IntroData("Make a clear your to-do list", R.drawable.clock),
            )
        )
        pager = view.findViewById(R.id.pager)
        btnNext = view.findViewById(R.id.btn_next)
        dots = view.findViewById(R.id.dots_indicator)

        pager.adapter = adapter.apply {
            submitList(dataList)
        }

        dots.attachTo(pager)

        btnNext.setOnClickListener {
            viewModel.openNextScreen(pager.currentItem)
        }

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (pager.currentItem) {
                    0 -> btnNext.text = "next"
                    1 -> btnNext.text = "next"
                    2 -> btnNext.text = "enter"
                }
            }
        })


//
//        binding.pager.adapter = adapter.apply {
//            submitList(dataList)
//        }
//
//        binding.dotsIndicator.attachTo(binding.pager)
//
//        binding.btnNext.setOnClickListener {
//            viewModel.openNextScreen(binding.pager.currentItem)
//        }

//        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//                when (binding.pager.currentItem) {
//                    0 -> binding.btnNext.text = "next"
//                    1 -> binding.btnNext.text = "next"
//                    2 -> binding.btnNext.text = "enter"
//                }
//            }
//        })
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
}