package com.techmahindra.techmEmployeePortal.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.data.response.AddEmployeeInfo
import com.techmahindra.techmEmployeePortal.utils.loadImage
import com.techmahindra.techmEmployeePortal.view.TechmEmployeeListActivity.Companion.deleteEmp
import kotlinx.android.synthetic.main.adapter_employee_information.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * AdapterEmployeeInformation - This adapter helps to bind the employee info to recyclerview
 * */
class AdapterEmployeeInformation(
    private var addEmployeeInfoList: ArrayList<AddEmployeeInfo>,
    private val context: Context?,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<AdapterEmployeeInformation.ViewHolder>(), Filterable {
    private var employeeFilterList = ArrayList<AddEmployeeInfo>()

    init {
        employeeFilterList.addAll(addEmployeeInfoList)
    }

    // return total list item
    override fun getItemCount(): Int {
        return addEmployeeInfoList.size
    }


    // bind layout to set the data to views
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_employee_information, parent, false)
        return ViewHolder(v)
    }

    // bind viewholder with data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.employeeName.text =
            addEmployeeInfoList[position].name + " (" + addEmployeeInfoList[position].band + ")"
        holder.designation.text = addEmployeeInfoList[position].designation
        holder.project.text = addEmployeeInfoList[position].project
        holder.employeeId.text = addEmployeeInfoList[position].employeeId.toString()
        holder.competency.text = addEmployeeInfoList[position].competency

        holder.bind(addEmployeeInfoList[position], position, listener)

        when (addEmployeeInfoList[position].competency) {
            context?.resources?.getString(R.string.android) ->
                holder.employeeImage.loadImage(context.resources.getDrawable(R.drawable.android))
            context?.resources?.getString(R.string.ios) ->
                holder.employeeImage.loadImage(context.resources.getDrawable(R.drawable.ios))
            context?.resources?.getString(R.string.ux) ->
                holder.employeeImage.loadImage(context.resources.getDrawable(R.drawable.ux))
            context?.resources?.getString(R.string.tester) ->
                holder.employeeImage.loadImage(context.resources.getDrawable(R.drawable.tester))

        }
    }

    // delete item at perticular position
    fun delete(position: Int) {
        this.addEmployeeInfoList.removeAt(position)
        notifyItemRemoved(position)
    }

    // return selected item position
    fun getItemAtPosition(position: Int): AddEmployeeInfo {
        return this.addEmployeeInfoList[position]
    }

    // load data using list
    fun loadTechmEmployeeInfo(dataInformation: List<AddEmployeeInfo>) {
        this.addEmployeeInfoList = dataInformation as ArrayList<AddEmployeeInfo>
        employeeFilterList.addAll(addEmployeeInfoList)
        notifyDataSetChanged()
    }

    // filter the employee list by input text of searchview, it compare each and every char
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    employeeFilterList = addEmployeeInfoList
                } else {
                    val resultList = ArrayList<AddEmployeeInfo>()
                    for (row in addEmployeeInfoList) {
                        if (row.name.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    employeeFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = employeeFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                employeeFilterList = results?.values as ArrayList<AddEmployeeInfo>
                notifyDataSetChanged()
            }

        }
    }

    interface ItemClickListener {
        fun onItemClick(mAddEmployeeInfo: AddEmployeeInfo, position: Int)
    }

    // delete item at swiped position from list
    fun removeAt(position: Int) {
        deleteEmp = position
        addEmployeeInfoList.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            AddEmployeeInfo: AddEmployeeInfo,
            position: Int,
            listener: AdapterEmployeeInformation.ItemClickListener
        ) {
            itemView.setOnClickListener {
                listener.onItemClick(AddEmployeeInfo, position)
            }
        }

        val employeeName: TextView = view.textViewEmployeeNameBand
        val designation: TextView = view.textViewEmpDesignation
        val project: TextView = view.textViewEmployeeProject
        val employeeImage: ImageView = view.imageViewProfile
        val employeeId: TextView = view.textViewEmpyoyeeId
        val competency: TextView = view.textViewEmployeeCompetency

    }
}