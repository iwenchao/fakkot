package com.iwenchaos.fakkot.contract

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.TreeListResponse

/**
 * Created by chaos
 * on 2018/4/5. 19:40
 * 文件描述：
 */
interface TypeContract {

    interface TypeModel{
        fun getTypeList(callback: OnRequstCallback<TreeListResponse>)
    }

    interface TypeView{

        fun getTypeListSuccess(result:TreeListResponse)
        fun getTypeListFail(errorMsg:String?)

    }

    interface TypePresenter{

        fun getTypeList()
    }

}