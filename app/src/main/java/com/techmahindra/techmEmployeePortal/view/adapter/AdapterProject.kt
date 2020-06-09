package com.techmahindra.techmEmployeePortal.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.data.response.ProjectInfo
import com.techmahindra.techmEmployeePortal.view.ProjectActivity.Companion.deleteProject
import kotlinx.android.synthetic.main.adapter_project_info.view.*
import kotlin.collections.ArrayList

class AdapterProject(
    private var projectInfoList: ArrayList<ProjectInfo>,
    private val context: Context?
) : RecyclerView.Adapter<AdapterProject.ViewHolderProject>() {
    private var itemsList = ArrayList<ProjectInfo>()
    var rowIndex: Int

    init {
        itemsList.addAll(projectInfoList)
        rowIndex = -1 // default selected row index
    }

    // get total list item
    override fun getItemCount(): Int {
        return projectInfoList.size
    }


    // create viewholder to create recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProject {
        return ViewHolderProject(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_project_info,
                parent,
                false
            )
        )
    }

    // bind data to viewholder
    override fun onBindViewHolder(holder: ViewHolderProject, position: Int) {
        holder.bind(projectInfoList[position], position)

    }

    // delete item at selected location
    fun delete(position: Int) {
        this.projectInfoList.removeAt(position)
        notifyItemRemoved(position)
    }

    // load product list
    fun loadProductlistInfo(dataInformation: List<ProjectInfo>) {
        this.projectInfoList = dataInformation as ArrayList<ProjectInfo>
        itemsList.addAll(projectInfoList)
        notifyDataSetChanged()
    }

    // delete item at swiped position from list
    fun removeAt(position: Int) {
        deleteProject = projectInfoList[position].projectId
        projectInfoList.removeAt(position)
        notifyItemRemoved(position)
    }


   inner class ViewHolderProject(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(projectrow: ProjectInfo, position: Int) {
            projectName.text = projectrow.projectName

            itemView.setOnClickListener {
                rowIndex = position
                notifyDataSetChanged() // notify when data change
            }
        }

        val projectName: TextView = view.textViewProjectInfo
    }
}