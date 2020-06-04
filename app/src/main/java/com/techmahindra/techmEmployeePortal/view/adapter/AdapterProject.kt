package com.techmahindra.techmEmployeePortal.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectInfo
import kotlinx.android.synthetic.main.adapter_project_info.view.*
import kotlin.collections.ArrayList

class AdapterProject(
    private var ProjectInfoList: ArrayList<ProjectInfo>,
    private val context: Context?
) : RecyclerView.Adapter<ViewHolderProject>() {
    private var itemsList = ArrayList<ProjectInfo>()

    init {
        itemsList.addAll(ProjectInfoList)
    }

    // get total list item
    override fun getItemCount(): Int {
        return ProjectInfoList.size
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
        holder.projectName.text = ProjectInfoList[position].projectName

        holder.bind(ProjectInfoList[position], position)

    }

    // delete item at selected location
    fun delete(position: Int) {
        this.ProjectInfoList.removeAt(position)
        notifyItemRemoved(position)
    }

    // load product list
    fun loadProductlistInfo(dataInformation: List<ProjectInfo>) {
        this.ProjectInfoList = dataInformation as ArrayList<ProjectInfo>
        itemsList.addAll(ProjectInfoList)
        notifyDataSetChanged()
    }
}

class ViewHolderProject(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        projectInfo: ProjectInfo,
        position: Int
    ) {

    }

    val projectName: TextView = view.projectInfo
}