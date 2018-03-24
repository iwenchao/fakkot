package com.iwenchaos.fakkot.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.adapter.BannerAdapter
import com.iwenchaos.fakkot.adapter.HomeAdapter
import com.iwenchaos.fakkot.base.BaseFragment
import com.iwenchaos.fakkot.base.Preference
import com.iwenchaos.fakkot.bean.BannerResponse
import com.iwenchaos.fakkot.bean.Datas
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.HomeContract
import com.iwenchaos.fakkot.inflate
import com.iwenchaos.fakkot.presenter.HomePresenterImpl
import com.iwenchaos.fakkot.toast
import com.iwenchaos.fakkot.ui.activity.ContentActivity
import com.iwenchaos.fakkot.ui.activity.LoginActivity
import com.iwenchaos.fakkot.ui.activity.TypeContentActivity
import com.iwenchaos.fakkot.ui.widget.HorizontalRecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Created by chaos
 * on 2018/3/24. 15:15
 * 文件描述：
 */
class HomeFragment : BaseFragment(), HomeContract.View {

    companion object {
        private const val BANNER_TIME = 5 * 1000L
    }

    private var mainView: View? = null
    private lateinit var bannerRecyclerView: HorizontalRecyclerView
    private val datas = mutableListOf<Datas>()
    private val bannerDatas = mutableListOf<BannerResponse.Data>()
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY,false)

    //TODO 问题1：这里presenter继承接口类型的时候，会出错；必须显示为当前类型？？？
    private val homePresenter: HomePresenterImpl by lazy {
        HomePresenterImpl(this)
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(activity, datas)
    }
    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter(activity, bannerDatas)
    }

    private val linearlayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private val bannerPagerSnap: PagerSnapHelper by lazy {
        PagerSnapHelper()
    }

    private var bannerSwitchJob: Job? = null
    private var currentIndex = 0

    private val onRreshListener = SwipeRefreshLayout.OnRefreshListener {
        refreshData()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //设置refreshLayout
        swipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener(onRreshListener)
        }
        //设置recyclerView
        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        bannerRecyclerView.run {
            layoutManager = linearlayoutManager
            bannerPagerSnap.attachToRecyclerView(this)
            requestDisallowInterceptTouchEvent(true)
            setOnTouchListener(onTouchListener)
            addOnScrollListener(onScrollListener)
        }
        bannerAdapter.run {
            bindToRecyclerView(bannerRecyclerView)
            onItemClickListener  = this@HomeFragment.onBannerItemClickListener
        }
        homeAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener,recyclerView)
            onItemClickListener = this@HomeFragment.onItemClickListener
            onItemChildClickListener = this@HomeFragment.onItemChildClickListener
            addHeaderView(bannerRecyclerView)
            setEmptyView(R.layout.fragment_home_empty)
        }

        homePresenter.getBanner()
        homePresenter.getHomeList()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView ?: let {
            mainView = inflater.inflate(R.layout.fragment_home, container, false)
            bannerRecyclerView = activity.inflate(R.layout.home_banner) as HorizontalRecyclerView
        }
        return mainView
    }


    fun refreshData() {
        swipeRefreshLayout.isRefreshing = true

    }


    override fun getHomeListSuccess(result: HomeListResponse) {
    }

    override fun getHomeListFailed(errorMessage: String?) {
    }

    override fun getHomeListZero() {
    }

    override fun getHomeListSmall(result: HomeListResponse) {
    }

    override fun getBannerSuccess(result: BannerResponse) {
    }

    override fun getBannerFailed(errorMessage: String?) {
    }

    override fun getBannerZero() {
    }


    override fun cancelRequest() {

    }


    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
                if (datas.size != 0) {
                    val data = datas[position]
                    when (view.id) {
                        R.id.homeItemType -> {
                            data.chapterName ?: let {
                                activity.toast(getString(R.string.type_null))
                                return@OnItemChildClickListener
                            }
                            Intent(activity, TypeContentActivity::class.java).run {
                                putExtra(Constant.CONTENT_TARGET_KEY, true)
                                putExtra(Constant.CONTENT_TITLE_KEY, data.chapterName)
                                putExtra(Constant.CONTENT_CID_KEY, data.chapterId)
                                startActivity(this)
                            }
                        }
                        R.id.homeItemLike -> {
                            if (isLogin) {
                                val collect = data.collect
                                data.collect = !collect
                                homeAdapter.setData(position, data)
//                                homePresenter.collectArticle(data.id, !collect)
                            } else {
                                Intent(activity, LoginActivity::class.java).run {
                                    startActivityForResult(this, Constant.MAIN_REQUEST_CODE)
                                }
                                activity.toast(getString(R.string.login_please_login))
                            }
                        }
                    }
                }
            }

    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener{_,_,position ->
        if (datas.size != 0){
            Intent(activity,ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, datas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, datas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, datas[position].title)
                startActivity(this)
            }
        }
    }

    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        val  page = homeAdapter.data.size /20 +1
        homePresenter.getHomeList(page)
    }

    private val onBannerItemClickListener = BaseQuickAdapter.OnItemClickListener{_,_,position ->
        if (bannerDatas.size  != 0){
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY,bannerDatas[position].url)
                putExtra(Constant.CONTENT_TITLE_KEY,bannerDatas[position].title)
                startActivity(this)
            }
        }
    }


    private val onScrollListener =  object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when(newState){
                RecyclerView.SCROLL_STATE_IDLE -> {
                    currentIndex = linearlayoutManager.findFirstVisibleItemPosition()
                    startSwitchJob()
                }
            }
        }
    }


    private val onTouchListener = View.OnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                cancelSwitchJob()
            }
        }
        false
    }

    private fun getBannerSwitchJob() = launch {
        repeat(Int.MAX_VALUE){
            if (bannerDatas.size == 0){
                return@launch
            }
            delay(BANNER_TIME)
            currentIndex++
            val index = currentIndex % bannerDatas.size
            bannerRecyclerView.smoothScrollToPosition(index)
            currentIndex = index
        }
    }


    private fun startSwitchJob() = bannerSwitchJob?.run {
        if (!isActive){
            bannerSwitchJob = getBannerSwitchJob().apply {
                start()
            }
        }
    } ?: let {
        bannerSwitchJob = getBannerSwitchJob().apply { start() }
    }

    private fun cancelSwitchJob() = bannerSwitchJob?.run {
        if (isActive) {
            cancel()
        }
    }
}