package com.example.nft

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft.adapter.MyAdapter
import com.example.nft.adapter.MyAdapter1
import com.example.nft.model.Item
import com.example.nft.repository.Repository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val myAdapter by lazy { MyAdapter1() }
private lateinit var viewModel: MainViewModel
private lateinit var tempitemArrayList : ArrayList<Item>
/**
 * A simple [Fragment] subclass.
 * Use the [PurshasedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PurshasedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_purshased, container, false)
        val right = view.findViewById<TextView>(R.id.right)
        right.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.favoritesFragment)
            findNavController().navigate(R.id.action_purshasedFragment_to_favoritesFragment)

        }

        val left = view.findViewById<TextView>(R.id.left)
        left.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.onSalesFragment)
            findNavController().navigate(R.id.action_purshasedFragment_to_onSalesFragment)

        }
        var recyclerView = view.findViewById<RecyclerView>(R.id.purshasedrc)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        tempitemArrayList = arrayListOf<Item>()
        viewModel.getPurshased()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {

                response.body()?.let { myAdapter.setData(it) }
                response.body()?.forEach {

                    Log.i("Response",it.tokenId.toString())
                    Log.i("Response",it.seller.toString())
                    Log.i("Response",it.owner.toString())
                    Log.i("Response",it.price.toString())
                    Log.i("Response",it.name.toString())
                    Log.i("Response",it.description.toString())
                    Log.i("Response",it.image.toString())
                    Log.i("Response","------------------------------")

                }


            } else {
                Log.i("Response", "error")

            }
        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PurshasedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PurshasedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}