package id.co.astra.adel.metamor.presentasion.saveorder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.FragmentSaveOrderBinding
import id.co.astra.adel.metamor.presentasion.order.OrderListActivity
import id.co.astra.adel.metamor.utils.Constants.ID_CUSTOMER
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaveOrderFragment : Fragment() {

    private var _binding: FragmentSaveOrderBinding? = null
    private val binding get() = _binding!!
    private val saveOrderViewModel: SaveOrderViewModel by viewModel()
    private val saveOrderAdapter: SaveOrderAdapter by lazy {
        SaveOrderAdapter(
            requireActivity(),
            mutableListOf(),
            onClickListener = {
                intentToOrder(it.idCustomer)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSaveOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveOrderObserver()
    }

    private fun saveOrderObserver() {
        saveOrderViewModel.getSaveOrder.observe(requireActivity()) {
            saveOrderAdapter.setListSaveOrder(it)
            saveOrderAdapter.notifyDataSetChanged()
            showRecyclerListSaveOrder()
        }
    }

    private fun showRecyclerListSaveOrder() {
        with(binding) {
            this.rvSaveOrder.layoutManager = LinearLayoutManager(context)
            this.rvSaveOrder.setHasFixedSize(true)
            this.rvSaveOrder.adapter = saveOrderAdapter
        }
    }

    private fun intentToOrder(idCustomer: Int) {
        val intent = Intent(requireContext(), OrderListActivity::class.java)
        intent.putExtra(ID_CUSTOMER, idCustomer)
        startActivity(intent)
    }
}