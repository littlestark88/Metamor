package id.co.astra.adel.metamor.presentasion.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.databinding.FragmentHomeBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.presentasion.additem.AddItemViewModel
import id.co.astra.adel.metamor.presentasion.order.OrderItemFragment
import id.co.astra.adel.metamor.utils.Constants.ITEM_DATA

@AndroidEntryPoint
class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val addItemViewModel: AddItemViewModel by viewModels()
    private val menuItemAdapter: ListMenuAdapter by lazy {
        ListMenuAdapter(
            requireActivity(),
            mutableListOf(),
            onClickListener = {
                intentOrderItem(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listMenuObserver()

        binding?.edtSearchItem?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addItemViewModel.getDataByName(p0.toString()).observe(viewLifecycleOwner) {
                    menuItemAdapter.setListMenu(it)
                    menuItemAdapter.notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun listMenuObserver() {
        addItemViewModel.getAllItem.observe(viewLifecycleOwner) {
            menuItemAdapter.setListMenu(it)
            menuItemAdapter.notifyDataSetChanged()
            showRecyclerListMenu()
        }

    }

    private fun intentOrderItem(data: AddItem) {
        val orderItemFragment = OrderItemFragment()
        val mFragmentManager = parentFragmentManager
        val bundle = Bundle()
        bundle.putParcelable(ITEM_DATA, data)
        orderItemFragment.arguments = bundle
        orderItemFragment.show(mFragmentManager, OrderItemFragment::class.java.simpleName)
    }

    private fun showRecyclerListMenu() {
        with(binding) {
            this?.rvListMenu?.layoutManager = LinearLayoutManager(context)
            this?.rvListMenu?.setHasFixedSize(true)
            this?.rvListMenu?.adapter = menuItemAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}