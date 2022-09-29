package id.co.astra.adel.metamor.presentasion.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ActivityPaymentBinding
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.domain.customer.model.Customer
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.presentasion.checkout.CheckoutViewModel
import id.co.astra.adel.metamor.presentasion.order.OrderViewModel
import id.co.astra.adel.metamor.utils.Constants.PPN
import id.co.astra.adel.metamor.utils.Constants.QUANTITY
import id.co.astra.adel.metamor.utils.Constants.TOTAL
import id.co.astra.adel.metamor.utils.DataMapper
import id.co.astra.adel.metamor.utils.convertCurrency
import id.co.astra.adel.metamor.utils.customDialogYesOrNo
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.util.*

class PaymentActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPaymentBinding
    private var total: Double = 0.0
    private var ppn: Double = 0.0
    private var quantity = 0
    private var cash: Double = 0.0
    private var cashReturn: Double = 0.0
    private var dataCustomer: Customer? = null
    private var typePayment: String? = null
    private var dataOrder = ArrayList<Order>()
    private val dateToday = LocalDateTime.now()
    private val viewModelCheckout: CheckoutViewModel by viewModel()
    private val orderViewModel: OrderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        total = intent.getDoubleExtra(TOTAL, 0.0)
        ppn = intent.getDoubleExtra(PPN, 0.0)
        quantity = intent.getIntExtra(QUANTITY, 0)
//        dataCustomer = intent.getParcelableExtra<Customer>(DATA_CUSTOMER)
        initUI()
        getOrderObserver()
    }

    private fun initUI() {
        with(binding) {
            Toast.makeText(this@PaymentActivity, total.toString(), Toast.LENGTH_SHORT).show()
            tvTotalValue.text = convertCurrency(total)
            btn20000.setOnClickListener(this@PaymentActivity)
            btn50000.setOnClickListener(this@PaymentActivity)
            btn100000.setOnClickListener(this@PaymentActivity)
            btn200000.setOnClickListener(this@PaymentActivity)
            btnCharge.setOnClickListener(this@PaymentActivity)
            btnDineIn.setOnClickListener(this@PaymentActivity)
            btnTakeAway.setOnClickListener(this@PaymentActivity)
            edtMoney.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    val money = edtMoney.text.toString().trim()
                    if(money.isEmpty()) {
                        edtMoney.hint = getString(R.string.label_cash)
                    } else {
                        val totalReturn = p0.toString().toDouble() - total
                        if(totalReturn <= 0.0) {
                            tvReturnCash.text = getString(R.string.label_return_cash_min)
                            cashReturn = totalReturn.toString().toDouble()
                        } else {
                            tvReturnCash.text = "Jumlah Kembalian: ${convertCurrency(totalReturn)}"
                            cashReturn = totalReturn.toString().toDouble()
                            cash = money.toDouble()
                        }
                    }

                }

            })
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn20000 -> binding.edtMoney.setText(20000.toString())
            R.id.btn50000 -> binding.edtMoney.setText(50000.toString())
            R.id.btn100000 -> binding.edtMoney.setText(100000.toString())
            R.id.btn200000 -> binding.edtMoney.setText(200000.toString())
            R.id.btnCharge -> {
                if(typePayment?.isEmpty() == true) {
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                } else {
                    val randomId = Random().nextInt(1000000)
                    customDialogYesOrNo(
                        this,
                        cancelable = true,
                        message = getString(R.string.label_price),
                        positiveButtonListener = {
                            intentToInvoice(randomId)
                        }
                    )
                }

            }
            R.id.btnDineIn -> {
                typePayment = getString(R.string.label_dine_in)
            }
            R.id.btnTakeAway -> typePayment = getString(R.string.label_take_away)
        }
    }

    private fun getOrderObserver() {
        orderViewModel.getAllOrder.observe(this) { order ->
            dataOrder.addAll(order)
        }
    }

    private fun intentToInvoice(id: Int) {
//        if(id == id) {
//            customDialogOk(
//                this,
//                cancelable = true,
//                message = getString(R.string.label_failed_save_price)
//            )
//        } else {
            val checkout = Checkout(
                0,
                dataCustomer?.nameCustomer.toString(),
                dataCustomer?.numberPhone ?: 0,
                dataCustomer?.email.toString(),
                total,
                ppn,
                quantity,
                typePayment.toString(),
                cash,
                cashReturn,
                dateToday.toString(),
                DataMapper.mapOrderToCheckoutList(dataOrder)
            )
            lifecycleScope.launch {
                viewModelCheckout.insertCheckout(checkout)
            }
//            val intent = Intent(this, InvoiceDetailActivity::class.java)
//            intent.putExtra(CHECKOUT_ID, id)
//            startActivity(intent)
//        }
    }
}