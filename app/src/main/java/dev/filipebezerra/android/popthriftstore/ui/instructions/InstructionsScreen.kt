package dev.filipebezerra.android.popthriftstore.ui.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dev.filipebezerra.android.popthriftstore.databinding.InstructionsFragmentBinding
import dev.filipebezerra.android.popthriftstore.ui.util.DepthPageTransformer
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver

class InstructionsScreen : Fragment() {

    private val instructionsViewModel: InstructionsViewModel by viewModels()

    private lateinit var instructionFragmentBinding: InstructionsFragmentBinding

    private lateinit var instructionItemAdapter: InstructionItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = InstructionsFragmentBinding.inflate(inflater)
        .apply {
            instructionFragmentBinding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = instructionsViewModel
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        observeUi()
    }

    private fun setupViewPager() {
        with(instructionFragmentBinding.viewPager) {
            initViewPagerAdapter()
            initViewPagerPageChangeCallback()
            initViewPagerAnimation()
        }
    }

    private fun ViewPager2.initViewPagerAdapter() {
        instructionItemAdapter = InstructionItemAdapter().apply {
            adapter = this
        }
    }

    private fun ViewPager2.initViewPagerPageChangeCallback() {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) =
                instructionsViewModel.changeCurrentInstructionPosition(position)
        })
    }

    private fun ViewPager2.initViewPagerAnimation() {
        setPageTransformer(DepthPageTransformer())
    }

    private fun observeUi() {
        instructionsViewModel.instructionItems.observe(viewLifecycleOwner) {
            instructionFragmentBinding.viewPager.offscreenPageLimit = it.size
            instructionItemAdapter.submitList(it)
        }
        instructionsViewModel.currentInstructionPosition.observe(viewLifecycleOwner) { currentPosition ->
            instructionFragmentBinding.viewPager.setCurrentItem(currentPosition, true)
        }
        instructionsViewModel.navigateToHome.observe(viewLifecycleOwner, EventObserver {

        })
    }
}