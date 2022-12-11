package id.co.astra.adel.metamor.presentasion.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ActivityOrderListBinding
import id.co.astra.adel.metamor.domain.customer.model.Customer
import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import id.co.astra.adel.metamor.presentasion.customer.CustomerViewModel
import id.co.astra.adel.metamor.presentasion.payment.PaymentActivity
import id.co.astra.adel.metamor.presentasion.saveorder.SaveOrderViewModel
import id.co.astra.adel.metamor.utils.CallbackInterface
import id.co.astra.adel.metamor.utils.Constants.ID_CUSTOMER
import id.co.astra.adel.metamor.utils.Constants.PPN
import id.co.astra.adel.metamor.utils.Constants.QUANTITY
import id.co.astra.adel.metamor.utils.Constants.TOTAL
import id.co.astra.adel.metamor.utils.DataMapper
import id.co.astra.adel.metamor.utils.convertCurrency
import id.co.astra.adel.metamor.utils.showSimpleListDialog
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding
    private val orderViewModel: OrderViewModel by viewModels()
    private val customerViewModel: CustomerViewModel by viewModels()
    private val saveOrderViewModel: SaveOrderViewModel by viewModels()
    private val customerOrderViewModel: CustomerOrderViewModel by viewModels()
    private lateinit var orderListAdapter: OrderListAdapter
    private var data = mutableListOf<Order>()
    private var dataOrder = mutableListOf<Order>()
    private var dataCustomer = mutableListOf<Customer>()
    private var dataCustomerSelected : Customer? = null
    private var dataCustomerIdSelected: Int = 0
    private var idOrder = mutableListOf<Int>()
    private var itemDataCustomer = mutableListOf<String>()
    private var dataTotal: Double = 0.0
    private var dataQuantity: Int = 0
    private var dataPPN: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idCustomer = intent.getIntExtra(ID_CUSTOMER, 0)
        if(idCustomer == 0) {
            initOrderObserver()
            initCustomerObserver()
        } else {
            initGetSaveOrderObserver(idCustomer)
        }
        showRecyclerList()
        with(binding) {
            btnCharge.setOnClickListener {
//            if(dataQuantity == 0 || selectedDataCustomer.nameCustomer.isEmpty()) {
//                Toast.makeText(this, "Silahkan pilih pesanan anda terlebih dahulu", Toast.LENGTH_SHORT).show()
//            } else {
                val intent = Intent(this@OrderListActivity, PaymentActivity::class.java)
//                intent.putExtra(DATA_CUSTOMER, selectedDataCustomer)
//                intent.putExtra(DATA_ORDER, getDataOrder)
                intent.putExtra(TOTAL, dataTotal)
                intent.putExtra(QUANTITY, dataQuantity)
                intent.putExtra(PPN, dataPPN)
                startActivity(intent)
            }
//        }
            edtName.setOnClickListener {
                showSimpleDialogName()
            }
            btnSaveOrder.setOnClickListener {
                saveOrder()
            }
        }


    }

    private fun saveOrder() {
        var customerOrder : List<CustomerOrder>? = null
        val addCustomerOrder : ArrayList<CustomerOrder>? = null
        for(i in dataOrder.indices) {
            customerOrder = listOf(
                CustomerOrder(
                    customerId = dataCustomerIdSelected,
                    orderId = dataOrder[i].idItem
                )
            )
            addCustomerOrder?.addAll(customerOrder)
        }
        lifecycleScope.launch {
            customerOrder?.let { customerOrderViewModel.insertCustomerOrder(addCustomerOrder ?: emptyList()) }
        }
    }

    private fun initGetSaveOrderObserver(idCustomer: Int) {
        saveOrderViewModel.getSaveOrderById(idCustomer).observe(this) {

        }
    }
    private fun initOrderObserver() {
        orderViewModel.getAllOrder.observe(this) { order ->
            dataOrder.clear()
            dataOrder.addAll(order)
            orderListAdapter.setListOrder(order)
            orderListAdapter.notifyDataSetChanged()
        }
    }


    private fun initCustomerObserver() {
        customerViewModel.getAllCustomer.observe(this) { customer ->
            dataCustomer.clear()
            dataCustomer.addAll(customer)
            itemDataCustomer.addAll(dataCustomer.map { it.nameCustomer })
        }
    }

    private fun showSimpleDialogName() {
        showSimpleListDialog(
            title = getString(R.string.select_name),
            items = itemDataCustomer,
            onItemClicked = {
                binding.edtName.setText(dataCustomer[it].nameCustomer)
                dataCustomerIdSelected = dataCustomer[it].idCustomer
            }
        )
    }

    private fun showRecyclerList() {
        orderListAdapter = OrderListAdapter(data) { itemEntity ->
            val dialog = MaterialDialog(this)
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
            rvOrder.layoutManager = LinearLayoutManager(applicationContext)
            rvOrder.setHasFixedSize(true)
            rvOrder.adapter = orderListAdapter
            orderListAdapter.callbackInterface = object : CallbackInterface {
                override fun passResultCallback(totalValue: String, totalQuantity: Int) {
                    tvSubTotalValue.text = convertCurrency(totalValue.toDouble())
                    dataPPN = totalValue.toDouble() / 100 * 11
                    tvPPNValue.text = convertCurrency(dataPPN)
                    val total = dataPPN + totalValue.toDouble()
                    dataQuantity = totalQuantity
                    dataTotal = total
                    tvTotalValue.text = convertCurrency(total)
                    btnCharge.text = "Checkout ($totalQuantity)"
                }
            }
        }
    }
}