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
import com.iwenchaos.fakkot.adapter.TypeArticleAdapter
import com.iwenchaos.fakkot.base.BaseFragment
import com.iwenchaos.fakkot.base.Preference
import com.iwenchaos.fakkot.bean.ArticleListResponse
import com.iwenchaos.fakkot.bean.Datas
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.TypeContentContract
import com.iwenchaos.fakkot.presenter.TypeContentPresenterImpl
import com.iwenchaos.fakkot.toast
import com.iwenchaos.fakkot.ui.activity.ContentActivity
import com.iwenchaos.fakkot.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_type_content.*

/**
 * Created by chaos
 * on 2018/4/6. 11:11
 * 文件描述：
 */
class TypeArticleFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, TypeContentContract.TypeView {


    private var mainView: View? = null
    private var cid: Int = 0
    private val datas = mutableListOf<Datas>()
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    private val typeArticleAdapter: TypeArticleAdapter by lazy {
        TypeArticleAdapter(activity, datas)
    }
    private val typeArticlePresenter: TypeContentContract.TypePresenter by lazy {
        TypeContentPresenterImpl(this)
    }

    companion object {
        fun newInstance(id: Int): TypeArticleFragment {
            val fragment = TypeArticleFragment()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, id)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView ?: let {
            mainView = inflater.inflate(R.layout.fragment_type_content, container, false)
        }

        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //数据id
        cid = arguments.getInt(Constant.CONTENT_CID_KEY)
        //view
        tabSwipeLayout.run {
            isRefreshing = true
            setOnRefreshListener(this@TypeArticleFragment)
        }
        tabRecyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = typeArticleAdapter
        }

        typeArticleAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, tabRecyclerView)
            onItemClickListener = this@TypeArticleFragment.onItemClickListener
            onItemChildClickListener = this@TypeArticleFragment.onItemChildClickListener
            setEmptyView(R.layout.fragment_home_empty)
        }

        //获取数据
        typeArticlePresenter.getTypeArticleList(cid = cid)
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        tabSwipeLayout.isRefreshing = true
        typeArticleAdapter.setEnableLoadMore(false)
        typeArticlePresenter.getTypeArticleList(cid = cid)
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, datas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, datas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, datas[position].title)
                startActivity(this)
            }
        }
    }
    /**
     * LoadMoreListener
     */
    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        val page = typeArticleAdapter.data.size / 20 + 1
        typeArticlePresenter.getTypeArticleList(page, cid)
    }

    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
                if (datas.size != 0) {
                    val data = datas[position]
                    when (view.id) {
                        R.id.homeItemLike -> {
                            if (isLogin) {
                                val collect = data.collect
                                data.collect = !collect
                                typeArticleAdapter.setData(position, data)
//                                typeArticlePresenter.collectArticle(data.id, !collect)
                                activity.toast("等一下哦")
                            } else {
                                Intent(activity, LoginActivity::class.java).run {
                                    startActivity(this)
                                }
                                activity.toast(getString(R.string.login_please_login))
                            }
                        }
                    }
                }
            }

    override fun getTypeArticleListSuccess(result: ArticleListResponse) {
        result.data.datas?.let {
            typeArticleAdapter.run {
                val total = result.data.total
                if (result.data.offSet >= total || data.size >= total) {
                    loadMoreEnd()
                    return@let
                }
                if (tabSwipeLayout.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                loadMoreComplete()
                setEnableLoadMore(true)
            }
        }
        tabSwipeLayout.isRefreshing = false
    }

    override fun getTypeArticleListFail(errorMsg: String?) {
        typeArticleAdapter.setEnableLoadMore(false)
        typeArticleAdapter.loadMoreFail()
        errorMsg?.let {
            activity.toast(it)
        } ?: let {
            activity.toast(getString(R.string.get_data_error))
        }
        tabSwipeLayout.isRefreshing = false
    }


    override fun cancelRequest() {

    }
}