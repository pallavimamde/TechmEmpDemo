package com.techmahindra.techmEmployeePortal.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.techmahindra.techmEmployeePortal.R
import com.techmahindra.techmEmployeePortal.roomdatabase.ProjectInfo
import kotlinx.android.synthetic.main.adapter_project_info.view.*

/*
* ProjectSpinnerAdapter - Create Project list spinner
* */
class ProjectSpinnerAdapter(val context: Context, private var projectInfoList: ArrayList<ProjectInfo>) :
    BaseAdapter() {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
            view = mInflater.inflate(R.layout.adapter_project_info, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh

        vh.label.text = projectInfoList[position].projectName
        return view
    }

    fun setLIst(projectInfoList: ArrayList<ProjectInfo>) {
        this.projectInfoList=projectInfoList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): ProjectInfo {

        return projectInfoList[position]

    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    override fun getCount(): Int {
        return projectInfoList.size
    }

    fun getPosition(projectName: String): Int {

        run {
            var index = 0
            while (index < projectInfoList.size) {
                if (getItem(index).projectName == projectName) {
                    return index
                }
                ++index
            }
        }
        return 0
    }

    private class ItemRowHolder(row: View) {
        val label: TextView = row.projectInfo
    }
}