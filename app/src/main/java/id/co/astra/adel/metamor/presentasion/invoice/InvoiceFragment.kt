package id.co.astra.adel.metamor.presentasion.invoice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.databinding.FragmentInvoiceBinding
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.presentasion.checkout.CheckoutViewModel
import id.co.astra.adel.metamor.utils.Constants.CHECKOUT_ID

@AndroidEntryPoint
class InvoiceFragment : Fragment() {

    private var _binding: FragmentInvoiceBinding? = null
    private val binding get() = _binding
    private val checkoutViewModel: CheckoutViewModel by viewModels()
    private val invoiceAdapter: InvoiceAdapter by lazy {
        InvoiceAdapter(
            mutableListOf()
        ) {
            intentToInvoiceDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listInvoiceObserver()
        showRecyclerListInvoice()
    }

    private fun listInvoiceObserver() {
//        checkoutViewModel.getAllCheckout.observe(viewLifecycleOwner) {
//            invoiceAdapter.setListInvoice(it)
//            invoiceAdapter.notifyDataSetChanged()
//        }
    }

    private fun intentToInvoiceDetail(data: Checkout) {
        val intent = Intent(context, InvoiceDetailActivity::class.java)
        intent.putExtra(CHECKOUT_ID, data.idCustomer)
        startActivity(intent)
    }
    private fun showRecyclerListInvoice() {
        with(binding) {
            this?.rvInvoice?.layoutManager = LinearLayoutManager(context)
            this?.rvInvoice?.setHasFixedSize(true)
            this?.rvInvoice?.adapter = invoiceAdapter
        }
    }

}