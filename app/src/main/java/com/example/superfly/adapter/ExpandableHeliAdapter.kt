//package com.example.superfly.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseExpandableListAdapter
//import com.example.superfly.R
//import com.example.superfly.model.Helicopter
//
//class ExpandableHeliAdapter (val  _context: Context, var _listDataHeader ) : BaseExpandableListAdapter(){
//
//    var _listDataHeaderFiltered: ArrayList<Helicopter>
//    var _listDataHeaderOriginal = ArrayList<Helicopter>()
//
//    init {
//        _listDataHeaderFiltered = _listDataHeader
//        _listDataHeaderOriginal.addAll(_listDataHeader)
//    }
//
//    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
//        return _listDataHeaderFiltered[groupPosition]
//    }
//
//    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
//        return childPosition.toLong()
//    }
//
//    override fun getChildView(groupPosition: Int, childPosition: Int,
//                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
//        var convertView = convertView
//
//        val childText = getChild(groupPosition, childPosition) as String
//
//        if (convertView == null) {
//            val infalInflater = this._context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            convertView = infalInflater.inflate(R.layout.ex_child_layout, null)
//        }
//
//        convertView!!.txtFaqAnswer.text = childText
//
//        /*val txtListChild = convertView!!
//                .findViewById(R.id.lblListItem) as TextView
//        txtListChild.text = childText
//        */
//        return convertView!!
//    }
//
//    override fun getChildrenCount(groupPosition: Int): Int {
//        return 1
//    }
//
//    override fun getGroup(groupPosition: Int): Any {
//        return this._listDataHeaderFiltered[groupPosition].question
//    }
//
//    override fun getGroupCount(): Int {
//        return this._listDataHeaderFiltered.size
//    }
//
//    override fun getGroupId(groupPosition: Int): Long {
//        return groupPosition.toLong()
//    }
//
//    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
//                              convertView: View?, parent: ViewGroup): View {
//        var convertView = convertView
//        val headerTitle = getGroup(groupPosition) as String
//        if (convertView == null) {
//            val infalInflater = this._context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            convertView = infalInflater.inflate(R.layout.item_faqs_parent, null)
//        }
//
//        if (groupPosition % 2 == 1) {
//            convertView?.setBackgroundResource(R.color.colorLightGrayBackground)
//        } else {
//            convertView?.setBackgroundResource(R.color.colorDarkGrayBackground)
//        }
//
//        convertView!!.txtFaqs.text = headerTitle
//
//        return convertView!!
//    }
//
//    override fun hasStableIds(): Boolean {
//        return false
//    }
//
//    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
//        return true
//    }
//}
//}