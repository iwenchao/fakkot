package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.TreeListResponse
import com.iwenchaos.fakkot.contract.TypeContract
import com.iwenchaos.fakkot.model.TypeModelImpl

/**
 * Created by chaos
 * on 2018/4/5. 19:42
 * 文件描述：
 */
class TypePresenterImpl(val typeView: TypeContract.TypeView) : TypeContract.TypePresenter {

    private val typeModel  = TypeModelImpl()//类型推断

    override fun getTypeList() {
        typeModel.getTypeList(object :OnRequstCallback<TreeListResponse>{
            /**
             * 请求成功
             */
            override fun success(result: TreeListResponse) {
                if(result.errorCode != 0){
                    typeView.getTypeListFail(result.errorMsg)
                }else{
                    typeView.getTypeListSuccess(result)
                }

            }

            /**
             * 请求失败
             */
            override fun fail(errorMsg: String) {
                typeView.getTypeListFail(errorMsg)
            }

        })
    }


}