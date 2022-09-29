package id.co.astra.adel.metamor.presentasion.invoice

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ActivityInvoiceDetailBinding
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.domain.checkout.model.CheckoutList
import id.co.astra.adel.metamor.presentasion.checkout.CheckoutViewModel
import id.co.astra.adel.metamor.utils.Constants.CHECKOUT_ID
import id.co.astra.adel.metamor.utils.convertCurrency
import id.co.astra.adel.metamor.utils.convertPatternDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInvoiceDetailBinding
    private var dataCheckout: Checkout? = null
    private var dataCheckoutList = ArrayList<CheckoutList>()
    private val checkoutViewModel: CheckoutViewModel by viewModel()
    private lateinit var invoiceDetailAdapter: InvoiceDetailAdapter
    private var printing: Printing? = null
    private var outputDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Printooth.hasPairedPrinter())
            printing = Printooth.printer()
        val checkoutId = intent.getIntExtra(CHECKOUT_ID, 0)
        checkoutViewModel.getCheckoutById(checkoutId).observe(this) { checkout ->
            dataCheckout = checkout
            checkout.checkoutItem.let {
                dataCheckoutList.addAll(it)
                invoiceDetailAdapter = InvoiceDetailAdapter(
                    this,
                    it
                )
            }
            initUIInvoice(checkout)
        }


    }

    @SuppressLint("SetTextI18n")
    private fun initUIInvoice(dataOrder: Checkout) {
        outputDate = convertPatternDate(dataOrder.date)

        with(binding) {
            tvNameValue.text = ": ${dataOrder.nameCustomer}"
            tvNumberPhoneValue.text = ": ${dataOrder.numberPhone}"
            tvEmailValue.text = ": ${dataOrder.email}"
            tvTypePaymentValue.text = ": ${dataOrder.typePayment}"
            tvDate.text = outputDate
            tvQuantityValue.text = dataOrder.quantity.toString()
            tvPPNValue.text = convertCurrency(dataOrder.ppn)
            tvTotalValue.text = convertCurrency(dataOrder.total)
            rvListOrder.layoutManager = LinearLayoutManager(applicationContext)
            rvListOrder.setHasFixedSize(true)
            rvListOrder.adapter = invoiceDetailAdapter
            btnPrint.setOnClickListener {
                if (!Printooth.hasPairedPrinter()) startActivityForResult(
                    Intent(
                        this@InvoiceDetailActivity,
                        ScanningActivity::class.java
                    ),
                    ScanningActivity.SCANNING_FOR_PRINTER
                )
                else printSomePrintable()
            }
        }
    }

    private fun printSomePrintable() {
        val printable = getSomePrintable()
        printing?.print(printable)
    }

    private fun getSomePrintable() = ArrayList<Printable>().apply {
        add(
            ImagePrintable.Builder(R.drawable.metamor, resources)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText(getString(R.string.label_anddress))
                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText(getString(R.string.label_phone_number))
                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setNewLinesAfter(1)
                .build()
        )
        add(
            TextPrintable.Builder()
                .setText(getString(R.string.label_symbol))
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Nama Customer : ${dataCheckout?.nameCustomer}")
                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText(outputDate.toString())
                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Tipe Pembelian : ${dataCheckout?.typePayment}")
                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText(getString(R.string.label_symbol_line))
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                .setUnderlined(DefaultPrinter.UNDERLINED_MODE_ON)
                .setNewLinesAfter(1)
                .build()
        )

        for(i in dataCheckoutList.indices) {
            add(
                TextPrintable.Builder()
                    .setText("${dataCheckoutList.getOrNull(i)?.nameItem}         ${dataCheckoutList.getOrNull(i)?.quantityItem} x Rp. ${dataCheckoutList.getOrNull(i)?.priceItem}")
                    .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setNewLinesAfter(1)
                    .build()
            )
        }

        add(
            TextPrintable.Builder()
                .setText(getString(R.string.label_symbol_line))
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                .setUnderlined(DefaultPrinter.UNDERLINED_MODE_ON)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Sub - Total Rp. 96.000")
                .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Pajak (11%) Rp. ${dataCheckout?.ppn}")
                .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Tunai Rp. ${dataCheckout?.cash}")
                .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Total Kembalian Rp. ${dataCheckout?.cashReturn}")
                .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText("Total Rp. ${dataCheckout?.total}")
                .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                .setNewLinesAfter(1)
                .build()
        )

        add(
            TextPrintable.Builder()
                .setText(getString(R.string.label_thanks))
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                .setUnderlined(DefaultPrinter.UNDERLINED_MODE_ON)
                .setNewLinesAfter(1)
                .build()
        )

        add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build()) // feed lines example in raw mode
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK)
            printSomePrintable()
    }
}