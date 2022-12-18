package id.co.astra.adel.metamor.presentasion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.FragmentHomeBinding
import id.co.astra.adel.metamor.domain.customer.model.Customer
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.presentasion.customer.CustomerViewModel
import id.co.astra.adel.metamor.presentasion.order.OrderListAdapter
import id.co.astra.adel.metamor.presentasion.order.OrderListFragment
import id.co.astra.adel.metamor.presentasion.order.OrderViewModel
import id.co.astra.adel.metamor.utils.Constants.ID_CUSTOMER
import id.co.astra.adel.metamor.utils.showSimpleListDialog
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val customerViewModel: CustomerViewModel by viewModels()
    private val orderViewModel: OrderViewModel by viewModels()
    private var dataCustomerIdSelected: Int = 0
    private var dataCustomer = mutableListOf<Customer>()
    private var itemDataCustomer = mutableListOf<String>()
    private lateinit var orderListAdapter: OrderListAdapter
    private var data = mutableListOf<Order>()

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
        initCustomerObserver()
        initOrderObserver()
        showRecyclerList()
        binding?.btnSaveOrder?.setOnClickListener {
            intentToOrder()
        }
        binding?.edtName?.setOnClickListener {
            showSimpleDialogName()
        }
    }

    private fun intentToOrder() {
        val orderItemFragment = OrderListFragment()
        val mFragmentManager = parentFragmentManager
        val bundle = Bundle()
        bundle.putInt(ID_CUSTOMER, dataCustomerIdSelected)
        orderItemFragment.arguments = bundle
        orderItemFragment.show(mFragmentManager, OrderListFragment::class.java.simpleName)
    }

    private fun showSimpleDialogName() {
        showSimpleListDialog(
            requireActivity(),
            title = getString(R.string.select_name),
            items = itemDataCustomer,
            onItemClicked = {
                binding?.edtName?.setText(dataCustomer[it].nameCustomer)
                dataCustomerIdSelected = dataCustomer[it].idCustomer
            }
        )
    }

    private fun initOrderObserver() {
        orderViewModel.getOrderByCustomerId(dataCustomerIdSelected).observe(viewLifecycleOwner) { order ->
            data.clear()
            data.addAll(order)
            orderListAdapter.setListOrder(order)
            orderListAdapter.notifyDataSetChanged()
        }
    }

    private fun initCustomerObserver() {
        customerViewModel.getAllCustomer.observe(requireActivity()) { customer ->
            dataCustomer.clear()
            dataCustomer.addAll(customer)
            itemDataCustomer.addAll(dataCustomer.map { it.nameCustomer })
        }
    }

    private fun showRecyclerList() {
        orderListAdapter = OrderListAdapter(data) { itemEntity ->
            val dialog = MaterialDialog(requireActivity())
                .cancelable(false)
                .cancelOnTouchOutside(false)
                .title(text = getString(R.string.label_notif))
                .message(text = getString(R.string.label_confirm_delete_item))
                .positiveButton(text = getString(R.string.label_ok)) {
                    lifecycleScope.launch {
                        orderViewModel.deleteCustomer(itemEntity.idItem)
                    }
                }
                .negativeButton(text = getString(R.string.label_no)) {
                    it.dismiss()
                }
            dialog.show()
        }
        with(binding) {
            this?.rvOrder?.layoutManager = LinearLayoutManager(requireActivity())
            this?.rvOrder?.setHasFixedSize(true)
            this?.rvOrder?.adapter = orderListAdapter
//            orderListAdapter.callbackInterface = object : CallbackInterface {
//                override fun passResultCallback(totalValue: String, totalQuantity: Int) {
//                    this.tvSubTotalValue?.text = convertCurrency(totalValue.toDouble())
//                    dataPPN = totalValue.toDouble() / 100 * 11
//                    tvPPNValue.text = convertCurrency(dataPPN)
//                    val total = dataPPN + totalValue.toDouble()
//                    dataQuantity = totalQuantity
//                    dataTotal = total
//                    tvTotalValue.text = convertCurrency(total)
//                    btnCharge.text = "Checkout ($totalQuantity)"
//                }
//            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}