package com.iwenchaos.fakkot.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.adapter.TypeAdapter
import com.iwenchaos.fakkot.base.BaseFragment
import com.iwenchaos.fakkot.bean.TreeListResponse
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.TypeContract
import com.iwenchaos.fakkot.presenter.TypePresenterImpl
import com.iwenchaos.fakkot.toast
import com.iwenchaos.fakkot.ui.activity.TypeContentActivity
import kotlinx.android.synthetic.main.fragment_type.*

/**
 * Created by chaos
 * on 2018/4/5. 19:37
 * 文件描述：
 */
class TypeFragment : BaseFragment(), TypeContract.TypeView {


    private var mainView: View? = null
    private val datas = mutableListOf<TreeListResponse.Data>()
    private val typePresenter: TypeContract.TypePresenter by lazy {
        TypePresenterImpl(this)
    }
    private val typeAdapter: TypeAdapter by lazy {
        TypeAdapter(activity, datas)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView ?: let {
            mainView = inflater?.inflate(R.layout.fragment_type, container, false)
        }
        return mainView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        typeSwipeLayout.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
        }

        typeRecyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = typeAdapter
        }
        typeAdapter.run {
            bindToRecyclerView(typeRecyclerView)
            setEmptyView(R.layout.fragment_home_empty)
            onItemClickListener = this@TypeFragment.onItemClickListener
        }
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden && isFirst) {
            typePresenter.getTypeList()
        }
    }

    fun refreshData() {
        typeSwipeLayout.isRefreshing = true
        //获取数据
        typePresenter.getTypeList()
    }

    override fun getTypeListSuccess(result: TreeListResponse) {
        result.data.let {
            if (typeSwipeLayout.isRefreshing) {
                typeAdapter.replaceData(it)
            } else {
                typeAdapter.addData(it)
            }
        }
        typeSwipeLayout.isRefreshing = false
    }

    override fun getTypeListFail(errorMsg: String?) {
        errorMsg?.let {
            activity.toast(it)
        } ?: let {
            activity.toast(R.string.load_failed)
        }
        typeSwipeLayout.isRefreshing = false
    }

    fun smoothScrollToPosition() = typeRecyclerView.smoothScrollToPosition(0)


    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        refreshData()
    }

    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, pos ->
        if (datas.size != 0) {
            Intent(activity, TypeContentActivity::class.java).run {
                putExtra(Constant.CONTENT_TITLE_KEY, datas[pos].name)
                putExtra(Constant.CONTENT_CHILDREN_DATA_KEY, datas[pos])
                startActivity(this)
            }
        }
    }


    override fun cancelRequest() {

    }
}