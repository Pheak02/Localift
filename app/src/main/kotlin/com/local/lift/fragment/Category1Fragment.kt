package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.local.locallift.R
import com.local.locallift.databinding.Category1Binding
import java.util.zip.Inflater

class Category1Fragment : Fragment() {
    private lateinit var allCat:Button
    private lateinit var clothesCat:Button
    private lateinit var bagCat:Button
    private lateinit var accessCat:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view= inflater.inflate(R.layout.category1, container, false)

        allCat = view.findViewById(R.id.allCat)
        clothesCat = view.findViewById(R.id.clothesCat)
        bagCat = view.findViewById(R.id.bagCat)
        accessCat = view.findViewById(R.id.accessCat)

        loadFragment(AllFragment())
        highlightButton(allCat)
        allCat.setOnClickListener({
            loadFragment(AllFragment())
            highlightButton(allCat)
        })
        clothesCat.setOnClickListener({
            loadFragment(ClothesFragment())
            highlightButton(clothesCat)
        })
        bagCat.setOnClickListener({
            loadFragment(BagsFragment())
            highlightButton(bagCat)
        })
        accessCat.setOnClickListener({
            loadFragment(AccessoriesFragment())
            highlightButton(accessCat)
        })
        return view
    }

    private fun loadFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
    private fun highlightButton(selectedButton: Button) {
        // Set all buttons to unselected color
        allCat.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.unselected_button_color))
        clothesCat.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.unselected_button_color))
        bagCat.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.unselected_button_color))
        accessCat.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.unselected_button_color))
        // Set selected button to blue color
        selectedButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.selected_button_color))
    }

}
