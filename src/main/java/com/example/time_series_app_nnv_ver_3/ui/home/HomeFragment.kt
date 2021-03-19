package com.example.time_series_app_nnv_ver_3.ui.home

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.time_series_app_nnv_ver_3.R
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var filepath: String= ""
    private  val util = Utilities()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //button_comp.setOnClickListener { onClickComp()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_comp.setOnClickListener{onClickComp()}
        button_load.setOnClickListener{onClickGetFile()}
    }

    fun onClickComp(){
        //val  otherPath = "content://com.android.externalstorage.documents/document/primary%3ADownload%2Fgazp_210301_210302.txt"


//        val imagePath = File(Context., "images")
//        val newFile = File(imagePath, "default_image.jpg")
//        val contentUri =
//            getUriForFile(context!!, "com.mydomain.fileprovider", newFile)


        //this::class.java.getResource("gazp_210318_210318.txt")
        //val arr = util.Parse(this::class.java.getResource("gazp_210318_210318.txt"))
        if(filepath!= "") {
            Log.w("MyApp", "gg"+ filepath)

            //val arr = util.Parse(filepath)
            Log.w("MyApp", "gg")
        }
        val arr = util.Parse()
//        var dd=arr.map { t -> 227.5800000 }
        val movingAverage = arr.windowed(4,1) { it.average() }
        //Log.w("MyApp", arr.toString())
        //Log.w("MyApp", filepath.toString())
        chart.isDragEnabled=true
        chart.isScaleXEnabled=false
        chart.isScaleYEnabled=false


        val itt =arr.iterator()
        var ind = 0
        var tmp =itt.next().toFloat()
        val a:ArrayList<Entry> = ArrayList<Entry>()
        a.add( Entry(ind.toFloat(),tmp ))
        while (itt.hasNext()){
            ind += 1
            tmp=itt.next().toFloat()
            a.add( Entry(ind.toFloat(),tmp)  )
        }
        val set1 = LineDataSet(a,"set1")
        set1.fillAlpha=110
        set1.fillColor= Color.CYAN
        set1.color=Color.RED
        set1.lineWidth=3f
        set1.valueTextSize=10f
        set1.valueTextColor=Color.BLACK
        val datesets:ArrayList<ILineDataSet> = ArrayList<ILineDataSet>()
        datesets.add(set1)







        val ittr =movingAverage.iterator()
        var indr= 0
        var tmpr =ittr.next().toFloat()
        val ar:ArrayList<Entry> = ArrayList<Entry>()
        ar.add( Entry(indr.toFloat(),tmpr ))
        while (ittr.hasNext()){
            indr += 1
            tmpr=ittr.next().toFloat()
            ar.add( Entry(indr.toFloat(),tmpr)  )
        }
        val set2 = LineDataSet(ar,"set2")
        set2.fillAlpha=110
        set2.fillColor= Color.CYAN
        set2.color=Color.GREEN
        set2.lineWidth=3f
        set2.valueTextSize=10f
        set2.valueTextColor=Color.BLACK

        datesets.add(set2)




        val data = LineData(datesets as List<ILineDataSet>?)
        chart.data=data
    }

    fun onClickGetFile(){
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==111 && data != null) {
            this.filepath = data.data!!.path!!
            //data.data.encodedPath
        }


    }
}