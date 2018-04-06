package com.iwenchaos.fakkot.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.iwenchaos.fakkot.bean.TreeListResponse
import com.iwenchaos.fakkot.ui.fragment.TypeArticleFragment

/**
 * Created by chaos
 * on 2018/4/6. 11:09
 * 文件描述：相关资料
 */
class TypeArticlePageAdapter(val list: MutableList<TreeListResponse.Data.Children>,
                             fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val listFragment = mutableListOf<Fragment>()

    init {
        list.forEach { listFragment.add(TypeArticleFragment.newInstance(it.id)) }
    }

    override fun getItem(position: Int): Fragment = listFragment[position]

    override fun getCount(): Int = list.size


    override fun getPageTitle(position: Int): CharSequence = list[position].name

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

}
