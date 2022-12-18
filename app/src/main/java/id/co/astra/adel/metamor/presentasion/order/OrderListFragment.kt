package id.co.astra.adel.metamor.presentasion.order

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.base.Resource
import id.co.astra.adel.metamor.databinding.FragmentOrderListBinding
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.presentasion.additem.AddItemViewModel
import id.co.astra.adel.metamor.presentasion.home.ListMenuAdapter
import id.co.astra.adel.metamor.utils.Constants.ID_CUSTOMER
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderListFragment : DialogFragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding
    private val addItemViewModel: AddItemViewModel by viewModels()
    private val orderViewModel: OrderViewModel by viewModels()
    private var customerId: Int? = 0
    private val menuItemAdapter: ListMenuAdapter by lazy {
        ListMenuAdapter(
            requireActivity(),
            mutableListOf(),
            onClickListener = {
                lifecycleScope.launch {
                    if(customerId != 0) {
                        orderViewModel.insertOrder(
                            Order(
                                0,
                                it.nameItem,
                                it.priceItem,
                                1,
                                0,
                                customerId ?: 0
                            )
                        )
                    } else {
                        Toast.makeText(requireActivity(), "Mohon untuk memilih customer terlebih dahulu", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customerId = arguments?.getInt(ID_CUSTOMER)
        listMenuObserver()
        binding?.edtSearchItem?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                addItemViewModel.getDataByName(p0.toString()).observe(viewLifecycleOwner) {
//                    menuItemAdapter.setListMenu(it)
//                    menuItemAdapter.notifyDataSetChanged()
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun listMenuObserver() {
        addItemViewModel.getAllItem()
        addItemViewModel.getAllItem.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showToast("Loading")
                is Resource.Empty -> showToast("Empty")
                is Resource.Success -> {
                    it.data?.let { it1 -> menuItemAdapter.setListMenu(it1) }
                    menuItemAdapter.notifyDataSetChanged()
                    showRecyclerListMenu()
                }
                is Resource.Error -> {
                    showToast(it.message.toString())
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(),message, Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerListMenu() {
        with(binding) {
            this?.rvListMenu?.layoutManager = LinearLayoutManager(context)
            this?.rvListMenu?.setHasFixedSize(true)
            this?.rvListMenu?.adapter = menuItemAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
    }
}