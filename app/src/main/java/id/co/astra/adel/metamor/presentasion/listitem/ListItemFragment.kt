package id.co.astra.adel.metamor.presentasion.listitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.FragmentListItemBinding
import id.co.astra.adel.metamor.presentasion.additem.AddItemViewModel
import id.co.astra.adel.metamor.presentasion.listitem.adapter.ListItemAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListItemFragment : Fragment() {

    private var _binding: FragmentListItemBinding? = null
    private val addItemViewModel: AddItemViewModel by viewModel()
    private val listItemAdapter: ListItemAdapter by lazy {
        ListItemAdapter(
            requireActivity(),
            mutableListOf(),
            onClickListener = { addItem ->
                val dialog = MaterialDialog(requireActivity())
                    .cancelable(false)
                    .cancelOnTouchOutside(false)
                    .title(text = getString(R.string.label_notif))
                    .message(text = getString(R.string.label_confirm_delete_item))
                    .positiveButton(text = getString(R.string.label_ok)) {
                        lifecycleScope.launch {
                            addItemViewModel.deleteItem(addItem.idItem)
                        }
                    }
                    .negativeButton(text = getString(R.string.label_no)){
                        it.dismiss()
                    }
                dialog.show()
            }
        )
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
        addItemViewModel.getAllItem.observe(viewLifecycleOwner) {
            listItemAdapter.setListMetamor(it)
            listItemAdapter.notifyDataSetChanged()
        }
    }

    private fun showRecyclerList() {
        binding.rvItemMetamor.layoutManager = LinearLayoutManager(context)
        binding.rvItemMetamor.setHasFixedSize(true)
        binding.rvItemMetamor.adapter = listItemAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}