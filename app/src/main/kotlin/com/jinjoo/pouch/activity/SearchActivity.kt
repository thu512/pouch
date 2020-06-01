package com.jinjoo.pouch.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.integration.android.IntentIntegrator
import com.jinjoo.pouch.R
import com.jinjoo.pouch.adapter.SearchAdapter
import com.jinjoo.pouch.bacode.CustomScannerActivity
import com.jinjoo.pouch.api.model.Cosmetic
import com.jinjoo.pouch.api.model.pub.Res
import com.jinjoo.pouch.api.provideImgApi
import com.jinjoo.pouch.api.provideSearchCosApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : Activity(), SearchAdapter.ItemClickListener {

    internal val adapter by lazy {
        SearchAdapter().apply { setItemClickListener(this@SearchActivity) }
    }
    private var list: MutableList<Cosmetic> = mutableListOf()
    internal val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    internal val disposables = CompositeDisposable()
    internal val nameApi by lazy { provideSearchCosApi() }
    internal val imgApi by lazy { provideImgApi() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        with(search_list) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = this@SearchActivity.adapter
        }


        //직접입력 버튼
        direct.setOnClickListener {

        }

        //바코드 버튼
        bacode.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setBeepEnabled(false)
            integrator.captureActivity = CustomScannerActivity::class.java
            integrator.initiateScan()
        }

        //검색 버튼
        search.setOnClickListener {
            imm.hideSoftInputFromWindow(name.windowToken, 0)
            existDataView(true)
            list = mutableListOf()
            getCosmetic(name.text.toString())
            adapter.setItems(list)
        }
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    fun existDataView(exist: Boolean) {
        if (exist) {
            search_list.visibility = View.VISIBLE
            no_item.visibility = View.GONE
        } else {
            search_list.visibility = View.GONE
            no_item.visibility = View.VISIBLE
        }
    }

    fun getCosmetic(itemName: String) {
        disposables.add(nameApi.cos(itemName, 50)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showPd() }
                .doOnError {
                    stopPd()
                    Log.d("jinjoo", "실패1: "+it.localizedMessage)
                }
                .doOnComplete { stopPd() }
                .subscribe({ res ->
                    Log.d("jinjoo", "개수: " + (res.body?.totalCount ?: -1))
                    if (res.body?.totalCount == "0") {
                        //검색 결과 없을때
                        existDataView(false)
                    } else {
                        for (i in res.body?.items!!) {
                            i.ITEM_NAME?.let { getImg(it, Cosmetic(i.ITEM_NAME!!, i.ENTP_NAME!!)) }
                        }
                    }
                }) { Log.d("jinjoo", "실패: " + it.localizedMessage) })
    }

    fun getImg(name: String, cos: Cosmetic) {
        disposables.add(imgApi.img(resources.getString(R.string.naver_id), resources.getString(R.string.naver_key), name, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Log.d("jinjoo/getImg1", "실패" + t.localizedMessage) }
                .subscribe({ res ->
                    cos.img = res.items[0].image
                    Log.d("jinjoof", "사진: " + cos.img)
                    list.add(cos)
                    with(adapter){
                        adapter.setItems(list)
                        adapter.notifyDataSetChanged()
                    }
                }) { Log.d("jinjoo/getImg2", "실패: " + it.localizedMessage) })
    }

    override fun onItemClick(cos: Cosmetic) {
        //아이템 클릭시 처리
        Toast.makeText(applicationContext,"등록: "+cos.name,Toast.LENGTH_SHORT).show()
    }
}