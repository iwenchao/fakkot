package com.iwenchaos.fakkot.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.base.Preference
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.action.CollectArticleView
import com.iwenchaos.fakkot.getAgentWeb
import com.iwenchaos.fakkot.presenter.ContentPresenterImpl
import com.iwenchaos.fakkot.toast
import com.just.agentweb.AgentWeb
import com.just.agentweb.ChromeClientCallbackManager
import kotlinx.android.synthetic.main.activity_content.*

/**
 * Created by chaos
 * on 2018/3/24. 17:53
 * 文件描述：
 */
class ContentActivity : BaseActivity(), CollectArticleView {


    private lateinit var agentWeb: AgentWeb
    private lateinit var shareTitle: String
    private lateinit var shareUrl: String
    private var shareId: Int = 0
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    private val collectArticlePresenter: ContentPresenterImpl by lazy {
        ContentPresenterImpl(this)
    }

    private val receivedTitleCallback = ChromeClientCallbackManager.ReceivedTitleCallback { _, title ->
        title?.let {
            toolbar.title = it
        }
    }


    /**
     * 初始化web页的immersion bar
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun setLayoutId(): Int = R.layout.activity_content


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = getString(R.string.loading)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        intent.extras?.let {
            shareId = it.getInt(Constant.CONTENT_ID_KEY, 0)
            shareUrl = it.getString(Constant.CONTENT_URL_KEY)
            shareTitle = it.getString(Constant.CONTENT_TITLE_KEY)
            agentWeb = shareUrl.getAgentWeb(this,
                    webContent, LinearLayout.LayoutParams(-1, -1), receivedTitleCallback)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_content, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menuShare -> {
                Intent().run {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, getString(
                            R.string.share_article_url,
                            getString(R.string.app_name),
                            shareTitle,
                            shareUrl
                    ))
                    type = Constant.CONTENT_SHARE_TYPE
                    startActivity(Intent.createChooser(this, getString(R.string.share_title)))
                }
                return true
            }
            R.id.menuLike -> {
                if (!isLogin) {
                    Intent(this, LoginActivity::class.java).run {
                        startActivity(this)
                    }
                    toast(R.string.login_please_login)
                    return true
                }
                if (shareId == 0) {
                    collectArticlePresenter.collectOutSideArticle(
                            shareTitle, getString(R.string.outside_title),
                            shareUrl, true)
                    return true
                }
                collectArticlePresenter.collectArticle(shareId, true)
                return true
            }
            R.id.menuBrowser -> {
                Intent().run {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse(shareUrl)
                    startActivity(this)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()

    }

    override fun onPause() {
        agentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    /**
     * add article success
     * @param result HomeListResponse
     * @param isAdd true add, false remove
     */
    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        toast(if (isAdd) getString(R.string.bookmark_success) else getString(R.string.bookmark_cancel_success))
    }

    /**
     * add article false
     * @param errorMessage error message
     * @param isAdd true add, false remove
     */
    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        toast(
                if (isAdd)
                    getString(R.string.bookmark_failed, errorMessage)
                else
                    getString(R.string.bookmark_cancel_failed, errorMessage))
    }


    override fun cancelRequest() {
    }
}