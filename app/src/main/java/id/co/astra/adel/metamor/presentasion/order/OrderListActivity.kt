package id.co.astra.adel.metamor.presentasion.order

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ActivityOrderListBinding
import id.co.astra.adel.metamor.domain.customer.model.Customer
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding
    private val orderViewModel: OrderViewModel by viewModel()
    private val customerViewModel: CustomerViewModel by viewModel()
    private val saveOrderViewModel: SaveOrderViewModel by viewModel()
    private lateinit var orderListAdapter: OrderListAdapter
    private var data = mutableListOf<Order>()
    private var dataOrder = mutableListOf<Order>()
    private var dataCustomer = mutableListOf<Customer>()
    private var dataCustomerSelected : Customer? = null
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
        val saveOrderRequest = SaveOrder(
            0,
            dataCustomerSelected?.nameCustomer.toString(),
            dataCustomerSelected?.numberPhone ?: 0,
            dataCustomerSelected?.email.toString(),
            DataMapper.mapOrderToSaveOrderList(dataOrder)
        )
        lifecycleScope.launch {
            saveOrderViewModel.insertSaveOrder(saveOrderRequest)
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
                dataCustomerSelected = dataCustomer[it]
            }
        )
    }

    private fun showRecyclerList() {
        orderListAdapter = OrderListAdapter(this, data) { itemEntity ->
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