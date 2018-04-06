package com.iwenchaos.fakkot.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.adapter.TypeArticlePageAdapter
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.bean.TreeListResponse
import com.iwenchaos.fakkot.bean.TreeListResponse.Data.Children
import com.iwenchaos.fakkot.cnostant.Constant
import kotlinx.android.synthetic.main.activity_type_content.*

/**
 * Created by chaos
 * on 2018/3/24. 18:04
 * 文件描述：分类资料
 */
class TypeContentActivity : BaseActivity() {

    private lateinit var toolTitle: String
    private val list = mutableListOf<Children>()
    private var target: Boolean = false
    private val typeArticlePagerAdapter: TypeArticlePageAdapter by lazy {
        TypeArticlePageAdapter(list, supportFragmentManager)
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.typeSecondToolbar).init()
    }

    override fun setLayoutId(): Int = R.layout.activity_type_content


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置toolbar
        typeSecondToolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        intent.extras?.let {
            target = it.getBoolean(Constant.CONTENT_TARGET_KEY, false)
            it.getString(Constant.CONTENT_TITLE_KEY)?.let {
                toolTitle = it
                typeSecondToolbar.title = it
            }
            if (target) {
                list.add(Children(it.getInt(Constant.CONTENT_CID_KEY, 0), toolTitle,
                        0, 0, 0, 0, null))
            } else {
                it.getSerializable(Constant.CONTENT_CHILDREN_DATA_KEY)?.let {
                    val data = it as TreeListResponse.Data
                    data.children?.let { children ->
                        list.addAll(children)
                    }
                }
            }
        }
        //view
        typeSecondPages.run {
            adapter = typeArticlePagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(typeSecondTabs))
        }
        //tab 与 page绑定
        typeSecondTabs.run {
            setupWithViewPager(typeSecondPages)
            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(typeSecondPages))
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_type_content, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menuSearch -> {
                Intent(this, SearchActivity::class.java).run {
                    startActivity(this)
                }
                return true
            }
            R.id.menuShare -> {
                Intent().run {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,
                            getString(R.string.share_type_url, getString(R.string.app_name),
                                    list[typeSecondTabs.selectedTabPosition].name,
                                    list[typeSecondTabs.selectedTabPosition].id)
                    )
                    type = Constant.CONTENT_SHARE_TYPE
                    startActivity(Intent.createChooser(this, getString(R.string.share_title)))
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun cancelRequest() {
    }
}